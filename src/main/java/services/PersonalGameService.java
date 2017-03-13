package services;

import com.fasterxml.jackson.annotation.JsonView;
import model.PersonalGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.PersonalGameRepository;
import views.View;

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

    @JsonView(View.ListPersonalGame.class)
    //TODO: This annotation doesn't have sense, check if the annotation can be removed
    public Collection<PersonalGame> listPersonalGamesByUser(int userId) {
        Assert.notNull(userId);
        List<PersonalGame> personalGames;
        personalGames = personalGameRepository.findByUserId(userId);
//        personalGames.forEach(personalGame -> {
//            personalGame.getUser().setFollowees(new ArrayList<>());
//            personalGame.getUser().setFollowers(new ArrayList<>());
//            personalGame.getGame().setGenres(new ArrayList<>());
//            personalGame.getGame().setGameModes(new ArrayList<>());
//
//        });
        Assert.notNull(personalGames);

        return personalGames;
    }

    public List<PersonalGame> findAllPersonalGameByUser(int id) {
        List<PersonalGame> personalGames;
        personalGames = personalGameRepository.findAllPersoalGameByUser(id);
        Assert.notNull(personalGames);
        return personalGames;
    }

    public PersonalGame save(PersonalGame personalGame) {
        PersonalGame pg = personalGameRepository.save(personalGame);
        return pg;
    }
}
