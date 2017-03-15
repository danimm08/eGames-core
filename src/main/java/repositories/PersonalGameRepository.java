package repositories;

import model.PersonalGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by daniel on 28/02/17.
 */
@Repository
public interface PersonalGameRepository extends JpaRepository<PersonalGame, Integer> {

    @Query("select pg from PersonalGame pg where pg.game.id = ?1 order by pg.user.reputation")
    List<PersonalGame> findByGameIdOrderByReputation(int gameId);

    @Query("select pg from PersonalGame pg where pg.game.id = ?1 order by pg.type")
    List<PersonalGame> findByGameIdOrderByType(int gameId);

    List<PersonalGame> findByGameId(int gameId);

    @Query("select pg from PersonalGame pg where pg.user.id = ?1")
    List<PersonalGame> findByUserId(int userId);

    @Query("select pg from PersonalGame pg where pg.user.id = ?1 and pg.exchange is null")
    List<PersonalGame> findAvailablePersonalGameByUser(int id);

    @Query("select pg from PersonalGame pg where pg.exchange.id = ?1")
    List<PersonalGame> findAllPersonalGameByExchange(int id);

    @Query("select pg from PersonalGame pg where pg.exchange.id = ?1 and pg.user.id = ?2")
    List<PersonalGame> findAllPersonalGameByUserAndExchange(int exchangeId, int userId);
}
