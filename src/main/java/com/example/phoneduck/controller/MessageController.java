package com.example.phoneduck.controller;

import com.example.phoneduck.model.Message;
import com.example.phoneduck.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/phoneduck/channels")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<String> updateMessage(@PathVariable Long messageId,
                                                @RequestBody Message message){
        Optional<Message> optionalMessage = messageService.searchMessageById(messageId);
        if (optionalMessage.isEmpty()){
            return ResponseEntity.status(404).body("Message not found");
        } else {
            Message currentMessage = optionalMessage.get();
            currentMessage.setContent(message.getContent());
            messageService.updateMessage(currentMessage);
            return ResponseEntity.status(201).body("Message was updated");
        }
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long messageId){
        Optional<Message> optionalMessage = messageService.searchMessageById(messageId);
        if (optionalMessage.isEmpty()){
            return ResponseEntity.status(404).body("Message not found");
        } else {
            messageService.deleteMessage(messageId);
            return ResponseEntity.status(200).body("Message " + messageId + " was deleted");
        }
    }
}
