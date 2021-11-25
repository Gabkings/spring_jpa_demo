package com.gab.jpa_demo.services;

import com.gab.jpa_demo.entity.User;
import com.gab.jpa_demo.exceptions.EtAuthException;
import com.gab.jpa_demo.mapstruct.mappers.MapStructMapper;
import com.gab.jpa_demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    MapStructMapper mapStructMapper;

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        if(email != null) email = email.toLowerCase();
        return userRepository.findByEmailAndPassword(email, password);
    }





}
