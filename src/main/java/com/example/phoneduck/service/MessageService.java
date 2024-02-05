package com.example.phoneduck.service;

import com.example.phoneduck.model.Message;
import com.example.phoneduck.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void addMessage(Message message){
        messageRepository.save(message);
    }

    public List<String> findContentByChannelId(Long channelid){
        return messageRepository.findContentByChannelId(channelid);
    }

    public Optional<Message> searchMessageById(Long messageId){
        return messageRepository.getMessageById(messageId);
    }

    public void updateMessage(Message message){
        messageRepository.save(message);
    }

    public void deleteByChannelId(Long channelId){
        messageRepository.deleteByChannel_Id(channelId);
    }

    public void deleteMessage(Long messageId){
        messageRepository.deleteById(messageId);
    }
}
