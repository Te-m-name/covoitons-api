package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.ConversationDto;
import com.example.covoitonsapi.entity.ConversationEntity;
import com.example.covoitonsapi.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConversationService implements IConversationService{

    @Autowired
    ConversationRepository conversationRepository;

    public ConversationDto toDto(ConversationEntity e) {
        ConversationDto dto = new ConversationDto();

        dto.setId(e.getId());
        dto.setUser1(e.getUser1());
        dto.setUser2(e.getUser2());

        return dto;
    }
    @Override
    public List<ConversationDto> getLatestMessageOfEachConversation(Integer user) throws IOException{
        List<ConversationEntity> entities = conversationRepository.getLastestMessageOfEachConversation(user);
        return entities.stream().map(e -> toDto(e)).collect(Collectors.toList());
    }

    @Override
    public List<ConversationDto> getAllConv() throws IOException {
        List<ConversationEntity> entities = conversationRepository.findAll();
        return entities.stream().map(e -> toDto(e)).collect(Collectors.toList());
    }

    @Override
    public ConversationDto getByUser1(Integer id) {
        return toDto(conversationRepository.findByUser1(id));
    }
}
