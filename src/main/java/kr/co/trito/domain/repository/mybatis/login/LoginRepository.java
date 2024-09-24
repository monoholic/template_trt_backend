package kr.co.trito.domain.repository.mybatis.login;

import kr.co.trito.dto.Mybatis.users.UserInfoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginRepository {
    UserInfoDto getUserInfo(UserInfoDto userInfoDto);
}
