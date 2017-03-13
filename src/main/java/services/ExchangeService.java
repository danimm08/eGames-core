package services;

import forms.ExchangeForm;
import model.*;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ExchangeRepository;
import security.UserDetailsService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        System.out.println(username);
        User u1 = userService.findByUsername(username);
        User u2 = userService.findByPersonalGameId(personalGameId);
        ef.setPersonalGamesUser1(personalGameService.findAllPersonalGameByUser(u1.getId()));
        ef.setPersonalGamesUser2(personalGameService.findAllPersonalGameByUser(u2.getId()));
        ef.setNotes(new ArrayList<Note>());
        ef.setType(Type.Fijo);
        ef.setWayExchange("");

        return ef;
    }

    public Exchange save(ExchangeForm ef) {

        User u = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        Hibernate.initialize(u.getFollowees());
        Hibernate.initialize(u.getFollowers());

        Date now = new Date();

        Exchange exchange = new Exchange();
        exchange.setWayExchange(ef.getWayExchange());
        exchange.setType(ef.getType());
        exchange.setUser(u);
        exchange.setCreationDate(now);
        exchange.setEventDate(null);
        exchange.setLastUpdateDate(now);
        exchange.setStatus(null);
        exchange.setNumberOfAttemps(1);


        Exchange res = exchangeRepository.save(exchange);

        if (ef.getNotes().size() > 0) {
            Note note = ef.getNotes().get(ef.getNotes().size() - 1);
            note.setDate(now);
            note.setExchange(res);
            note.setUser(u);
            noteService.save(note);
        }

        ef.getPersonalGamesUser1().forEach(personalGame -> {
            personalGame.setExchange(res);
            personalGameService.save(personalGame);
        });
        ef.getPersonalGamesUser2().forEach(personalGame -> {
            personalGame.setExchange(res);
            personalGameService.save(personalGame);
        });

        return res;

    }

    public boolean acceptOrDecline(int exchangeId, boolean isAccept) {
        boolean res = false;
        Exchange exchange = exchangeRepository.findOne(exchangeId);
        if (isAccept) {
            exchange.setStatus(true);
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
        User u = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        Hibernate.initialize(u.getFollowees());
        Hibernate.initialize(u.getFollowers());

        Date now = new Date();

        exchange.setWayExchange(ef.getWayExchange());
        exchange.setType(ef.getType());
        exchange.setLastUpdateDate(now);
        exchange.setNumberOfAttemps(exchange.getNumberOfAttemps() + 1);

        Exchange res = exchangeRepository.save(exchange);

        if (ef.getNotes().size() > 0) {
            Note note = ef.getNotes().get(ef.getNotes().size() - 1);
            note.setDate(now);
            note.setExchange(res);
            note.setUser(u);
            noteService.save(note);
        }

        List<PersonalGame> personalGameList = personalGameService.findAllPersonalGameByExchange(exchangeId);
        personalGameList.forEach(personalGame -> {
            personalGame.setExchange(null);
            personalGameService.save(personalGame);
        });


        ef.getPersonalGamesUser1().forEach(personalGame -> {
            personalGame.setExchange(res);
            personalGameService.save(personalGame);
        });

        ef.getPersonalGamesUser2().forEach(personalGame -> {
            personalGame.setExchange(res);
            personalGameService.save(personalGame);
        });

        return res;

    }

    public ExchangeForm getExchangeForm(int exchangeId) {

        Exchange exchange = exchangeRepository.findOne(exchangeId);
        User u1 = exchange.getUser();
        PersonalGame auxPersonalGame = personalGameService.findAllPersonalGameByExchange(exchangeId)
                .stream().filter(personalGame -> personalGame.getExchange().getId() == exchangeId && personalGame.getUser().getId() != u1.getId()).findAny().get();
        System.out.println(auxPersonalGame);
        User u2 = auxPersonalGame.getUser();

        ExchangeForm ef = new ExchangeForm();
        ef.setPersonalGamesUser1(personalGameService.findAllPersonalGameByUserAndExchange(exchangeId, u1.getId()));
        ef.setPersonalGamesUser2(personalGameService.findAllPersonalGameByUserAndExchange(exchangeId, u2.getId()));
        ef.setWayExchange(exchange.getWayExchange());
        ef.setType(exchange.getType());
        List<Note> notes = noteService.findNotesByExchange(exchangeId);
        ef.setNotes(notes);

        return ef;


    }
}
