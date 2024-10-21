package com.test.userservice.users.interfaces.dto;

import com.test.userservice.users.domain.command.LoginCmd;
import com.test.userservice.users.domain.info.LoginInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class LoginDto {

    @Getter
    @Setter
    @ToString
    public static class LoginRequest {

        @Schema(description = "회원 이메일", example = "test1@test1.com")
        @Email(message = "올바른 메일을 입력해주세요.")
        @NotBlank(message = "이메일은 공백이 들어갈 수 없습니다.")
        private String email;

        @Schema(description = "회원 비밀번호", example = "123456s")
        @NotBlank(message = "비밀번호에 공백은 들어갈 수 없습니다.")
        private String password;

        public LoginCmd toLoginCmd() {
            return LoginCmd.builder()
                    .email(email)
                    .password(password)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class LoginResponse {

        @Schema(description = "회원 이름", example = "test1")
        private String name;

        @Schema(description = "회원 토큰", example = "...")
        private String token;

        public LoginResponse(LoginInfo loginInfo) {
            this.name = loginInfo.getName();
            this.token = loginInfo.getToken();
        }
    }
}
