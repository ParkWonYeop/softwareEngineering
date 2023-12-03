package com.example.softwareengineering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 스프링 부트 어플리케이션의 메인 클래스입니다.
 * 스프링 부트의 자동 설정을 사용하여 어플리케이션을 구동합니다.
 * 이 클래스는 Spring Security의 자동 구성을 비활성화합니다.
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // Spring Security 자동 구성 제외
public class SoftwareEngineeringApplication {

	/**
	 * 어플리케이션의 메인 메서드입니다.
	 * 스프링 어플리케이션을 구동시킵니다.
	 *
	 * @param args 프로그램 인자.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SoftwareEngineeringApplication.class, args);
	}

}
