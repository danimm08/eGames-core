package repositories;

import model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by daniel on 17/03/17.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
