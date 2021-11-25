package com.gab.jpa_demo.repos;

import com.gab.jpa_demo.entity.Course;
import com.gab.jpa_demo.entity.CourseMaterial;
import com.gab.jpa_demo.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void createTeacher(){
       Course course = Course.builder()
               .title("Java")
               .credit(3)
               .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();



        Teacher teacher = Teacher.builder()
                .firstName("Gitonga1")
                .lastName("Gitonga2").build();
               //.courseList(List.of(course)).build();


        teacherRepository.save(teacher);

        System.out.println("Teacher => "+ teacher);
    }

}
