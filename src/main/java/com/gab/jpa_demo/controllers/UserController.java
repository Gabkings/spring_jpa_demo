package com.gab.jpa_demo.controllers;


import com.gab.jpa_demo.entity.User;
import com.gab.jpa_demo.exceptions.EtAuthException;
import com.gab.jpa_demo.exceptions.EtResourceNotFoundException;
import com.gab.jpa_demo.mapstruct.mappers.MapStructMapper;
import com.gab.jpa_demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gab.jpa_demo.mapstruct.dtos.*;

import javax.validation.Valid;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MapStructMapper mapStructMapper;

    @PostMapping("/register")
    public UserGetDto registerUser(@Valid @RequestBody UserPostDto userPostDto)  {
        String email = userPostDto.getEmail();
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new EtAuthException("Invalid email format");
        Integer count = userRepository.findEmailCount(email);
        if(count > 0)
            throw new EtAuthException("Email already in use");
        Long userId = userRepository.save(
                mapStructMapper.userPostDtoToUser(userPostDto)
        ).getUserId().longValue();

        UserGetDto userGetDto = mapStructMapper.userToUserGetDto(
                userRepository.findById(userId).get()
        );
        if(userGetDto == null) throw new EtResourceNotFoundException("User not found");

        return userGetDto ;

    }


    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> getById (
            @PathVariable(value = "id") Long id
    ) {

        UserGetDto userGetDto = mapStructMapper.userToUserGetDto(
                userRepository.findById(id).get()
        );
        if(userGetDto.getUserId() == null) throw  new EtResourceNotFoundException("User not found");

        return new ResponseEntity( userGetDto, HttpStatus.OK) ;
    }


    @GetMapping("/")
    public ResponseEntity<List<UserGetDto>> getAllUsers () {

        List<UserGetDto> userGetDtos = mapStructMapper.userToUserGetDtos(
                userRepository.findAll()
        );
        return new ResponseEntity( userGetDtos, HttpStatus.OK) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserGetDto> update(@PathVariable Long id, @RequestBody UserPostDto userPostDto) {
        UserGetDto userGetDto = mapStructMapper.userToUserGetDto(
                userRepository.findById(id).get()
        );

        User user = mapStructMapper.userGetDtoToUser(userGetDto);
        System.out.println(userPostDto);
        mapStructMapper.updateUserFromDto(userPostDto, user);
        //userPostDto.setUserId(id);
        //userGetDto.setFirstName(userPostDto.getFirstName());
       // userGetDto.setLastName(userPostDto.getLastName() != null ? userPostDto.getLastName(): userGetDto.getLastName() );
        //User user = mapStructMapper.userPostDtoToUser(userPostDto);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userGetDto);
    }

    @PutMapping("/m2/{id}")
    public Object updateM2(@PathVariable Long id, @RequestBody UserPostDto userPostDto) {
        System.out.println(userPostDto);
        int res = userRepository.updateUserById(userPostDto.getFirstName(), userPostDto.getLastName(), id);
        if(res == 1){
            UserGetDto userGetDto = mapStructMapper.userToUserGetDto(
                    userRepository.findById(id).get()
            );
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userGetDto);
        }
        return new EtResourceNotFoundException("No user found");
    }

    @PutMapping("/m3/{id}")
    public Object updateM3(@PathVariable Long id, @RequestBody UserPostDto userPostDto) {
        System.out.println(userPostDto);
        int res = userRepository.updateUserByIdJPQ(userPostDto.getFirstName(), userPostDto.getLastName(), id);
        if(res == 1){
            UserGetDto userGetDto = mapStructMapper.userToUserGetDto(
                    userRepository.findById(id).get()
            );
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userGetDto);
        }
        return new EtResourceNotFoundException("No user found");
    }

}
