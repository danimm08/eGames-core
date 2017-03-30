package es.eGames.repositories;

import es.eGames.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by daniel on 17/03/17.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query("select i from Image i where i.pathUrl LIKE ?1")
    Image findByPathUrl(String fileName);
}
