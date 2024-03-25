package kr.co.trito.domain.repository.userInfo;

import kr.co.trito.dto.login.LoginDto;
import kr.co.trito.dto.login.LoginUserInfoDto;

public interface UserInfoCustom {
    LoginUserInfoDto getLogin(LoginDto loginDto);
}
