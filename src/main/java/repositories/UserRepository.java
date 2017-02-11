package repositories;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daniel on 11/02/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
}
