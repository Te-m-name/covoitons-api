package com.example.covoitonsapi.controller;


import com.example.covoitonsapi.dto.ConversationDto;
import com.example.covoitonsapi.dto.MessageDto;
import com.example.covoitonsapi.service.ConversationService;
import com.example.covoitonsapi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://covoitons-client-team-name4.vercel.app"})
@RestController
@RequestMapping("messages")
public class MessageController {

    @Autowired
    private MessageService messagingService;
    @Autowired
    private ConversationService conversationService;

    @GetMapping("conv/{id}/")
    public ResponseEntity<List<MessageDto>> conversation(@PathVariable String id) {

        Integer ID = Integer.parseInt(id);

        try {
            List<MessageDto> dto = messagingService.conversation(ID);
            return new ResponseEntity(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("send")
    public ResponseEntity<MessageDto> send(@RequestBody MessageDto dto) {
        try {
            dto = messagingService.send(dto);
            return new ResponseEntity(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("allConv/{user}")
    public ResponseEntity<List<ConversationDto>> getAllConversations(@PathVariable Integer user) {
        try {
            System.out.println("poueeeet");
//            List<ConversationDto> dtos = ;
            System.out.println("pouet");
            return new ResponseEntity(conversationService.getAllConv(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

