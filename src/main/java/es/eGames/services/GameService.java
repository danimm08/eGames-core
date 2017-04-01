package es.eGames.services;

import es.eGames.forms.GameDetailsForm;
import es.eGames.model.Game;
import es.eGames.model.PersonalGame;
import es.eGames.repositories.GameRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

/**
 * Created by daniel on 28/02/17.
 */
@Service
@Transactional
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PersonalGameService personalGameService;

    @Autowired
    private Environment env;

    public Collection<Game> gameList(String filterBy, String param) throws Exception {
        System.out.println(filterBy);
        Collection<Game> games;
        if ((filterBy.equals("") || filterBy.equals("genre") || filterBy.equals("platform") || filterBy.equals("firstReleaseDate"))) {

            if (filterBy.equals("genre")) {
                games = gameRepository.findByGenreName(param);
            } else if (filterBy.equals("platform")) {
                games = gameRepository.findByPlatformName(param);
            } else if (filterBy.equals("firstReleaseDate")) {
                games = gameRepository.findByFirstReleaseDate(new Integer(param));
            } else {
                games = gameRepository.findAll();
            }
//          Initialize lazy collections
            games.forEach(game -> Hibernate.initialize(game.getGenres()));
            games.forEach(game -> Hibernate.initialize(game.getGameModes()));
        } else {
            games = null;
        }
        return games;
    }

    public GameDetailsForm detailsOfGame(int gameId, String orderBy) {
        GameDetailsForm gameDetailsForm;

        Game game = gameRepository.findOne(gameId);
        Assert.notNull(game);
        Hibernate.initialize(game.getGenres());
        Hibernate.initialize(game.getGameModes());

        List<PersonalGame> personalGameList;
        personalGameList = personalGameService.findOrderedPersonalGamesByGameId(gameId, orderBy);
        Assert.notNull(personalGameList);

        gameDetailsForm = new GameDetailsForm(game, personalGameList);

        return gameDetailsForm;
    }

    public List<GameDetailsForm> listRecommendedGames(int gameId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", env.getProperty("es.eGames.recommenderSystem.password"));
        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<List> request = restTemplate.exchange(env.getProperty("es.eGames.recommenderSystem.url") + gameId, HttpMethod.GET, httpEntity, List.class);
        List<Integer> listOfGameIDs;
        listOfGameIDs = request.getBody();
        List<GameDetailsForm> res = new ArrayList<>();
        for (Integer id : listOfGameIDs) {
            Game game = gameRepository.findOne(id);
            GameDetailsForm gameDetailsForm = new GameDetailsForm(game, null);
            if (!res.contains(gameDetailsForm))
                res.add(gameDetailsForm);
        }
        return res;
    }

    private List<GameDetailsForm> listCloseGames() {
        List<PersonalGame> personalGames = personalGameService.findAllExceptOfPrincipal();
        List<PersonalGame> personalGameIntegerMap = personalGameService.calculateNearestPersonalGames(personalGames);
        List<PersonalGame> personalGamesWithDistance = personalGameService.calculateDistances(personalGameIntegerMap);
        personalGamesWithDistance.sort(Comparator.comparing(PersonalGame::getDistance));
        List<GameDetailsForm> result = new ArrayList<>();
        for (PersonalGame p : personalGamesWithDistance) {
            GameDetailsForm gameDetailsForm = new GameDetailsForm(p.getGame(), null);
            if (!result.contains(gameDetailsForm))
                result.add(gameDetailsForm);
        }
        return result;

    }

    private List<GameDetailsForm> listGamesOfFollowees() {
        List<PersonalGame> personalGames;
        personalGames = personalGameService.findPersonalGamesOfFollowees();
        List<GameDetailsForm> result = new ArrayList<>();
        for (PersonalGame p : personalGames) {
            GameDetailsForm gameDetailsForm = new GameDetailsForm(p.getGame(), null);
            if (!result.contains(gameDetailsForm))
                result.add(gameDetailsForm);
        }
        return result;
    }


    public List<GameDetailsForm> listGames(int gameId, String type) {
        List<GameDetailsForm> result;
        if (type.equals("recommend")) {
            result = listRecommendedGames(gameId);
        } else if (type.equals("followees")) {
            result = listGamesOfFollowees();
        } else if (type.equals("distance")) {
            result = listCloseGames();
        } else {
            throw new IllegalArgumentException("The arguments you have provided are not correct.");
        }
        return result;
    }


    public List<Game> search(String toSearch) {
        List<Game> games;
        games = gameRepository.search(toSearch);
        return games;
    }
}
