package com.example.covoitonsapi.controller;

import com.example.covoitonsapi.dto.ConversationDto;
import com.example.covoitonsapi.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://covoitons-client-team-name4.vercel.app"})
@RestController
@RequestMapping("conversation")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @GetMapping("allConv/{user}")
    public ResponseEntity<List<ConversationDto>> getAllConversations(@PathVariable Integer user) {
        try {
            return new ResponseEntity(conversationService.getLatestMessageOfEachConversation(user), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
