package com.example.socialnetwork.repository;

import com.example.socialnetwork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_friend(user_id,friend_id) VALUES(:userId,:friend_id)", nativeQuery = true)
    void addFriend(@Param("userId") int userId, @Param("friend_id") int friend_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO friend_request(from_id,to_id) VALUES(:userId,:friendId)", nativeQuery = true)
    void saveFriendRequest(@Param("userId") int userId, @Param("friendId") int friendId);

    @Query(value = "SELECT `from_id` FROM friend_request WHERE `to_id` = :friendId", nativeQuery = true)
    List<Integer> findAllFriendRequests(@Param("friendId") int friendId);

    @Query(value = "SELECT `user_id` FROM user_friend WHERE `friend_id` = :userId", nativeQuery = true)

    List<Integer> findAllFriendsSecond(@Param("userId") int userId);


    @Query(value = "SELECT `friend_id` FROM user_friend WHERE `user_id` = :userId", nativeQuery = true)
    List<Integer> findAllFriends(@Param("userId") int userId);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_friend WHERE friend_id = :userId AND user_id = :fromId", nativeQuery = true)
    void deleteUserFriendById(@Param("userId") int id, @Param("fromId") int fromId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM friend_request WHERE from_id = :userId AND to_id = :friendId", nativeQuery = true)
    void removeRequest(@Param("userId") int userId, @Param("friendId") int friendId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_friend WHERE user_id = :userId AND friend_id = :fromId", nativeQuery = true)
    void deleteFriendById(@Param("userId") int id, @Param("fromId") int fromId);


}
