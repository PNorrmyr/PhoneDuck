package com.example.phoneduck.controller;

import com.example.phoneduck.model.Channel;
import com.example.phoneduck.model.Message;
import com.example.phoneduck.service.ChannelService;
import com.example.phoneduck.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/phoneduck/channels")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public ResponseEntity<List<Channel>> getAll(){
        if (channelService.getAll().isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(channelService.getAll());
        }
    }


    @PostMapping("/")
    public ResponseEntity<String> addChannel(@RequestBody Channel channel){
        if (channel.getTitle() == null) {
            return ResponseEntity.status(400).body("Check correct title input");
        } else if (channel.getTitle().isEmpty()){
            return ResponseEntity.status(400).body("Channel must have title");
        } else {
            channelService.addChannel(channel);
            return ResponseEntity.status(201).body("Channel created");
        }
    }

    //Parametern Id representerar id på kanalen man vill ta bort
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChannel(@PathVariable Long id){

        if (channelService.searchById(id).isEmpty()){
            return ResponseEntity.status(404).body("Channel not found");
        } else {
            messageService.deleteByChannelId(id);
            channelService.deleteById(id);
            return ResponseEntity.status(200).body("Deleted channel " + id);
        }
    }

    //Parametern Id representerar id på kanalen man vill uppdatera titeln på
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateChannelTitle(@PathVariable Long id, @RequestBody Channel channel) {
        if (!channelService.updateChannelTitle(id, channel)){
            return ResponseEntity.status(404).body("Could not find channel " + id);
        } else if (channel.getTitle() == null) {
            return ResponseEntity.status(400).body("Check correct title input");
        } else if (channel.getTitle().isEmpty()) {
            return ResponseEntity.status(400).body("Title can not be empty");
        } else {
            return ResponseEntity.status(201).body("Updated channel title");
        }
    }

    //Parametern Id representerar id på kanalen man vill lägga till meddelande i
    @PutMapping("/{id}")
    public ResponseEntity<String> addMessageToChannel(@PathVariable Long id, @RequestBody Message message){

            Optional<Channel> currentChannel = channelService.searchById(id);
            if (currentChannel.isEmpty()){
                return ResponseEntity.status(404).body("Could not find channel " + id);
            } else if (message.getContent() == null) {
                return ResponseEntity.status(404).body("Check correct content input");
            } else if (message.getContent().isEmpty()) {
                return ResponseEntity.status(400).body("Message can not be empty");
            } else {
                message.setChannel(currentChannel.get());
                messageService.addMessage(message);
                return ResponseEntity.status(201).body("Message created in channel " + id);
            }
    }

    //Parametern Id representerar id på kanalen man vill hämta meddelanden från
    @GetMapping("/{id}")
    public ResponseEntity<String> getAllMessages(@PathVariable Long id){

        if (channelService.searchById(id).isEmpty()){
            return ResponseEntity.status(404).body("Channel " + id + " not found");
        } else if (messageService.findContentByChannelId(id).isEmpty()){
            return ResponseEntity.status(204).build();
        } else {
            List<String> messages = messageService.findContentByChannelId(id);
            String messagesString = String.join("\n", messages);
            return ResponseEntity.status(200).body("Messages in channel, " + id + ": \n" + "ID |  Message  \n" + messagesString);
        }

    }
}
