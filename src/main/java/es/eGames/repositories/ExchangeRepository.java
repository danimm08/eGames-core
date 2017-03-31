package es.eGames.repositories;

import es.eGames.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by daniel on 9/03/17.
 */
@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer>{
    @Query("select pg.exchange from PersonalGame pg where pg.user.id = ?1")
    List<Exchange> findByUserId(int userId);
}
