package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.ConversationDto;
import com.example.covoitonsapi.dto.MessageDto;
import com.example.covoitonsapi.entity.ConversationEntity;
import com.example.covoitonsapi.entity.MessageEntity;
import com.example.covoitonsapi.repository.ConversationRepository;
import com.example.covoitonsapi.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MessageService implements IMessagingService{

    @Autowired
    private MessageRepository messageRepository;
    private ConversationRepository conversationRepository;

    @Override
    public MessageDto toDto(MessageEntity entity) {
        MessageDto dto = new MessageDto();

        dto.setId(entity.getId());
        dto.setConversationID(entity.getConversationID());
        dto.setSender(entity.getSender());
        dto.setContent(entity.getContent());
        dto.setTime(entity.getTime());

        return dto;
    }

    @Override
    public MessageDto send(MessageDto dto) {
        MessageEntity entity = new MessageEntity();

        entity.setSender(dto.getSender());
        entity.setConversationID(dto.getConversationID());
        entity.setContent(dto.getContent());
        entity.setTime(LocalDateTime.now());

        dto.setId(entity.getId());
        System.out.println(dto.getId());
        System.out.println(dto.getConversationID());
        dto.setTime(entity.getTime());

        entity = messageRepository.saveAndFlush(entity);

        return dto;
    }


    @Override
    public List<MessageDto> conversation(Integer id_conversation) throws IOException {
        //List<MessageEntity> entities = messageRepository.getConversation(sender, recipient);
        List<MessageEntity> entities = messageRepository.findAllByconversationID(id_conversation);
        return entities.stream().map(e -> toDto(e)).collect(Collectors.toList());
    }

}
