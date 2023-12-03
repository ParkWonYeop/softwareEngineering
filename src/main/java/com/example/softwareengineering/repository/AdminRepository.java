package com.example.softwareengineering.repository;

import com.example.softwareengineering.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 'Admin' 엔티티에 대한 JPA 리포지토리 인터페이스입니다.
 * 스프링 데이터 JPA의 JpaRepository를 확장하여, 학생 데이터에 대한 기본적인 CRUD 작업과
 * 특정 쿼리 메소드를 제공합니다.
 */
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findAdminByPhonenumber(String phoneNumber);
}
