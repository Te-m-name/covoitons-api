package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.ConversationDto;
import com.example.covoitonsapi.dto.MessageDto;
import com.example.covoitonsapi.entity.MessageEntity;

import java.io.IOException;
import java.util.List;

public interface IMessagingService {

    MessageDto toDto(MessageEntity entity);
    List<MessageDto> conversation(Integer id) throws IOException;

    MessageDto send(MessageDto dto);

}
