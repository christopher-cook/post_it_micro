package com.example.usersapi.service;

import com.example.usersapi.model.JwtResponse;
import com.example.usersapi.model.User;
//import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService //extends UserDetailsService
{
  public Iterable<User> listUsers();

  public JwtResponse createUser(User user);
  public JwtResponse login(User user);
  public User getUserByUsername(String username);
}
