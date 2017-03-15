package services;

import model.PersonalGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.PersonalGameRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by daniel on 1/03/17.
 */
@Transactional
@Service
public class PersonalGameService {

    @Autowired
    private PersonalGameRepository personalGameRepository;

    public PersonalGameService() {
        super();
    }

    public List<PersonalGame> findOrderedPersonalGamesByGameId(int gameId, String orderBy) {
        List<PersonalGame> personalGameList;
        Assert.notNull(gameId);
        Assert.notNull(orderBy);

        if (orderBy == "reputation") {
            personalGameList = personalGameRepository.findByGameIdOrderByReputation(gameId);
        } else if (orderBy == "type") {
            personalGameList = personalGameRepository.findByGameIdOrderByType(gameId);
        } else if (orderBy == "distance") {
            //TODO: Change this for real method when the distances are defined.
            personalGameList = new ArrayList<>();
        } else {
            personalGameList = personalGameRepository.findByGameId(gameId);
        }

        return personalGameList;
    }

    public PersonalGame findById(String personalGameId) {
        Assert.notNull(personalGameId);
        PersonalGame personalGame;
        personalGame = personalGameRepository.findOne(new Integer(personalGameId));
        personalGame.getGame().setGenres(new ArrayList<>());
        personalGame.getGame().setGameModes(new ArrayList<>());
        personalGame.getUser().setFollowees(null);
        personalGame.getUser().setFollowers(null);

        return personalGame;
    }


    public Collection<PersonalGame> listPersonalGamesByUser(int userId) {
        Assert.notNull(userId);
        List<PersonalGame> personalGames;
        personalGames = personalGameRepository.findByUserId(userId);
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
        personalGames = personalGameRepository.findAllPersonalGameByUserAndExchange(exchangeId,userId);
        Assert.notNull(personalGames);
        return personalGames;
    }

    public PersonalGame save(PersonalGame personalGame) {
        PersonalGame pg = personalGameRepository.save(personalGame);
        return pg;
    }
}
