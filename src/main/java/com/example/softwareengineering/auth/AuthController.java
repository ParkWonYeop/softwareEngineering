package com.example.softwareengineering.auth;

import com.example.softwareengineering.dto.LoginDto;
import com.example.softwareengineering.dto.SignupDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증 관련 요청을 처리하는 컨트롤러 클래스입니다.
 * '/auth' 경로로 시작하는 HTTP 요청을 이 클래스에서 처리합니다.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    /**
     * 사용자 가입 요청을 처리합니다.
     *
     * @param signupDto 가입할 사용자의 정보를 담은 DTO.
     * @return 성공적으로 가입 처리가 완료되면 "success" 문자열을 반환합니다.
     * @throws Exception 가입 과정에서 문제가 발생하면 예외를 발생시킵니다.
     */
    @PostMapping("/signup")
    public String signup(@RequestBody @Valid SignupDto signupDto) throws Exception {
        return authService.signup(signupDto);
    }

    /**
     * 사용자 로그인 요청을 처리합니다.
     *
     * @param loginDto 로그인할 사용자의 정보를 담은 DTO.
     * @return 로그인이 성공하면 "success" 문자열을 반환합니다.
     * @throws Exception 로그인 과정에서 문제가 발생하면 예외를 발생시킵니다.
     */
    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDto loginDto) throws Exception {
        return authService.login(loginDto);
    }
}
