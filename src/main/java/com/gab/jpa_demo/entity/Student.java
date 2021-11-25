package com.gab.jpa_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "tbl_student",
    uniqueConstraints = @UniqueConstraint(
            name = "emailid_unique",
            columnNames = "student_email"
    )
)
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_generator",
            sequenceName = "student_generator",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_generator"
    )
    public Long studentId;
    public String firstName;
    public String lastName;
    @Column(name = "student_email", nullable = false)
    public String emailId;
    public String mobileNumber;
//    public String guardianName;
//    public String guardianEmail;
//    public String guardianMobile;

    @Embedded
    private Guardian guardian;
}
