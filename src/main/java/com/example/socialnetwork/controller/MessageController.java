package com.example.socialnetwork.controller;

import com.example.socialnetwork.model.Message;
import com.example.socialnetwork.model.User;
import com.example.socialnetwork.repository.MessageRepository;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.security.SpringUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    private User user;

    @GetMapping("/message")
    public String messagePage(@RequestParam("id") int id, ModelMap modelMap) {
        List<Message> messages = messageRepository.findAllMessagesById(id);
        messages.addAll(messageRepository.findAllMessagesByFriendId(id));
        user = userRepository.getOne(id);
        modelMap.addAttribute("messages", messages);
        modelMap.addAttribute("toId", user);
        return "message";
    }

    @PostMapping("/message")
    public String message(@ModelAttribute Message message, @AuthenticationPrincipal SpringUser springUser, RedirectAttributes redirectAttributes) {
        message.setUser(springUser.getUser());
        message.setFriend(user);
        message.setDate(new Date());
        messageRepository.save(message);
        redirectAttributes.addAttribute("id",user.getId());
        return "redirect:/message";
    }
}
