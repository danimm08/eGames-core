package es.eGames.services;

import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixElementStatus;
import com.google.maps.model.DistanceMatrixRow;
import es.eGames.forms.PersonalGameForm;
import es.eGames.model.Image;
import es.eGames.model.PersonalGame;
import es.eGames.model.User;
import es.eGames.repositories.PersonalGameRepository;
import es.eGames.security.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by daniel on 1/03/17.
 */
@Transactional
@Service
public class PersonalGameService {

    @Autowired
    private PersonalGameRepository personalGameRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private Environment env;

    public PersonalGameService() {
        super();
    }

    public List<PersonalGame> findOrderedPersonalGamesByGameId(int gameId, String orderBy) {
        List<PersonalGame> personalGameList;
        Assert.notNull(gameId);
        Assert.notNull(orderBy);

        int userId = userService.findByUsername(UserDetailsService.getPrincipal().getUsername()).getId();

        if (orderBy.equals("reputation")) {
            personalGameList = personalGameRepository.findByGameIdOrderByReputation(gameId, userId);
        } else if (orderBy.equals("type")) {
            personalGameList = personalGameRepository.findByGameIdOrderByType(gameId, userId);
        } else if (orderBy.equals("followees")) {
            personalGameList = findPersonalGamesOfFolloweesByGameId(gameId, userId);
        } else if (orderBy.equals("distance")) {
            personalGameList = personalGameRepository.findByGameId(gameId, userId);
            List<PersonalGame> personalGameIntegerMap = calculateNearestPersonalGames(personalGameList);
            List<PersonalGame> personalGamesWithDistance = calculateDistances(personalGameIntegerMap);
            personalGamesWithDistance.sort(Comparator.comparing(PersonalGame::getDistance));
            personalGameList = personalGamesWithDistance;
        } else {
            personalGameList = personalGameRepository.findByGameId(gameId, userId);
        }

        return personalGameList;
    }

    public List<PersonalGame> calculateNearestPersonalGames(List<PersonalGame> personalGameList) {
        Map<PersonalGame, Integer> res = new HashMap<>();
        User u = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        Integer userZip = new Integer(u.getAddress().getZip());
        for (PersonalGame p : personalGameList) {
            Integer zip = new Integer(p.getUser().getAddress().getZip());
            Integer difference = Math.abs(userZip - zip);
            if (res.keySet().size() < 20) {
                res.put(p, difference);
            } else {
                Integer valueToDelete = res.values().stream().filter(integer -> integer > difference).findAny().get();
                if (valueToDelete != null) {
                    res.entrySet().removeIf(personalGameIntegerEntry -> personalGameIntegerEntry.getValue() == valueToDelete);
                    res.put(p, difference);
                }
            }
        }
        return new ArrayList<>(res.keySet());
    }

