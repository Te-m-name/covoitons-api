package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.ConversationEntity;
import com.example.covoitonsapi.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.mail.Message;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
//    @Query(value = "select * from users join messages on users.id = messages.id_recipient where (id_sender = ?1 and id_recipient = ?2) or (id_sender = ?2 and id_recipient = ?1)", nativeQuery = true)
//    List<MessageEntity> findBySenderAndRecipient(Integer sender, Integer recipient);
//
//    @Query(value = "SELECT users.id, users.firstname, users2.id as id_recipient, id_sender, subquery.message_text as message_text FROM users JOIN\n" +
//            "    ( SELECT messages.message_text,row_number() OVER ( PARTITION BY id_sender + id_recipient ORDER BY id DESC) AS row_num,id_sender,id_recipient,id\n" +
//            "        FROM messages\n" +
//            "        GROUP BY id, id_recipient, id_sender, messages.message_text)\n" +
//            "        AS subquery ON (\n" +
//            "            ( subquery.id_sender = users.id OR subquery.id_recipient = users.id)  AND row_num = 1 )\n" +
//            "            JOIN users as users2 ON ( users2.id = CASE WHEN users.id = subquery.id_sender THEN subquery.id_recipient ELSE subquery.id_sender END )\n" +
//            "WHERE users.id = ?1 ORDER BY subquery.id DESC;", nativeQuery = true)
//    List<MessageEntity> findLatestMessages(Integer user);

    @Query(value = "select id from conversations where (user1=?1 and user2=?2) or (user2=?1 and user1=?2)", nativeQuery = true)
    List<MessageEntity> getConversation(Integer user1, Integer user2);

    List<MessageEntity> findAllByconversationID(Integer id_conversation);

}


//select * from conversations join messages on conversations.id = messages.id_conversation where id_conversation in