package com.example.usersapi.service;

import com.example.usersapi.model.UserProfile;

public interface UserProfileService {
    public UserProfile createProfile(Long userId, UserProfile userProfile);
//    public UserProfile createProfile(String userId, Long userProfile);

}
