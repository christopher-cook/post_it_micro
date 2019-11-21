package com.example.usersapi.controller;

import com.example.usersapi.model.UserProfile;
import com.example.usersapi.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @PostMapping("/profile")
    public UserProfile createUserProfile(@RequestHeader("userId") String userId, @RequestBody UserProfile userProfile){
        return userProfileService.createProfile(userId, userProfile);
    }

}
