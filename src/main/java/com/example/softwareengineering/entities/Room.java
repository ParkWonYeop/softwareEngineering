package com.example.softwareengineering.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * 'Room' 엔티티를 나타내는 클래스입니다.
 * 이 클래스는 각각의 방(회의실, 강의실 등)에 대한 데이터를 데이터베이스에 저장하기 위한 엔티티로 사용됩니다.
 */
@Data // Lombok을 사용하여 getter, setter, toString 등을 자동으로 생성
@Setter
@Getter
@NoArgsConstructor // 기본 생성자를 자동으로 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자를 자동으로 생성
@Entity // JPA 엔티티임을 나타냄
public class Room {
    @Id // 기본 키임을 나타냄
    @GeneratedValue(strategy= GenerationType.IDENTITY) // 기본 키 생성 전략을 IDENTITY로 설정
    private long id;

    @Column(nullable = false) // null 불가
    private String roomType; // 방의 유형 (예: 회의실, 강의실 등)

    @Column(nullable = false) // null 불가
    private int max_member; // 방의 최대 인원 수
}
