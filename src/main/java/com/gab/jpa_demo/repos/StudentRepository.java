package com.gab.jpa_demo.repos;

import com.gab.jpa_demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
     List<Student> findByEmailId(String emailId);
     Student findFirstByEmailId(String emailId);

     List<Student> findAllByFirstNameContaining(String firstName);
     List<Student> findAllByGuardianName(String guardian_name);

     //JPQL
     @Query("select s from Student s where s.emailId = ?1")
     Student getStudentByEmailAddress(String emailId);


     //JPQL
     @Query("select s.firstName from Student s where s.emailId = ?1")
     String getStudentFirstNameByEmailAddress(String emailId);

     //Native
     @Query(
             value = "SELECT * FROM tbl_student s where s.student_email = ?1",
             nativeQuery = true
     )
     Student getStudentByEmailAddressNative(String emailId);

     //Native Named Param
     @Query(
             value = "SELECT * FROM tbl_student s where s.student_email = :emailId",
             nativeQuery = true
     )
     Student getStudentByEmailAddressNativeNamedParam(
             @Param("emailId") String emailId
     );


     //Native Named Param
     @Query(
             value = "SELECT * FROM tbl_student s where s.student_email = :emailId and s.first_name = :first_name",
             nativeQuery = true
     )
     Student getStudentByEmailAddressAndFirstnameNativeNamedParam(
             @Param("emailId") String emailId,
             @Param("first_name") String firstName

     );

     @Modifying
     @Transactional
     @Query(
             value = "update tbl_student set first_name = ?1 where student_email = ?2",
             nativeQuery = true
     )
     int updateStudentNameByEmailId(String firstName, String emailId);
}
