package es.eGames.repositories;

import es.eGames.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daniel on 9/03/17.
 */
@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer>{
}
