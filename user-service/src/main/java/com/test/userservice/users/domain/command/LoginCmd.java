package com.test.userservice.users.domain.command;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LoginCmd {
    private String email;
    private String password;
}
