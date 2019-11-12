package com.example.usersapi.service;

import com.example.usersapi.config.JwtUtil;
import com.example.usersapi.model.JwtResponse;
import com.example.usersapi.model.User;
import com.example.usersapi.model.UserRole;
import com.example.usersapi.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserRoleService userRoleService;

  @Autowired
  @Qualifier("encoder")
  PasswordEncoder bCryptPasswordEncoder;

  @Autowired
  JwtUtil jwtUtil;

  @Override
  public Iterable<User> listUsers() {
    return userRepository.findAll();
  }

  @Override
  public JwtResponse createUser(User user) {
//  TODO:  uncomment this to add user roles later and in the user entity

//    UserRole userRole = userRoleService.getRole(user.getUserRole().getName());;
//    user.setUserRole(userRole);
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    User savedUser = userRepository.save(user);
    if (savedUser != null) {
      UserDetails userDetails = loadUserByUsername(savedUser.getUsername());
      String token = jwtUtil.generateToken(userDetails);
      return new JwtResponse(token, savedUser.getUsername());
    }
    return null;
  }

  @Override
  public JwtResponse login(User user) {
    User foundUser = userRepository.login(user.getEmail());
    if (foundUser != null && bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
      UserDetails userDetails = loadUserByUsername(foundUser.getUsername());
      String token = jwtUtil.generateToken(userDetails);
      return new JwtResponse(token, foundUser.getUsername());
    }
    return null;
  }

  @Override
  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = getUserByUsername(username);

    if(user==null)
      throw new UsernameNotFoundException("User null");

    return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
        true, true, true, true, new ArrayList<>());
  }

  private List<GrantedAuthority> getGrantedAuthorities(User user){
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//    TODO : add this back in for user roles
//    authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));

    return authorities;
  }
}
