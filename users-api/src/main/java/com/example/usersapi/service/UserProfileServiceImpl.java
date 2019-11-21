package com.example.usersapi.service;

import com.example.usersapi.model.User;
import com.example.usersapi.model.UserProfile;
import com.example.usersapi.repository.UserProfileRepository;
import com.example.usersapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public UserProfile createProfile(String userId, UserProfile userProfile) {
        User currentUser = userRepository.findById(Long.getLong(userId)).get();
        UserProfile savedProfile = userProfileRepository.save(userProfile);
        currentUser.setUserProfile(savedProfile);
        userRepository.save(currentUser);
        return userProfile;

    }
}
