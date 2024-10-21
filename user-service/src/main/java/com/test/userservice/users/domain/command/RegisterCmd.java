package com.test.userservice.users.domain.command;

import com.test.userservice.users.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RegisterCmd {

    private String name;
    private String email;
    private String password;

    public User toEntity(String encodedPassword){
        return User.builder()
                .name(name)
                .email(email)
                .password(encodedPassword)
                .build();
    }
}
