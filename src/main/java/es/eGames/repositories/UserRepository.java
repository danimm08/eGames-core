package es.eGames.repositories;

import es.eGames.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by daniel on 11/02/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    @Query("select u from User u where u.userAccount.username = ?1")
    User findByUsername(String username);

    @Query("select pg.user from PersonalGame pg where pg.id = ?1")
    User findByPersonalGameId(int personalGameId);

    @Query("select u from User u where u.name like %?1% or u.surname like %?1% or u.userAccount.username like %?1%")
    List<User> search(String toSearch);
}
