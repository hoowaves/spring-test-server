package com.test.userservice.users.interfaces.dto;

import com.test.userservice.common.exception.BaseException;
import com.test.userservice.users.domain.command.RegisterCmd;
import com.test.userservice.users.domain.info.RegisterInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

public class RegisterDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterRequest {

        @Schema(description = "회원 이름", example = "test1")
        @Size(min = 1, max = 12, message = "이름은 1자리 이상 12자리 이하로 입력해주세요.")
        @NotBlank(message = "이름은 공백이 들어갈 수 없습니다.")
        private String name;

        @Schema(description = "회원 이메일", example = "test1@test1.com")
        @Email(message = "올바른 메일을 입력해주세요.")
        @NotBlank(message = "이메일은 공백이 들어갈 수 없습니다.")
        private String email;

        @Schema(description = "회원 비밀번호", example = "123456s")
        @Pattern(regexp = "^[A-Za-z0-9]{5,10}$", message = "알파벳 대소문자, 숫자로 이루어진 5~10크기 이내의 비밀번호여야 합니다.")
        @NotBlank(message = "비밀번호에 공백은 들어갈 수 없습니다.")
        private String password;

        @Schema(description = "회원 비밀번호 확인", example = "123456s")
        @NotBlank(message = "비밀번호에 공백은 들어갈 수 없습니다.")
        private String confirmPassword;

        public RegisterCmd toRegisterCmd() {
            if (!password.equals(confirmPassword)) throw new BaseException("비밀번호를 정확히 입력해주세요.", HttpStatus.BAD_REQUEST);

            return RegisterCmd.builder()
                    .name(name)
                    .email(email)
                    .password(password)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class RegisterResponse {
        @Schema(description = "회원 이름", example = "test1")
        private String name;

        @Schema(description = "회원 이메일", example = "test1@test1.com")
        private String email;

        public RegisterResponse(RegisterInfo registerInfo) {
            this.name = registerInfo.getName();
            this.email = registerInfo.getEmail();
        }
    }
}
