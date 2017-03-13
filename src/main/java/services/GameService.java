package services;

import forms.GameDetailsForm;
import model.Game;
import model.PersonalGame;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.GameRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

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

}
