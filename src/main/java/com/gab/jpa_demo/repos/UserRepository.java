package com.gab.jpa_demo.repos;

import com.gab.jpa_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT count(s.email) FROM user s where s.email = :emailId",
            nativeQuery = true
    )
    int findEmailCount(@Param("emailId") String emailId);

    User findByEmailAndPassword(String email, String password);


    @Modifying
    @Transactional
    @Query(
            value = "update user set first_name = ?1, last_name = ?2 where user_id = ?3",
            nativeQuery = true
    )
    int updateUserById(String firstName, String lastName, Long userId);


    //JPQL
    @Transactional
    @Modifying
    @Query("update User s set s.firstName =?1, s.lastName = ?2 where s.userId = ?3")
    int updateUserByIdJPQ(String firstName, String lastName, Long userId);
}
