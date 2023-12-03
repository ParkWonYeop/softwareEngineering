package com.example.softwareengineering.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 사용자 로그인 요청 시 사용되는 Data Transfer Object(DTO) 클래스입니다.
 * 사용자의 전화번호와 비밀번호 정보를 포함합니다.
 */
@Data // Lombok을 사용하여 getter, setter, toString 등을 자동으로 생성
@Setter
@Getter
public class LoginDto {
    @Pattern(regexp = "[0-9]{8,12}", message = "전화번호 형식을 맞춰주세요.")
    @NotBlank
    private String phonenumber; // 사용자 전화번호

    @Pattern(regexp="[a-zA-Z1-9]{6,12}", message = "비밀번호는 영어와 숫자로 포함해서 6~12자리 이내로 입력해주세요.")
    @NotBlank
    private String password; // 사용자 비밀번호
}
