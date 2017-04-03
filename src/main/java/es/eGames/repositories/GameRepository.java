package es.eGames.repositories;

import es.eGames.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by daniel on 28/02/17.
 */
@Repository
public interface GameRepository extends PagingAndSortingRepository<Game, Integer> {

    @Query("select g from Game g join g.genres gs where gs.name = ?1")
    Page<Game> findByGenreName(String genre, Pageable pageable);


    Page<Game> findByPlatformName(String platform, Pageable pageable);

    @Query("select g from Game g where YEAR(g.firstReleaseDate)=?1")
    Page<Game> findByFirstReleaseDate(Integer year, Pageable pageable);

    @Query("select g from Game g where g.title like %?1% or g.storyLine like %?1%")
    List<Game> search(String toSearch);

    Page<Game> findAll(Pageable pageable);
}
