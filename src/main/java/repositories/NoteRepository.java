package repositories;

import model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by daniel on 13/03/17.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {

    @Query("select n from Note n where n.exchange.id =?1")
    List<Note> findNotesByExchange(int exchangeId);

}
