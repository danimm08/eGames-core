package services;

import model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import repositories.NoteRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by daniel on 13/03/17.
 */
@Transactional
@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public NoteService() {
        super();
    }

    public List<Note> findNotesByExchange(int exchangeId) {
        List<Note> notes;
        notes = noteRepository.findNotesByExchange(exchangeId);
        Assert.notNull(notes);
        return notes;
    }

    public Note save(Note note){
        Assert.notNull(note);
        Note res = noteRepository.save(note);
        return res;
    }

}
