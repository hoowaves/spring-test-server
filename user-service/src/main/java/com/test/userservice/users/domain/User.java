package com.test.userservice.users.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 12, message = "이름은 1자리 이상 12자리 이하로 입력해주세요.")
    @NotBlank
    private String name;

    @Email(message = "올바른 메일을 입력해주세요.")
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String role;

    private String createAt;

    private String updateAt;

    private String token;

    @Builder
    public User(String name, String email, String password) {
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMddHHmmss");
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = "ROLE_USER";
        this.createAt = format.format(new Date());
        this.updateAt = format.format(new Date());
        this.token = null;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
