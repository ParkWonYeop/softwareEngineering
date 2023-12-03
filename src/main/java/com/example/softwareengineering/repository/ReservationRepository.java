package com.example.softwareengineering.repository;

import com.example.softwareengineering.entities.Reservation;
import com.example.softwareengineering.entities.Room;
import com.example.softwareengineering.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 'Reservation' 엔티티에 대한 JPA 리포지토리 인터페이스입니다.
 * 스프링 데이터 JPA의 JpaRepository를 확장하여, 학생 데이터에 대한 기본적인 CRUD 작업과
 * 특정 쿼리 메소드를 제공합니다.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    /**
     * 주어진 방에 대한 예약을 찾는 메소드입니다.
     *
     * @param room 예약을 찾고자 하는 방.
     * @return 해당 방에 대한 예약이 있을 경우, 해당 예약 정보를 담은 Optional 객체를 반환합니다.
     */
    List<Reservation> findReservationByRoom(Room room);

    /**
     * 주어진 학생에 대한 예약을 찾는 메소드입니다.
     *
     * @param student 예약을 한 학생.
     * @return 해당 학생이 대한 예약이 있을 경우, 해당 예약 정보를 담은 리스트를 반환합니다.
     */
    List<Reservation> findReservationsByStudent(Student student);

    /**
     * 특정 학생, 방, 시작 시간, 종료 시간을 기준으로 예약을 찾는 메소드입니다.
     *
     * @param student 예약을 조회할 학생.
     * @param room 예약된 방.
     * @param startDateTime 예약의 시작 시간.
     * @param endDateTime 예약의 종료 시간.
     * @return 해당 조건에 맞는 예약이 있을 경우, 해당 예약 정보를 담은 Optional 객체를 반환합니다.
     */
    Optional<Reservation> findReservationByStudentAndRoomAndStartDateTimeAndEndDateTime(
            Student student,
            Room room,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime
    );
}
