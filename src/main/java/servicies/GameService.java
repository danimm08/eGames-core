package servicies;

import model.Game;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.GameRepository;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * Created by daniel on 28/02/17.
 */
@Service
@Transactional
public class GameService {

    @Autowired
    private GameRepository gameRepository;

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

}
