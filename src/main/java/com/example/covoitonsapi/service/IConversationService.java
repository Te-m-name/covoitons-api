package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.ConversationDto;
import com.example.covoitonsapi.entity.ConversationEntity;

import java.io.IOException;
import java.util.List;

public interface IConversationService {

    ConversationDto toDto(ConversationEntity entity);
    List<ConversationDto> getLatestMessageOfEachConversation(Integer user) throws IOException;

    List<ConversationDto> getAllConv() throws IOException;

    ConversationDto getByUser1(Integer id);

}
