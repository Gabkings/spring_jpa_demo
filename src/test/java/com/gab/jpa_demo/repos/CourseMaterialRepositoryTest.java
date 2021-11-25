package com.gab.jpa_demo.repos;

import com.gab.jpa_demo.entity.Course;
import com.gab.jpa_demo.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    CourseMaterialRepository courseMaterialRepository;

    @Test
    public void createCourseMaterial(){
        Course course = Course.builder()
                .credit(4)
                .title("Advanced Statics")
                .build();
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .course(course)
                .url("tests_url1")
                .build();

        courseMaterialRepository.save(courseMaterial);
        System.out.println(courseMaterial);
    }

    @Test
    public void fetchCourseMaterial(){
       List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();

       System.out.println("courses Materials => "+ courseMaterials);
    }
}
