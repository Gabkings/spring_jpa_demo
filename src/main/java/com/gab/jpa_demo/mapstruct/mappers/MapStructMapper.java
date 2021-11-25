package com.gab.jpa_demo.mapstruct.mappers;

import com.gab.jpa_demo.entity.Employee;
import com.gab.jpa_demo.mapstruct.dtos.*;
import com.gab.jpa_demo.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {

    EmployeeRequestVO employeeDto(
           Employee employee
    );




    User userPostDtoToUser(
            UserPostDto userPostDto
    );


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserPostDto dto, @MappingTarget User entity);

    UserGetDto userToUserGetDto(
            User user
    );

    User userGetDtoToUser(
            UserGetDto userGetDto
    );

    List<UserGetDto> userToUserGetDtos(
            List<User> userList
    );

}
