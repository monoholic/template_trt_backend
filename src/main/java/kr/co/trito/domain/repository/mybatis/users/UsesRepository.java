package kr.co.trito.domain.repository.mybatis.users;

import kr.co.trito.dto.Mybatis.users.UserListParamsDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsesRepository {

    // 사용자 목록 조회
    Object getUserList(UserListParamsDto userListParamsDto);
}
