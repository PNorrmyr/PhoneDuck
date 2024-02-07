package com.example.phoneduck.repository;

import com.example.phoneduck.model.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT id, content FROM Message WHERE channel.id = :channelId")
    List<String> findContentByChannelId(Long channelId);


    void deleteByChannel_Id(Long channelId);

    Optional<Message> getMessageById(Long messageId);

}
