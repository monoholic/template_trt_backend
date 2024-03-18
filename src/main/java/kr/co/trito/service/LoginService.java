package kr.co.trito.service;

import kr.co.trito.domain.repository.UserInfoRepository;
import kr.co.trito.dto.request.LoginDto;
import kr.co.trito.dto.response.LoginUserInfoDto;
import kr.co.trito.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserInfoRepository userInfoRepository;

    public LoginUserInfoDto getLogin(LoginDto loginDto) {
        String userId = loginDto.userId();
        String encPasswd   = SecurityUtil.makeSHA256(loginDto.passwd());
        return userInfoRepository.getLogin(userId, encPasswd);
    }
}
