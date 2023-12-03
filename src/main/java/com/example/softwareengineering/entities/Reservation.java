package com.example.softwareengineering.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * 'Reservation' 엔티티를 나타내는 클래스입니다.
 * 이 클래스는 학생들의 방 예약 정보를 데이터베이스에 저장하기 위한 엔티티로 사용됩니다.
 */
@Data // Lombok을 사용하여 getter, setter, toString 등을 자동으로 생성
@Setter
@Getter
@NoArgsConstructor // 기본 생성자를 자동으로 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자를 자동으로 생성
@Entity // JPA 엔티티임을 나타냄
public class Reservation {
    @Id // 기본 키임을 나타냄
    @GeneratedValue(strategy= GenerationType.IDENTITY) // 기본 키 생성 전략을 IDENTITY로 설정
    private long id;

    @ManyToOne // 다대일 관계를 나타냄
    @JoinColumn(name="student_id") // 연결된 테이블의 컬럼명 지정
    private Student student; // 예약을 한 학생

    @ManyToOne // 다대일 관계를 나타냄
    @JoinColumn(name="room_id") // 연결된 테이블의 컬럼명 지정
    private Room room; // 예약된 방

    @Column(nullable = false) // null 불가
    private LocalDateTime startDateTime; // 예약 시작 시간

    @Column(nullable = false) // null 불가
    private LocalDateTime endDateTime; // 예약 종료 시간

    /**
     * 예약 객체를 생성하는 생성자입니다.
     *
     * @param student 이 예약과 연관된 학생 객체.
     * @param room 이 예약과 연관된 방 객체.
     * @param startDateTime 예약의 시작 시간. 이는 LocalDateTime 객체로 제공됩니다.
     * @param endDateTime 예약의 종료 시간. 이 역시 LocalDateTime 객체로 제공됩니다.
     */
    public Reservation(Student student, Room room, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.student = student;
        this.room = room;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
