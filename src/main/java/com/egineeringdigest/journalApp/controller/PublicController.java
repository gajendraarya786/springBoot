package com.egineeringdigest.journalApp.controller;

import com.egineeringdigest.journalApp.cache.AppCache;
import com.egineeringdigest.journalApp.entity.User;
import com.egineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @GetMapping("/health")
    public String HealthCheck(){
        return "ok";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }
}
