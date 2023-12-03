package com.example.softwareengineering.repository;


import com.example.softwareengineering.entities.Room;
import com.example.softwareengineering.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 'Student' 엔티티에 대한 JPA 리포지토리 인터페이스입니다.
 * 스프링 데이터 JPA의 JpaRepository를 확장하여, 학생 데이터에 대한 기본적인 CRUD 작업과
 * 특정 쿼리 메소드를 제공합니다.
 */
public interface StudentRepository extends JpaRepository<Student,Long> {
    /**
     * 전화번호로 학생을 찾는 메소드입니다.
     *
     * @param phonenumber 학생의 전화번호.
     * @return 해당 조건에 맞는 Student 엔티티의 Optional 객체.
     */
    Optional<Student> findStudentByPhonenumber(String phonenumber);

    /**
     * 학생의 ID를 기반으로 학생을 찾는 메소드입니다.
     *
     * @param id 찾고자 하는 학생의 ID.
     * @return 해당 조건에 맞는 Student 엔티티의 Optional 객체.
     */
    Optional<Student> findStudentById(Long id);
}
