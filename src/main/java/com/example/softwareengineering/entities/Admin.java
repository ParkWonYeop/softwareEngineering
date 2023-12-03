package com.example.softwareengineering.entities;

        import jakarta.persistence.*;
        import lombok.*;
        import org.hibernate.annotations.CreationTimestamp;
        import org.hibernate.annotations.UpdateTimestamp;

        import java.time.LocalDateTime;

/**
 * 'Admin' 엔티티를 나타내는 클래스입니다.
 * 이 클래스는 관리자의 데이터를 데이터베이스에 저장하기 위한 엔티티로 사용됩니다.
 */
@Data // Lombok을 사용하여 getter, setter, toString 등을 자동으로 생성
@Setter
@Getter
@NoArgsConstructor // 기본 생성자를 자동으로 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자를 자동으로 생성
@Entity // JPA 엔티티임을 나타냄
public class Admin {
    @Id // 기본 키임을 나타냄
    @GeneratedValue(strategy= GenerationType.IDENTITY) // 기본 키 생성 전략을 IDENTITY로 설정
    private long id;

    @Column(nullable = false, unique=true) // null 불가, 고유 값이어야 함
    private String phonenumber;

    @Column(nullable = false) // null 불가
    private String password;

    @Column(nullable = false) // null 불가
    private String name;

    @CreationTimestamp // 생성 시간 자동 기록
    @Column(nullable = false, updatable = false) // null 불가, 업데이트 불가
    private LocalDateTime createdAt;

    @UpdateTimestamp // 업데이트 시간 자동 기록
    @Column(nullable = false) // null 불가
    private LocalDateTime updatedAt;

    public Admin(String name, String phonenumber, String passwordHash) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.password = passwordHash;
    }
}
