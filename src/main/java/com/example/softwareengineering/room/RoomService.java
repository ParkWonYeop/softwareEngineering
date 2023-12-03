package com.example.softwareengineering.room;

import com.example.softwareengineering.entities.Room;
import com.example.softwareengineering.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 방 관련 정보를 제공하는 서비스 클래스.
 * 현재는 전체 방 목록을 조회하는 기능을 포함한다.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    /**
     * 전체 방 목록을 조회합니다.
     *
     * @return 전체 방 목록
     * @throws Exception 방 목록 조회 과정에서 발생할 수 있는 예외
     */
    public List<Room> getRoomList() {
        try {
            log.info("getRoomList : Success");
            return roomRepository.findAll();
        } catch(Exception err) {
            log.error(String.valueOf(err));
            throw err;
        }
    }
}
