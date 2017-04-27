package es.eGames.services;

import es.eGames.forms.DetailsOfExchangeForm;
import es.eGames.forms.ExchangeForm;
import es.eGames.model.*;
import es.eGames.repositories.ExchangeRepository;
import es.eGames.security.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by daniel on 9/03/17.
 */
@Transactional
@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;


    @Autowired
    private UserService userService;

    @Autowired
    private PersonalGameService personalGameService;

    @Autowired
    private NoteService noteService;

    public ExchangeService() {
        super();
    }

    public ExchangeForm createForm(int personalGameId) {
        ExchangeForm ef = new ExchangeForm();
        String username = UserDetailsService.getPrincipal().getUsername();
        Assert.isTrue(username != null, "You must be logged in.");
        User u1 = userService.findByUsername(username);
        User u2 = userService.findByPersonalGameId(personalGameId);
        Assert.isTrue(u1 != u2, "It's not allowed make exchanges with yourself.");
        ef.setPersonalGamesUser1(personalGameService.findAvailablePersonalGameByUser(u1.getId()));
        ef.setPersonalGamesUser2(personalGameService.findAvailablePersonalGameByUser(u2.getId()));
        ef.setNotes(new ArrayList<Note>());
        ef.setType(Type.Fijo);
        ef.setWayExchange("");

        return ef;
    }

    public Exchange save(ExchangeForm ef) {

        User principal = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());

        Date now = new Date();

        Exchange exchange = new Exchange();
        exchange.setWayExchange(ef.getWayExchange());
        exchange.setType(ef.getType());
        exchange.setUser(principal);
        exchange.setCreationDate(now);
        exchange.setEventDate(null);
        exchange.setLastUpdateDate(now);
        exchange.setStatus(null);
        exchange.setNumberOfAttemps(1);
        exchange.setLastModifier(principal);


        Exchange res = exchangeRepository.save(exchange);

        if (ef.getNotes().size() > 0) {
            Note note = ef.getNotes().get(ef.getNotes().size() - 1);
            note.setDate(now);
            note.setExchange(res);
            note.setUser(principal);
            noteService.save(note);
        }

        Assert.isTrue(ef.getPersonalGamesUser2().size() > 0, "A game has to be selected to exchange");

        ef.getPersonalGamesUser1().forEach(personalGame -> {
            PersonalGame pg = personalGameService.findById(Integer.toString(personalGame.getId()));
            Assert.isTrue(pg.getExchange() == null, "This personal game is not available to exchange it");
            Assert.isTrue(pg.getUser().equals(principal), "This personal game does not belong to you");
            pg.setExchange(res);
            personalGameService.save(pg);
        });

        User u2 = personalGameService.findById(Integer.toString(ef.getPersonalGamesUser2().get(0).getId())).getUser();
        ef.getPersonalGamesUser2().forEach(personalGame -> {
            PersonalGame pg = personalGameService.findById(Integer.toString(personalGame.getId()));
            Assert.isTrue(pg.getExchange() == null, "This personal game is not available to exchange it");
            Assert.isTrue(pg.getUser().equals(u2), "All games must to belong to same user");
            pg.setExchange(res);
            personalGameService.save(pg);
        });

        return res;

    }

    public boolean acceptOrDecline(int exchangeId, boolean isAccept) {
        boolean res = false;
        Exchange exchange = exchangeRepository.findOne(exchangeId);
        Assert.isNull(exchange.getStatus(), "It's not possible change the status of this exchange");
        Assert.notNull(exchange);

        User principal = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());

        List<User> usersInExchange = new ArrayList<>(usersInExchange(exchangeId));
        Assert.isTrue(usersInExchange.contains(principal), "You are not authorized to perform this operation");

        List<PersonalGame> personalGamesUser1 = extractPersonalGamesByExchange(exchangeId).get("user1");
        List<PersonalGame> personalGamesUser2 = extractPersonalGamesByExchange(exchangeId).get("user2");

        User u1 = personalGamesUser1.get(0).getUser();
        User u2 = personalGamesUser2.get(0).getUser();

        if (isAccept) {
            Assert.isTrue(personalGamesUser1.size() > 0);
            Assert.isTrue(personalGamesUser2.size() > 0);
            Assert.isTrue(!exchange.getLastModifier().equals(principal), "You must wait the response of the other player");

            exchange.setStatus(true);

            u1.setnExchanges(personalGamesUser1.get(0).getUser().getnExchanges() + 1);
            u2.setnExchanges(personalGamesUser2.get(0).getUser().getnExchanges() + 1);
            userService.save(u1);
            userService.save(u2);
        } else {
            exchange.setStatus(false);
        }
        exchange.setNumberOfAttemps(exchange.getNumberOfAttemps() + 1);
        Date now = new Date();
        exchange.setEventDate(now);
        exchange.setLastUpdateDate(now);

        Exchange exchangeDb = exchangeRepository.save(exchange);

        if (exchangeDb != null) {
            res = true;
        }

        return res;

    }

    public Exchange negotiate(ExchangeForm ef, int exchangeId) {

        Exchange exchange = exchangeRepository.findOne(exchangeId);
        User principal = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        Assert.notNull(exchange);

        Date now = new Date();

        exchange.setWayExchange(ef.getWayExchange());
        exchange.setType(ef.getType());
        exchange.setLastUpdateDate(now);
        exchange.setLastModifier(principal);
        exchange.setNumberOfAttemps(exchange.getNumberOfAttemps() + 1);

        Exchange res = exchangeRepository.save(exchange);

        Assert.isTrue(ef.getPersonalGamesUser2().size() > 0, "A game has to be selected to exchange");
        Assert.isTrue(ef.getPersonalGamesUser1().size() > 0, "A game has to be selected to exchange");

        if (ef.getNotes().size() > 0) {
            Note note = ef.getNotes().get(ef.getNotes().size() - 1);
            note.setDate(now);
            note.setExchange(res);
            note.setUser(principal);
            noteService.save(note);
        }

        List<PersonalGame> personalGameList = personalGameService.findAllPersonalGameByExchange(exchangeId);
        personalGameList.forEach(personalGame -> {
            personalGame.setExchange(null);
            personalGameService.save(personalGame);
        });


        ef.getPersonalGamesUser1().forEach(personalGame -> {
            PersonalGame pg = personalGameService.findById(Integer.toString(personalGame.getId()));
            Assert.isTrue(pg.getExchange() == null, "This personal game is not available to exchange it");
            Assert.isTrue(pg.getUser().equals(exchange.getUser()), "This personal game does not belong to you");
            pg.setExchange(res);
            personalGameService.save(pg);
        });

        User u2 = personalGameService.findById(Integer.toString(ef.getPersonalGamesUser2().get(0).getId())).getUser();
        Set<User> usersInExchange = usersInExchange(personalGameList);
        Assert.isTrue(usersInExchange.contains(u2), "You are not authorized to perform this operation");

        ef.getPersonalGamesUser2().forEach(personalGame -> {
            PersonalGame pg = personalGameService.findById(Integer.toString(personalGame.getId()));
            Assert.isTrue(pg.getExchange() == null, "This personal game is not available to exchange it");
            Assert.isTrue(pg.getUser().equals(u2), "All games must to belong to same user");
            pg.setExchange(res);
            personalGameService.save(pg);
        });

        return res;

    }

    private Map<String, List<PersonalGame>> extractPersonalGamesByExchange(int exchangeId) {
        Map<String, List<PersonalGame>> res = new HashMap<>();
        Exchange exchange = exchangeRepository.findOne(exchangeId);
        Assert.notNull(exchange);
        User principal = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        Assert.isTrue(usersInExchange(exchangeId).contains(principal));
        User u1 = principal;
        PersonalGame auxPersonalGame = personalGameService.findAllPersonalGameByExchange(exchangeId)
                .stream().filter(personalGame -> personalGame.getExchange().getId() == exchangeId && personalGame.getUser().getId() != u1.getId()).findAny().get();
        User u2 = auxPersonalGame.getUser();
        List<PersonalGame> listPersonalGameUser1 = personalGameService.findAllPersonalGameByUserAndExchange(exchangeId, u1.getId());
        List<PersonalGame> listPersonalGameUser2 = personalGameService.findAllPersonalGameByUserAndExchange(exchangeId, u2.getId());

        res.put("user1", listPersonalGameUser1);
        res.put("user2", listPersonalGameUser2);

        return res;
    }

    public Set<User> usersInExchange(int exchangeId) {
        Set<User> res = new HashSet<>();
        List<PersonalGame> personalGames = personalGameService.findAllPersonalGameByExchange(exchangeId);
        personalGames.stream().forEach(personalGame -> res.add(personalGame.getUser()));
        return res;
    }


    public Set<User> usersInExchange(List<PersonalGame> personalGames) {
        Set<User> res = new HashSet<>();
        personalGames.stream().forEach(personalGame -> res.add(personalGame.getUser()));
        return res;
    }

    public DetailsOfExchangeForm getExchangeInfo(int exchangeId) {
        User principal = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        List<User> usersInExchange = new ArrayList<>(usersInExchange(exchangeId));
        Assert.isTrue(usersInExchange.contains(principal), "You are not authorized to perform this operation");

        Map<String, List<PersonalGame>> map = extractPersonalGamesByExchange(exchangeId);
        Exchange exchange = exchangeRepository.findOne(exchangeId);

        Set<PersonalGame> personalGameUser1 = new HashSet(map.get("user1"));
        Set<PersonalGame> personalGameUser2 = new HashSet(map.get("user2"));
        List<Note> notes = noteService.findNotesByExchange(exchangeId);
        DetailsOfExchangeForm detailsOfExchangeForm = new DetailsOfExchangeForm(exchange, personalGameUser1, personalGameUser2, notes);

        return detailsOfExchangeForm;
    }

    public List<DetailsOfExchangeForm> getListOfMyExchanges() {
        User u = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        Assert.isTrue(u != null, "You must be logged in to use this feature");
        List<Exchange> myExchanges = exchangeRepository.findByUserId(u.getId());
        List<DetailsOfExchangeForm> myDetailsOfExchangesForm = new ArrayList<>();
        for (Exchange e : myExchanges) {
            Map<String, List<PersonalGame>> map = extractPersonalGamesByExchange(e.getId());
            Set<PersonalGame> personalGameUser1 = new HashSet(map.get("user1"));
            Set<PersonalGame> personalGameUser2 = new HashSet(map.get("user2"));
            List<Note> notes = noteService.findNotesByExchange(e.getId());
            DetailsOfExchangeForm detailsOfExchangeForm = new DetailsOfExchangeForm(e, personalGameUser1, personalGameUser2, notes);
            myDetailsOfExchangesForm.add(detailsOfExchangeForm);
            myDetailsOfExchangesForm.sort(Comparator.comparing(DetailsOfExchangeForm::getCreationDate).reversed());
        }
        return myDetailsOfExchangesForm;
    }

    public Exchange findById(Integer exchangeId) {
        return exchangeRepository.findOne(exchangeId);
    }
}
