package com.test.userservice.users.domain.info;

import com.test.userservice.users.domain.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RegisterInfo {
    private String name;
    private String email;

    public RegisterInfo(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }

}
