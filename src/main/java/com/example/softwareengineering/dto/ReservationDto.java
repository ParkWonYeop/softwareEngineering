package com.example.softwareengineering.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * 예약 시스템에서 사용되는 Data Transfer Object(DTO) 클래스입니다.
 * 이 클래스는 객실 예약과 관련된 정보를 포함합니다.
 */
@Data // Lombok을 사용하여 getter, setter, toString 등을 자동으로 생성
@Setter
@Getter
public class ReservationDto {
    @Pattern(regexp = "[0-9]{1,3}")
    @NotBlank
    Long roomId; // 예약하고자 하는 객실의 고유 ID
    @Pattern(regexp = "[0-9]{10,12}", message = "전화번호 형식을 맞춰주세요.")
    @NotBlank
    String phonenumber; // 예약을 진행하는 학생의 고유 ID
    @Future
    LocalDateTime startDateTime; // 예약 시작 시간
    @Future
    LocalDateTime endDateTime; // 예약 종료 시간
}

