package es.eGames.repositories;

import es.eGames.model.Message;
import es.eGames.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by daniel on 31/03/17.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("select m from Message m where m.sender = ?1 or m.recipient = ?1")
    List<Message> findChats(User principal);

    @Query("select m from Message m where (m.sender.userAccount.username = ?1 or m.recipient.userAccount.username = ?1) and (m.sender.userAccount.username = ?2 or m.recipient.userAccount.username = ?2) order by m.moment desc ")
    List<Message> findMessagesFromChat(String principal, String username1);
}
