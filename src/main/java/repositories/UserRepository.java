package repositories;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by daniel on 11/02/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    @Query("select u from User u where u.userAccount.username = ?1")
    User findByUsername(String username);

    @Query("select pg.user from PersonalGame pg where pg.id = ?1")
    User findByPersonalGameId(int personalGameId);
}
