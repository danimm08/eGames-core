package servicies;

import model.PersonalGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.PersonalGameRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

}
