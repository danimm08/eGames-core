package es.eGames.repositories;

import es.eGames.model.Game;
import es.eGames.model.PersonalGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by daniel on 28/02/17.
 */
@Repository
public interface PersonalGameRepository extends JpaRepository<PersonalGame, Integer> {

    @Query("select pg from PersonalGame pg where pg.game.id = ?1 order by pg.user.reputation desc")
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

    @Query("select pg from PersonalGame pg where pg.user.userAccount.username not like ?1")
    List<PersonalGame> findAllExceptOfPrincipal(String username);

    @Query("select pg from PersonalGame pg where pg.user = ANY (select fw from User u join u.followees fw where u.id = ?1 )")
    List<PersonalGame> findPersonalGamesOfFollowees(int principalId);

    @Query("select g from PersonalGame g where g.description like %?1% or g.game.title like %?1%")
    List<PersonalGame> search(String toSearch);
    
}