package com.example.phoneduck.repository;

import com.example.phoneduck.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

    Channel getChannelById(Long id);

}