    public List<PersonalGame> calculateDistances(List<PersonalGame> personalGameIntegerMap) {
        List<PersonalGame> res = new ArrayList<>();
        User u = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        String[] origins = {u.getAddress().getZip() + " España"};
        List<String> destinationsAux = new ArrayList<>();
        for (PersonalGame p : personalGameIntegerMap) {
            destinationsAux.add(p.getUser().getAddress().getZip() + " España");
        }
        String[] destinations = destinationsAux.stream().toArray(String[]::new);

        GeoApiContext context = new GeoApiContext().setApiKey(env.getProperty("es.eGames.googlemaps.api.key"));
        DistanceMatrixApiRequest apiRequest = new DistanceMatrixApiRequest(context);
        apiRequest.language("es-ES");
        apiRequest.destinations(destinations);
        apiRequest.origins(origins);

        Assert.notEmpty(destinations, "No se han podido calcular los destinos");
        Assert.notEmpty(origins, "No se han podido calcular los origenes");

        DistanceMatrix distanceMatrix = null;
        try {
            distanceMatrix = apiRequest.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<DistanceMatrixRow> rows = Arrays.asList(distanceMatrix.rows);
        for (DistanceMatrixRow r : rows) {
            List<DistanceMatrixElement> elements = Arrays.asList(r.elements);
            for (DistanceMatrixElement e : elements) {
                Assert.isTrue(e.status.equals(DistanceMatrixElementStatus.OK), "Ha habido algún error en el cálculo de las distancias");
                Double distance = new Double(e.distance.humanReadable.replace(",", ".").split(" ")[0]);

                PersonalGame personalGame = personalGameIntegerMap.get(elements.indexOf(e));
                personalGame.setDistance(distance);
                res.add(personalGame);
            }

        }
        return res;
    }

    public PersonalGame findById(String personalGameId) {
        Assert.notNull(personalGameId);
        PersonalGame personalGame;
        personalGame = personalGameRepository.findOne(new Integer(personalGameId));
        return personalGame;
    }


    public Collection<PersonalGame> listPersonalGamesByUser(int userId) {
        Assert.notNull(userId);
        List<PersonalGame> personalGames;
        List<PersonalGame> personalGames1;
        personalGames = personalGameRepository.findAvailablePersonalGameByUser(userId);
        personalGames1 = personalGameRepository.findSemiAvailablePersonalGameByUser(userId);
        personalGames.addAll(personalGames1);
        Assert.notNull(personalGames);

        return personalGames;
    }

    public List<PersonalGame> findAvailablePersonalGameByUser(int id) {
        List<PersonalGame> personalGames;
        personalGames = personalGameRepository.findAvailablePersonalGameByUser(id);
        Assert.notNull(personalGames);
        return personalGames;
    }

    public List<PersonalGame> findAllPersonalGameByExchange(int id) {
        List<PersonalGame> personalGames;
        personalGames = personalGameRepository.findAllPersonalGameByExchange(id);
        Assert.notNull(personalGames);
        return personalGames;
    }

    public List<PersonalGame> findAllPersonalGameByUserAndExchange(int exchangeId, int userId) {
        List<PersonalGame> personalGames;
        personalGames = personalGameRepository.findAllPersonalGameByUserAndExchange(exchangeId, userId);
        Assert.notNull(personalGames);
        return personalGames;
    }

    public PersonalGame savePersonalGameForm(PersonalGameForm personalGameForm) {
        User u = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        PersonalGame pg = new PersonalGame();
        pg.setDescription(personalGameForm.getDescription());
        pg.setGame(personalGameForm.getGame());
        pg.setType(personalGameForm.getType());
        pg.setUser(u);
        pg.setNumberOfViews(0);
        personalGameRepository.save(pg);
        return pg;
    }

    public PersonalGame save(PersonalGame personalGame) {
        return personalGameRepository.save(personalGame);
    }

    public List<PersonalGame> findAll() {
        List<PersonalGame> personalGames;
        personalGames = personalGameRepository.findAll();
        return personalGames;
    }

    public List<PersonalGame> findAllExceptOfPrincipal() {
        List<PersonalGame> personalGames;
        personalGames = personalGameRepository.findAllExceptOfPrincipal(UserDetailsService.getPrincipal().getUsername());
        return personalGames;
    }

    public List<PersonalGame> findPersonalGamesOfFolloweesByGameId(int gameId, int userId) {
        List<PersonalGame> personalGames;
        personalGames = personalGameRepository.findPersonalGamesOfFolloweesByGameId(gameId, userId);
        return personalGames;
    }

    public List<PersonalGame> findPersonalGamesOfFollowees() {
        List<PersonalGame> personalGames;
        int userId = userService.findByUsername(UserDetailsService.getPrincipal().getUsername()).getId();
        personalGames = personalGameRepository.findPersonalGamesOfFollowees(userId);
        return personalGames;
    }

    public void editPersonalGame(int personalGameId, PersonalGameForm personalGameForm) {
        User principal = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        PersonalGame personalGame = personalGameRepository.findOne(personalGameId);
        Assert.isTrue(principal.equals(personalGame.getUser()));

        if (!personalGameForm.getDescription().isEmpty())
            personalGame.setDescription(personalGameForm.getDescription());

        if (personalGameForm.getType() != null)
            personalGame.setType(personalGameForm.getType());

        if (personalGameForm.getGame() != null)
            personalGame.setGame(personalGameForm.getGame());

        personalGameRepository.save(personalGame);

    }

    public void delete(int personalGameId) {
        User principal = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());
        PersonalGame personalGame = personalGameRepository.findOne(personalGameId);
        Assert.isTrue(personalGame.getExchange() == null, "You can not delete a game that is involved in an exchange");
        Assert.isTrue(principal.equals(personalGame.getUser()), "You are not authorized to perform this operation");

        Set<Image> images = personalGame.getImages();
        try {
            for (Image i : images) {
                imageService.deletePicture(i.getPathUrl());
            }
            personalGameRepository.delete(personalGame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PersonalGame> search(String toSearch) {
        List<PersonalGame> games;
        games = personalGameRepository.search(toSearch);
        return games;
    }

    public List<PersonalGame> findByUserId(Integer userId) {
        return personalGameRepository.findByUserId(userId);
    }
}
