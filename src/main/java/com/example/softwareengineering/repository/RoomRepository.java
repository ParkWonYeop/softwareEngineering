package com.example.softwareengineering.repository;

import com.example.softwareengineering.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 'Room' 엔티티에 대한 JPA 리포지토리 인터페이스입니다.
 * 스프링 데이터 JPA의 JpaRepository를 확장하여, 학생 데이터에 대한 기본적인 CRUD 작업과
 * 특정 쿼리 메소드를 제공합니다.
 */
public interface RoomRepository extends JpaRepository<Room,Long> {
    /**
     * 특정 방 타입에 해당하는 모든 방들을 찾는 메소드입니다.
     *
     * @param roomType 찾고자 하는 방의 타입.
     * @return 해당 타입의 방들을 포함하는 리스트. 해당 타입의 방이 없는 경우 빈 리스트가 반환될 수 있습니다.
     */
    List<Room> findRoomsByRoomType(String roomType);

    /**
     * 방의 ID를 기반으로 방을 찾는 메소드입니다.
     *
     * @param id 찾고자 하는 방의 ID.
     * @return 해당 ID를 가진 방의 Optional 객체.
     */
    Optional<Room> findRoomById(Long id);
}
