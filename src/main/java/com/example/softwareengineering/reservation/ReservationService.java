package com.example.softwareengineering.reservation;

import com.example.softwareengineering.dto.ReservationDto;
import com.example.softwareengineering.dto.ReservationStudentDto;
import com.example.softwareengineering.entities.Reservation;
import com.example.softwareengineering.entities.Room;
import com.example.softwareengineering.entities.Student;
import com.example.softwareengineering.repository.ReservationRepository;
import com.example.softwareengineering.repository.RoomRepository;
import com.example.softwareengineering.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 예약 관련 서비스를 제공하는 클래스.
 * 특정 방 유형에 대한 예약 조회, 예약 추가, 예약 삭제, 특정 학생의 예약 조회 등의 기능을 포함한다.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;

    /**
     * 특정 방 유형에 해당하는 예약을 조회합니다.
     *
     * @param roomType 방 유형
     * @return 해당 방 유형의 예약 목록
     */
    public List<Reservation> getReservation(String roomType) {
        try {
            List<Room> roomList = roomRepository.findRoomsByRoomType(roomType);
            List<Reservation> reservationList = new ArrayList<>();
            for(Room room:roomList) {
                List<Reservation> reservation = reservationRepository.findReservationByRoom(room);
                reservationList.addAll(reservation);
            }

            log.info("getReservation : Success");
            return reservationList;
        } catch(Exception err) {
            log.error(String.valueOf(err));
            throw err;
        }
    }

    /**
     * 새로운 예약을 추가합니다.
     *
     * @param reservationDto 예약 정보를 담은 데이터 전송 객체
     * @return 예약 추가 성공 여부를 나타내는 문자열
     * @throws Exception 예약 추가 과정에서 발생할 수 있는 예외
     */
    public String addReservation(ReservationDto reservationDto) throws Exception {
        try {
            Optional<Student> checkStudent = studentRepository.findStudentByPhonenumber(reservationDto.getPhonenumber());
            if(checkStudent.isPresent()) {
                Optional<Room> checkRoom = roomRepository.findRoomById(reservationDto.getRoomId());
                if(checkRoom.isPresent()) {
                    Optional<Reservation> checkReservation = reservationRepository.findReservationByStudentAndRoomAndStartDateTimeAndEndDateTime(
                            checkStudent.get(),
                            checkRoom.get(),
                            reservationDto.getStartDateTime(),
                            reservationDto.getEndDateTime()
                    );
                    if(checkReservation.isEmpty()) {
                        Reservation reservation = new Reservation(
                                checkStudent.get(),
                                checkRoom.get(),
                                reservationDto.getStartDateTime(),
                                reservationDto.getEndDateTime()
                        );
                        reservationRepository.save(reservation);
                        log.info("addReservation : Success");
                        return "success";
                    }
                    log.error("addReservation : Already Reservation");
                    throw new Exception("Already Reservation");
                }
                log.error("addReservation : Not Found Room");
                throw new Exception("Not Found Room");
            }
            log.error("addReservation : Not Found Student");
            throw new Exception("Not Found Student");
        } catch(Exception err) {
            log.error(String.valueOf(err));
            throw err;
        }
    }

    /**
     * 예약을 삭제합니다.
     *
     * @param reservationDto 삭제할 예약 정보를 담은 데이터 전송 객체
     * @return 예약 삭제 성공 여부를 나타내는 문자열
     * @throws Exception 예약 삭제 과정에서 발생할 수 있는 예외
     */
    public String deleteStudent(ReservationDto reservationDto) throws Exception {
        try {
            Optional<Student> checkStudent = studentRepository.findStudentByPhonenumber(reservationDto.getPhonenumber());
            if(checkStudent.isPresent()) {
                Optional<Room> checkRoom = roomRepository.findRoomById(reservationDto.getRoomId());
                if(checkRoom.isPresent()) {
                    Optional<Reservation> reservation = reservationRepository.findReservationByStudentAndRoomAndStartDateTimeAndEndDateTime(
                            checkStudent.get(),
                            checkRoom.get(),
                            reservationDto.getStartDateTime(),
                            reservationDto.getEndDateTime()
                    );
                    if(reservation.isPresent()) {
                        reservationRepository.delete(reservation.get());
                        log.info("deleteReservation : Success");
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
        } catch(Exception err) {
            log.error(String.valueOf(err));
            throw err;
        }
    }

    /**
     * 특정 학생의 모든 예약을 조회합니다.
     *
     * @param reservationStudentDto 학생 정보를 담은 데이터 전송 객체
     * @return 해당 학생의 예약 목록
     * @throws Exception 학생 예약 조회 과정에서 발생할 수 있는 예외
     */
    public List<Reservation> getStudentReservation(ReservationStudentDto reservationStudentDto) throws Exception {
        try {
            Optional<Student> student = studentRepository.findStudentByPhonenumber(reservationStudentDto.getPhonenumber());
            if(student.isPresent()) {
                List<Reservation> reservationList = reservationRepository.findReservationsByStudent(student.get());

                log.info("getStudentReservation : Success");
                return reservationList;
            }

            log.error("getStudentReservation : Not Found Student");
            throw new Exception("Not Found Student");
        } catch (Exception err) {
            log.error(String.valueOf(err));
            throw err;
        }
    }
}
