package com.example.socialnetwork.controller;


import com.example.socialnetwork.model.User;
import com.example.socialnetwork.repository.UserRepository;
import com.example.socialnetwork.security.SpringUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {


    @Autowired
    private UserRepository userRepository;


    @GetMapping("/home")
    public String request(@AuthenticationPrincipal
                                  SpringUser springUser, ModelMap map) {
        List<Integer> allFriendRequests = userRepository.findAllFriendRequests(springUser.getUser().getId());
        List<Integer> allFriends = userRepository.findAllFriends(springUser.getUser().getId());
        List<Integer> all = allFriends;
        all.addAll(userRepository.findAllFriendsSecond(springUser.getUser().getId()));
        List<User> allById = userRepository.findAllById(allFriendRequests);
        List<User> allFriend = userRepository.findAllById(all);
        List<User> allUsers = userRepository.findAll();
        allUsers.remove(springUser.getUser());

        allUsers.removeAll(allById);
        allUsers.removeAll(allFriend);


        map.addAttribute("user", springUser.getUser());
        map.addAttribute("users", allUsers);
        map.addAttribute("requests", allById);
        map.addAttribute("friends", allFriend);
        return "home";

    }

    @GetMapping("/remove")
    public String deleteFriend(@RequestParam("id") int id, @AuthenticationPrincipal SpringUser springUser) {
        userRepository.deleteFriendById(id, springUser.getUser().getId());
        userRepository.deleteUserFriendById(id, springUser.getUser().getId());
        return "redirect:/home";
    }

    @GetMapping("/request")
    public String request(@RequestParam("id") int id, @AuthenticationPrincipal SpringUser springUser) {
        User oneUser = userRepository.getOne(id);
        if (oneUser != null) {
            userRepository.saveFriendRequest(springUser.getUser().getId(), oneUser.getId());
        }
        return "redirect:/home";
    }

    @GetMapping("/acceptOrReject")
    public String acceptOrReject(@RequestParam("id") int id, @RequestParam("action") String action, @AuthenticationPrincipal SpringUser springUser) {
        if (action.equals("reject") && userRepository.getOne(id) != null) {
            userRepository.removeRequest(id, springUser.getUser().getId());
        } else if (action.equals("accept") && userRepository.getOne(id) != null) {

            userRepository.addFriend(springUser.getUser().getId(), id);
            userRepository.removeRequest(id, springUser.getUser().getId());
        }
        return "redirect:/home";
    }

}
