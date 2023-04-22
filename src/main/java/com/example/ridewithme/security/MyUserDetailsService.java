package com.example.ridewithme.security;

import com.example.ridewithme.models.User;
import com.example.ridewithme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository  userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      Optional<User> user= userRepository.findUserByEmail(email);

      user.orElseThrow(()->new UsernameNotFoundException("user not found exception"));
      return user.map(MyUserDetail::new).get();
    }
}
