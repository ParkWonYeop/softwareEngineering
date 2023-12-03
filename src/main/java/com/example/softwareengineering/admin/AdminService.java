package com.example.softwareengineering.admin;

import com.example.softwareengineering.dto.LoginDto;
import com.example.softwareengineering.dto.ReservationDto;
import com.example.softwareengineering.dto.SignupDto;
import com.example.softwareengineering.entities.Admin;
import com.example.softwareengineering.entities.Reservation;
import com.example.softwareengineering.entities.Room;
import com.example.softwareengineering.entities.Student;
import com.example.softwareengineering.repository.AdminRepository;
import com.example.softwareengineering.repository.ReservationRepository;
import com.example.softwareengineering.repository.RoomRepository;
import com.example.softwareengineering.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder encoder;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;

    /**
     * 관리자 가입을 처리하는 메서드입니다.
     *
     * @param signupDto 사용자 가입 정보를 담은 Data Transfer Object(DTO).
     * @return 가입 성공 시 "success" 문자열을 반환합니다.
     * @throws Exception 만약 전화번호가 이미 등록되어 있거나, 다른 오류가 발생하면 예외가 발생합니다.
     */
    public String signup(SignupDto signupDto) throws Exception {
        try {
            String phonenumber = signupDto.getPhonenumber();
            String password = signupDto.getPassword();
            String name = signupDto.getName();

            Optional<Admin> phonenumberCheck = adminRepository.findAdminByPhonenumber(phonenumber);

            if(phonenumberCheck.isPresent()) {
                log.error("signup : This phonenumber already signup");
                throw new Exception("This phonenumber already signup");
            }

            String passwordHash = encoder.encode(password);

            Admin admin = new Admin(name, phonenumber, passwordHash);
            adminRepository.save(admin);

            log.info("signup : success");
            return "success";
        } catch (Exception err) {
            log.error(String.valueOf(err));
            throw err;
        }
    }

    /**
     * 관리자 로그인을 처리하는 메서드입니다.
     *
     * @param loginDto 로그인을 시도하는 사용자의 정보를 담은 Data Transfer Object(DTO).
     * @return 로그인이 성공하면 "success" 문자열을 반환합니다.
     * @throws Exception 관리자가 존재하지 않거나, 비밀번호가 일치하지 않을 경우 예외를 발생시킵니다.
     */
    public String login(LoginDto loginDto) throws Exception {
        try {
            Optional<Admin> admin = adminRepository.findAdminByPhonenumber(loginDto.getPhonenumber());

            if(admin.isEmpty()) {
                log.error("login : User Not Found");
                throw new Exception("User Not Found");
            }

            if(encoder.matches(loginDto.getPassword(), admin.get().getPassword())) {
                log.info("login : Success");
                return "success";
            }

            log.error("login : Password is not matched");
            throw new Exception("Password is not matched");
        } catch (Exception err) {
            log.error(String.valueOf(err));
            throw err;
        }
    }

    public List<Reservation> getReservationList() {
        try {
            return reservationRepository.findAll();
        } catch (Exception err) {
            log.error(String.valueOf(err));
            throw err;
        }
    }

    public String deleteReservation(ReservationDto reservationDto) throws Exception {
        try {
            Optional<Student> student = studentRepository.findStudentByPhonenumber(reservationDto.getPhonenumber());
            if(student.isPresent()) {
                Optional<Room> room = roomRepository.findRoomById(reservationDto.getRoomId());
                if(room.isPresent()) {
                    Optional<Reservation> reservation = reservationRepository.findReservationByStudentAndRoomAndStartDateTimeAndEndDateTime(
                            student.get(),
                            room.get(),
                            reservationDto.getStartDateTime(),
                            reservationDto.getEndDateTime()
                    );
                    if(reservation.isPresent()) {
                        reservationRepository.delete(reservation.get());
                        log.info("deleteReservation : success");
                        return "success";
                    }
                    log.error("deleteReservation : Not Found Reservation");
                    throw new Exception("Not Found Reservation");
                }
                log.error("deleteReservation : Not Found Room");
                throw new Exception("Not Found Room");
            }
            log.error("deleteReservation : Not Found Student");
            throw new Exception("Not Found Student");
        } catch (Exception err) {
            log.error(String.valueOf(err));
            throw err;
        }
    }
}
