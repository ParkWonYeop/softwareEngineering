package com.example.softwareengineering.room;

import com.example.softwareengineering.entities.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 방 관련 HTTP 요청을 처리하는 컨트롤러.
 * 현재는 전체 방 목록 조회 기능을 제공한다.
 */
@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    /**
     * 전체 방 목록을 조회합니다.
     *
     * @return 전체 방 목록
     */
    @GetMapping()
    public List<Room> getRoomList() {
        return roomService.getRoomList();
    }
}
