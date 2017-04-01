package es.eGames.repositories;

import es.eGames.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by daniel on 28/02/17.
 */
@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query("select g from Game g join g.genres gs where gs.name = ?1")
    Collection<Game> findByGenreName(String genre);


    Collection<Game> findByPlatformName(String platform);

    @Query("select g from Game g where YEAR(g.firstReleaseDate)=?1")
    Collection<Game> findByFirstReleaseDate(Integer year);

    @Query("select g from Game g where g.title like %?1% or g.storyLine like %?1%")
    List<Game> search(String toSearch);
}
