package kr.co.trito.service;

import kr.co.trito.domain.repository.mybatis.login.LoginRepository;
import kr.co.trito.dto.Mybatis.users.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    @Autowired
    private final LoginRepository loginRepository;

    public UserInfoDto getUserInfo(UserInfoDto userInfoDto) {
        UserInfoDto userInfo = loginRepository.getUserInfo(userInfoDto);
        return userInfo;
    }
}

