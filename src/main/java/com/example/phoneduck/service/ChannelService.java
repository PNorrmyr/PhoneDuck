package com.example.phoneduck.service;

import com.example.phoneduck.model.Channel;
import com.example.phoneduck.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    public List<Channel> getAll(){
        return channelRepository.findAll();
    }

    public void deleteById(Long id){
        channelRepository.deleteById(id);

    }

    public Optional<Channel> searchById(Long id){
        return channelRepository.findById(id);
    }

    public void addChannel(Channel channel) {
        channelRepository.save(channel);
    }

    public boolean updateChannelTitle(Long id, Channel channel){
        Optional<Channel> optionalChannel = searchById(id);
        if (optionalChannel.isEmpty()){
            return false;
        } else {
            Channel existingChannel = optionalChannel.get();
            existingChannel.setTitle(channel.getTitle());
            channelRepository.save(existingChannel);
        return true;
        }
    }
}
