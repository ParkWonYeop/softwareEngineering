package com.example.softwareengineering.admin;

import com.example.softwareengineering.dto.LoginDto;
import com.example.softwareengineering.dto.ReservationDto;
import com.example.softwareengineering.dto.SignupDto;
import com.example.softwareengineering.entities.Reservation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    /**
     * 관리자 가입 요청을 처리합니다.
     *
     * @param signupDto 가입할 관리자의 정보를 담은 DTO.
     * @return 성공적으로 가입 처리가 완료되면 "success" 문자열을 반환합니다.
     * @throws Exception 가입 과정에서 문제가 발생하면 예외를 발생시킵니다.
     */
    @PostMapping("/signup")
    public String signup(@RequestBody @Valid SignupDto signupDto) throws Exception {
        return adminService.signup(signupDto);
    }

    /**
     * 관리자 로그인 요청을 처리합니다.
     *
     * @param loginDto 로그인할 관리자의 정보를 담은 DTO.
     * @return 로그인이 성공하면 "success" 문자열을 반환합니다.
     * @throws Exception 로그인 과정에서 문제가 발생하면 예외를 발생시킵니다.
     */
    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDto loginDto) throws Exception {
        return adminService.login(loginDto);
    }

    /**
     * 예약리스트를 조회합니다.
     *
     * @return 예약 리스트 목록
     */
    @GetMapping("/reservation")
    public List<Reservation> getReservationList() {
        return adminService.getReservationList();
    }

    /**
     * 예약을 삭제합니다.
     *
     * @param reservationDto 삭제할 예약 정보를 담은 데이터 전송 객체
     * @return 예약 삭제 성공 여부를 나타내는 문자열
     * @throws Exception 예약 삭제 과정에서 발생할 수 있는 예외
     */
    @DeleteMapping("/reservation")
    public  String deleteReservation(@RequestBody @Valid ReservationDto reservationDto) throws Exception {
        return adminService.deleteReservation(reservationDto);
    }
}
