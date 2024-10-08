package com.example.phoneduck.service;

import com.example.phoneduck.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Test
    void testFindContentByChannelId() {
        //Given
        List<String> expectedContent = Arrays.asList("Hello", "World");
        MessageService messageService = new MessageService();

        MessageRepository messageRepository = mock(MessageRepository.class);
        when(messageRepository.findContentByChannelId(10L)).thenReturn(expectedContent);
        messageService.setMessageRepository(messageRepository);


        //When
        List<String> content = messageService.findContentByChannelId(10L);

        //Then
        assertEquals(expectedContent, content);
        verify(messageRepository).findContentByChannelId(10L);

    }

    @Test
    void searchMessageById() {
    }
}