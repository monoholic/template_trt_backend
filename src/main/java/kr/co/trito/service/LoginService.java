package kr.co.trito.service;

import kr.co.trito.domain.repository.Jpa.userInfo.UserInfoRepository;
import kr.co.trito.dto.Jpa.login.LoginDto;
import kr.co.trito.dto.Jpa.login.LoginUserInfoDto;
import kr.co.trito.exception.UserInfoException;
import kr.co.trito.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static kr.co.trito.enums.UserInfoErrorCode.NOT_MATCH_USER_INFO;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserInfoRepository userInfoRepository;

    public LoginUserInfoDto getLogin(LoginDto loginDto) {
        LoginDto userInfo = LoginDto.builder()
                .userId(loginDto.userId())
                .passwd(SecurityUtil.makeSHA256(loginDto.passwd()))
                .build();

        LoginUserInfoDto loginUserInfo = userInfoRepository.getLogin(userInfo);

        if(loginUserInfo == null) {
            throw new UserInfoException(NOT_MATCH_USER_INFO);
        }

        return loginUserInfo;
    }
}

