package com.api.oson.services;

import com.api.oson.config.CustomOAuth2User;
import com.api.oson.model.User;
import com.api.oson.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public  void processOAuthPostLogin(CustomOAuth2User oAuth2User){
        User user = userRepository.getByUsername(oAuth2User.getName());
        if(user == null){
            User user1 = new User(oAuth2User);
            userRepository.save(user1);
        }
    }

}
