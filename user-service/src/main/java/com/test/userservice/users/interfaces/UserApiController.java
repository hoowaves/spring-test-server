package com.test.userservice.users.interfaces;

import com.test.userservice.common.response.CommonResponse;
import com.test.userservice.users.domain.UserService;
import com.test.userservice.users.interfaces.dto.LoginDto;
import com.test.userservice.users.interfaces.dto.RegisterDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@OpenAPIDefinition(info = @Info(title = "회원가입 API", description = "회원가입 API", version = "v1"))
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {
    private final UserService userService;

    @Operation(summary = "회원가입 요청", description = "회원가입 요청을 진행할 수 있다.", tags = {"register"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "422", description = "Api Exception")
    })
    @PostMapping("/register")
    public CommonResponse<RegisterDto.RegisterResponse> register(@RequestBody @Valid RegisterDto.RegisterRequest request) {
        var registerCmd = request.toRegisterCmd();
        var registerInfo = userService.register(registerCmd);
        var response = new RegisterDto.RegisterResponse(registerInfo);
        return CommonResponse.success(response, "회원가입이 완료되었습니다.");
    }

    @Operation(summary = "로그인 요청", description = "로그인 요청을 진행할 수 있다.", tags = {"login"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "422", description = "Api Exception")
    })
    @PostMapping("/login")
    public CommonResponse<LoginDto.LoginResponse> login(@RequestBody @Valid LoginDto.LoginRequest request) {
        var loginCmd = request.toLoginCmd();
        var loginInfo = userService.login(loginCmd);
        var response = new LoginDto.LoginResponse(loginInfo);
        return CommonResponse.success(response);
    }

}