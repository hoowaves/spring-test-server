package com.test.userservice.users.domain;

import com.test.userservice.common.exception.BaseException;
import com.test.userservice.users.domain.command.LoginCmd;
import com.test.userservice.users.domain.command.RegisterCmd;
import com.test.userservice.users.domain.info.LoginInfo;
import com.test.userservice.users.domain.info.RegisterInfo;
import com.test.userservice.users.infrastructure.UserRepository;
import com.test.userservice.util.JWTUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public RegisterInfo register(RegisterCmd registerCmd) {
        if (userRepository.existsByName(registerCmd.getName()))
            throw new BaseException("이미 사용 중인 이름입니다.", HttpStatus.UNPROCESSABLE_ENTITY);
        if (userRepository.existsByEmail(registerCmd.getEmail()))
            throw new BaseException("이미 사용 중인 이메일입니다.", HttpStatus.UNPROCESSABLE_ENTITY);
        var userEntity = registerCmd.toEntity(passwordEncoder.encode(registerCmd.getPassword()));
        userRepository.save(userEntity);
        return new RegisterInfo(userEntity);
    }

    @Override
    public LoginInfo login(LoginCmd loginCmd) {
        var userOptional = userRepository.findByEmail(loginCmd.getEmail());
        if (userOptional.isEmpty()) throw new BaseException("아이디 또는 비밀번호를 확인해주세요.", HttpStatus.OK);
        if (!passwordEncoder.matches(loginCmd.getPassword(), userOptional.get().getPassword())) {
            throw new BaseException("아이디 또는 비밀번호를 확인해주세요.", HttpStatus.OK);
        }
        var userEntity = userOptional.get();
        userEntity.setToken(JWTUtil.generate(userEntity));
        userRepository.save(userEntity);
        return new LoginInfo(userEntity);
    }
}
