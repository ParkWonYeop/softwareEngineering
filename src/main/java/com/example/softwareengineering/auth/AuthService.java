package com.example.softwareengineering.auth;

import com.example.softwareengineering.dto.LoginDto;
import com.example.softwareengineering.dto.SignupDto;
import com.example.softwareengineering.entities.Student;
import com.example.softwareengineering.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

/**
 * 사용자 인증 관련 서비스를 제공하는 클래스입니다.
 * 이 클래스는 사용자 가입 및 로그인과 관련된 비즈니스 로직을 처리합니다.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder encoder;

    /**
     * 사용자 가입을 처리하는 메서드입니다.
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

            Optional<Student> phonenumberCheck = studentRepository.findStudentByPhonenumber(phonenumber);

            if(phonenumberCheck.isPresent()) {
                log.error("signup : This phonenumber already signup");
                throw new Exception("This phonenumber already signup");
            }

            String passwordHash = encoder.encode(password);

            Student student = new Student(name, phonenumber, passwordHash);
            studentRepository.save(student);

            log.info("signup : success");
            return "success";
        } catch (Exception err) {
            log.error(String.valueOf(err));
            throw err;
        }
    }

    /**
     * 사용자 로그인을 처리하는 메서드입니다.
     *
     * @param loginDto 로그인을 시도하는 사용자의 정보를 담은 Data Transfer Object(DTO).
     * @return 로그인이 성공하면 "success" 문자열을 반환합니다.
     * @throws Exception 사용자가 존재하지 않거나, 비밀번호가 일치하지 않을 경우 예외를 발생시킵니다.
     */
    public String login(LoginDto loginDto) throws Exception {
        try {
            Optional<Student> student = studentRepository.findStudentByPhonenumber(loginDto.getPhonenumber());

            if(student.isEmpty()) {
                log.error("login : User Not Found");
                throw new Exception("User Not Found");
            }

            if(encoder.matches(loginDto.getPassword(), student.get().getPassword())) {
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
}
