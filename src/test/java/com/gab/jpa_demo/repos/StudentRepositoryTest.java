package com.gab.jpa_demo.repos;

import com.gab.jpa_demo.entity.Guardian;
import com.gab.jpa_demo.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .firstName("Gabriel")
                .lastName("Gitonga")
                .emailId("gabworks51@gmail.com")
                //.guardianName("gabGuardianFN")
               // .guardianEmail("gabguardian@gmail.com")
                //.guardianMobile("999999999")
                .mobileNumber("9989808899")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printStudentList(){
        List<Student> students = studentRepository.findAll();
        System.out.println("Students => "+ students.get(0));
    }

    @Test
    public void printStudentListByEmail(){
        List<Student> students = studentRepository.findByEmailId("gabworks51@gmail.com");
        System.out.println("Students => "+ students.get(0));
    }


    @Test
    public void printStudentListByNameContaining(){
        List<Student> students = studentRepository.findAllByFirstNameContaining("ie");
        System.out.println("Students => "+ students);
    }

    @Test
    public void printStudentListByGuardianName(){
        List<Student> students = studentRepository.findAllByGuardianName("gabGuardianFN");
        System.out.println("Students => "+ students);
    }

    @Test
    public void printStudentListByEmailAddress(){
        Student students = studentRepository.getStudentByEmailAddress("gabworks51@gmail.com");
        System.out.println("Students => "+ students);
    }

    @Test
    public void getStudentFirstNameByEmailAddress(){
        String students = studentRepository.getStudentFirstNameByEmailAddress("gabworks51@gmail.com");
        System.out.println("Students => "+ students);
    }

    @Test
    public void getStudentByEmailAddressNative(){
        Student students = studentRepository.getStudentByEmailAddressNative("gabworks51@gmail.com");
        System.out.println("Students => "+ students);
    }

    @Test
    public void getStudentByEmailAddressNativeNamedParam(){
        Student students = studentRepository.getStudentByEmailAddressNativeNamedParam("gabworks51@gmail.com");
        System.out.println("Students => "+ students);
    }

    @Test
    public void getStudentByEmailAddressAndFirstnameNativeNamedParam(){
        Student students = studentRepository.getStudentByEmailAddressAndFirstnameNativeNamedParam("gabworks51@gmail.com", "Gabriel");
        System.out.println("Students => "+ students);
    }

    @Test
    public void updateStudentNameByEmailId(){
        int students = studentRepository.updateStudentNameByEmailId("GabrielUpdated","gabworks5122@gmail.com");
        System.out.println("Students => "+ students);
    }

    @Test
    public void updateStudentNameByEmail(){
        Student student = studentRepository.findFirstByEmailId("gabworks5122@gmail.com");
        student.firstName = "KingsUpdated";
        studentRepository.save(student);
        System.out.println("Students => "+ student);
    }

    @Test
    public void saveStudentWithGuardian(){

        Guardian guardian = Guardian.builder()
                .email("gabguardian@gmail.com")
                .name("gabGuardianFN")
                .mobile("999999999")
                .build();
        Student student = Student.builder()
                .firstName("Gabriel")
                .lastName("Gitonga")
                .emailId("gabworks5122@gmail.com")
                .mobileNumber("9989808899")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }



}
