package com.gab.jpa_demo.services;


import com.gab.jpa_demo.entity.User;
import com.gab.jpa_demo.exceptions.EtAuthException;
import com.gab.jpa_demo.mapstruct.dtos.UserPostDto;

import java.util.Optional;

public interface UserService {
    User validateUser(String email, String password) throws EtAuthException;


}
