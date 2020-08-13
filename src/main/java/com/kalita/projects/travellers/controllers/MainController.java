package com.kalita.projects.travellers.controllers;

import com.kalita.projects.travellers.domain.User;
import com.kalita.projects.travellers.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    private final MessageRepo messageRepo;
    @Value("${spring.profiles.active}")
    private String profile;

    public MainController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/")
    public String main(
            Model model,
            @AuthenticationPrincipal User user
    ) {
        Map<Object, Object> data = new HashMap<>();
        if (user != null) {
            data.put("profile", user);
            data.put("messages", messageRepo.findAll());
        }
        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}
