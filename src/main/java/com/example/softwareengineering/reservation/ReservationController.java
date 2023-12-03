package com.example.softwareengineering.reservation;

import com.example.softwareengineering.dto.ReservationDto;
import com.example.softwareengineering.dto.ReservationStudentDto;
import com.example.softwareengineering.entities.Reservation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 예약 관련 요청을 처리하는 컨트롤러 클래스입니다.
 * '/reservation' 경로로 시작하는 HTTP 요청을 이 클래스에서 처리합니다.
 */
@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    /**
     * 특정 방 유형에 해당하는 예약을 조회합니다.
     *
     * @param roomType 방 유형
     * @return 해당 방 유형의 예약 목록
     */
    @GetMapping()
    public List<Reservation> getReservation(@RequestParam(name="roomType") String roomType){
        return reservationService.getReservation(roomType);
    }

    /**
     * 새로운 예약을 추가합니다.
     *
     * @param reservationDto 예약 정보를 담은 데이터 전송 객체
     * @return 예약 추가 성공 여부를 나타내는 문자열
     * @throws Exception 예약 추가 과정에서 발생할 수 있는 예외
     */
    @PostMapping()
    public String addReservation(@RequestBody @Valid ReservationDto reservationDto) throws Exception {
        return reservationService.addReservation(reservationDto);
    }

    /**
     * 예약을 삭제합니다.
     *
     * @param reservationDto 삭제할 예약 정보를 담은 데이터 전송 객체
     * @return 예약 삭제 성공 여부를 나타내는 문자열
     * @throws Exception 예약 삭제 과정에서 발생할 수 있는 예외
     */
    @DeleteMapping()
    public String deleteReservation(@RequestBody @Valid ReservationDto reservationDto) throws Exception {
        return reservationService.deleteStudent(reservationDto);
    }

    /**
     * 특정 학생의 모든 예약을 조회합니다.
     *
     * @param reservationStudentDto 학생 정보를 담은 데이터 전송 객체
     * @return 해당 학생의 예약 목록
     * @throws Exception 학생 예약 조회 과정에서 발생할 수 있는 예외
     */
    @GetMapping("/student")
    public List<Reservation> getStudentReservation(@RequestBody @Valid ReservationStudentDto reservationStudentDto) throws Exception {
        return reservationService.getStudentReservation(reservationStudentDto);
    }
}
