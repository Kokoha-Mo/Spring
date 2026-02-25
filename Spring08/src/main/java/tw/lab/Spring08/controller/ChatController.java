package tw.lab.Spring08.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import tw.lab.Spring08.response.ChatMessage;

@Controller
public class ChatController {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    @MessageMapping("/chat/send")
    @SendTo("/topic/public")
    public ChatMessage handleMessage(String content, Principal principal) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setEmail(principal.getName());
        chatMessage.setContent(content);
        chatMessage.setTime(LocalDateTime.now().format(dtf));
        return chatMessage;
    }
}
