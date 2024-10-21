package com.test.userservice.users.domain;

import com.test.userservice.users.domain.command.LoginCmd;
import com.test.userservice.users.domain.command.RegisterCmd;
import com.test.userservice.users.domain.info.LoginInfo;
import com.test.userservice.users.domain.info.RegisterInfo;

public interface UserService {
    RegisterInfo register(RegisterCmd registerCmd);

    LoginInfo login(LoginCmd loginCmd);
}
