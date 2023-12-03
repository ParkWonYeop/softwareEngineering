package com.example.softwareengineering.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 예약과 관련된 학생 정보를 전달하기 위한 Data Transfer Object(DTO) 클래스입니다.
 * 이 클래스는 예약 정보와 관련된 학생의 데이터를 전달하는데 사용됩니다.
 */
@Data // Lombok을 사용하여 getter, setter, toString 등을 자동으로 생성
@Setter
@Getter
public class ReservationStudentDto {
    @Pattern(regexp = "[0-9]{10,12}", message = "전화번호 형식을 맞춰주세요.")
    @NotBlank
    String phonenumber; // 예약을 했던 학생의 전화번호
}
