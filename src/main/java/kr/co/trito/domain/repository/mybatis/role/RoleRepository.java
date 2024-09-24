package kr.co.trito.domain.repository.mybatis.role;

import kr.co.trito.dto.Mybatis.role.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleRepository {

// ------------- 역할 관리 -------------

    // total rows 검색
    int CountRoleList(RoleListParamsDto roleListParamsDto);

    // 역할 관리 목록 조회
    List<RoleListDto> getRoleList(RoleParamsDto params);

    // 역할 관리 중복 체크
    int checkRoleId(String roleId);

    // 역할 관리 추가
    int addRole(RoleAddModDto roleAddModDto);

    // 역할 관리 수정
    int modRole(RoleAddModDto roleAddModDto);

    // 역할 삭제
    int delRole(List<String> params);

// ------------- 사용자 역할 관리 -------------

    // 사용자 역할 total rows 검색
    int CountUserRoleList(UserRoleListParamsDto userRoleListParamsDto);

    // 사용자 역할 목록 조회
    List<UserRoleListDto> getUserRoleList(UserRoleParamsDto params);

    // 사용자 역할 중복 체크
    int checkUserRoleId(UserRoleAddModDto userRoleAddModDto);

    // 사용자 역할 추가
    int addUserRole(UserRoleAddModDto userRoleAddModDto);

    // 사용자 역할 수정
    int modUserRole(UserRoleAddModDto userRoleAddModDto);

    // 사용자 역할 삭제
    int delUserRole(Object params);
}
