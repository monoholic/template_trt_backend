package kr.co.trito.service;

import jakarta.persistence.Tuple;
import kr.co.trito.domain.repository.UserInfoRepository;
import kr.co.trito.dto.request.LoginDto;
import kr.co.trito.dto.response.LoginUserInfoDto;
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

        Tuple userInfo = userInfoRepository.getLogin(
                        loginDto.userId(),
                        SecurityUtil.makeSHA256(loginDto.passwd())
                )
                .orElseThrow(() -> new UserInfoException(NOT_MATCH_USER_INFO));

        return new LoginUserInfoDto(
                userInfo.get("USER_ID", String.class),
                userInfo.get("USER_NM", String.class),
                userInfo.get("SAWON_NO", String.class),
                userInfo.get("DEPT_CD", String.class),
                userInfo.get("DEPT_NM", String.class)
        );
    }
}

