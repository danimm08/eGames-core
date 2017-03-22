package es.eGames.repositories;

import es.eGames.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daniel on 11/02/17.
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findByAuthority(String name);
}
