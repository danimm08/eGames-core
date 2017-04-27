package es.eGames.repositories;

import es.eGames.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by daniel on 3/04/17.
 */
@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Integer> {

    Qualification findByUserIdAndExchangeId(Integer userId, Integer exchangeId);

    @Query("select q from Qualification q where q.user.id <> ?1 and q.exchange = ANY(select pg.exchange from PersonalGame pg where pg.user.id = ?1)")
    List<Qualification> findByUserId(Integer userId);

    @Query("select count(q) from Qualification q where q.user.id = ?1 and q.exchange.id = ?2")
    int isAllowedToQualify(int userId, int exchangeId);
}
