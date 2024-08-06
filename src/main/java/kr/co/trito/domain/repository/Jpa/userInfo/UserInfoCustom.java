package kr.co.trito.domain.repository.Jpa.userInfo;

import kr.co.trito.dto.Jpa.login.LoginDto;
import kr.co.trito.dto.Jpa.login.LoginUserInfoDto;

public interface UserInfoCustom {
    LoginUserInfoDto getLogin(LoginDto loginDto);
}
