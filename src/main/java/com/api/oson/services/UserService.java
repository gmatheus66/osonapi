package com.api.oson.services;

import com.api.oson.model.User;
import com.api.oson.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public  void processOAuthPostLogin(String username){
        User user = userRepository.getByUsername(username);
        if(user == null){
            User user1 = new User(username);
            userRepository.save(user1);
        }
    }

}
