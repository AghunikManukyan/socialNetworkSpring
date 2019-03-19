package com.example.socialnetwork.repository;

import com.example.socialnetwork.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Query(value = "SELECT * FROM message WHERE `from_id_id` = :userId", nativeQuery = true)
    List<Message> findAllMessagesById(@Param("userId") int userId);

    @Query(value = "SELECT * FROM message WHERE `to_id_id` = :userId", nativeQuery = true)
    List<Message> findAllMessagesByFriendId(@Param("userId") int userId);
}
