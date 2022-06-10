package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<ConversationEntity, Integer> {

    @Query(value = "select conversations.id, user1, user2 from conversations where user2=?1 or user1=?1",
            nativeQuery = true)
    List<ConversationEntity> getLastestMessageOfEachConversation(Integer user);

    List<ConversationEntity> findAll();

    ConversationEntity findByUser1(Integer id);
}
