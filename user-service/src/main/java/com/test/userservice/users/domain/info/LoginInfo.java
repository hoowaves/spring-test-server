package com.test.userservice.users.domain.info;

import com.test.userservice.users.domain.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginInfo {
    private String name;
    private String token;

    public LoginInfo(User user) {
        this.name = user.getName();
        this.token = user.getToken();
    }
}
