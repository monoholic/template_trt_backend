package kr.co.trito.domain.repository.mybatis.users;

import kr.co.trito.dto.Mybatis.users.UserAddMod;
import kr.co.trito.dto.Mybatis.users.UserListDto;
import kr.co.trito.dto.Mybatis.users.UserListParamsDto;
import kr.co.trito.dto.Mybatis.users.UserParmsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsesRepository {

    // total rows 검색
    int selectCountUser(UserListParamsDto userListParamsDto);

    // 사용자 목록 조회
    List<UserListDto> getUserList(UserParmsDto params);

    // 아이디 중복 조회
    int checkUserId(String userId);

    // 유저 등록
    int addUser(UserAddMod userAddMod);

    // 유저 수정
    int modUser(UserAddMod userAddMod);

    // 유저 삭제
    int delUser(List<String> params);
}
