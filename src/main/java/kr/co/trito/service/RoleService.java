package kr.co.trito.service;

import kr.co.trito.domain.repository.mybatis.role.RoleRepository;
import kr.co.trito.dto.Mybatis.role.*;
import kr.co.trito.exception.RoleException;
import kr.co.trito.utils.ComUtil;
import kr.co.trito.utils.SearchCondition;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.co.trito.enums.RoleErrorCode.ROLE_ID_DUPLICATION;
import static kr.co.trito.enums.RoleErrorCode.USER_ROLE_ID_DUPLICATION;

@Service
@Transactional
@AllArgsConstructor
public class RoleService {

    @Autowired
    private final RoleRepository roleRepository;

// ------------- 역할 관리 --------------------

    // 역할 목록 조회
    public Object getRoleList(RoleListParamsDto roleListParamsDto) {

        ComUtil comUtil = new ComUtil();

        // sortBy를 DB 컬럼 양식으로 변환
        if(roleListParamsDto.getSortBy() != null){
            roleListParamsDto.setSortBy(comUtil.changeForm(roleListParamsDto.getSortBy()));
        }

        // 검색 조건을 DB 컬럼 양식으로 변환
        if(roleListParamsDto.getSearchOpt() != null){
            roleListParamsDto.setSearchOpt(comUtil.changeForm(roleListParamsDto.getSearchOpt()));
        }

        // 페이지 관련 세팅
        SearchCondition condition = new SearchCondition(roleListParamsDto.getPage(), roleListParamsDto.getNumOfRows());
        int total = roleRepository.CountRoleList(roleListParamsDto);
        condition.pageSetup(total);

        // DB에 보낼 쿼리 파라미터
        RoleParamsDto params = new RoleParamsDto();
        params.setCondition(condition);
        params.setRoleListParamsDto(roleListParamsDto);

        // 목록 조회
        List<RoleListDto> resList = roleRepository.getRoleList(params);

        // 반환 값
        Map<String, Object> res = new HashMap<>();

        res.put("resList", resList);
        res.put("total", total);

        return res;
    }

    // 역할 추가 수정
    public long addModRole(RoleAddModDto roleAddModDto) {
        int res = -1;

        if(roleAddModDto.getAddMod().equals("A")) res = addRole(roleAddModDto);
        else res = modRole(roleAddModDto);

        return (long)res;
    }

    // 역할 추가
    public int addRole(RoleAddModDto roleAddModDto) {
        int res = -1;

        // 역할 중복 체크
        int idCnt = roleRepository.checkRoleId(roleAddModDto.getRoleId());

        if(idCnt == 0){
            // 중복X
            res = roleRepository.addRole(roleAddModDto);
        } else {
            // 중복
            throw new RoleException(ROLE_ID_DUPLICATION);
        }

        return res;
    }

    // 역할 수정
    public int modRole(RoleAddModDto roleAddModDto) {
        int res = -1;
        res = roleRepository.modRole(roleAddModDto);

        return res;
    }

    // 역할 삭제
    public long delRole(List<String> params) {
        int res = -1;
        res = roleRepository.delRole(params);

        return (long) res;
    }


// ------------- 사용자 역할 관리 --------------------

    // 사용자 역할 목록 조회
    public Object getUserRoleList(UserRoleListParamsDto userRoleListParamsDto) {
        ComUtil comUtil = new ComUtil();

        // sortBy를 DB 컬럼 양식으로 변환
        if(userRoleListParamsDto.getSortBy() != null){
            userRoleListParamsDto.setSortBy(comUtil.changeForm(userRoleListParamsDto.getSortBy()));
        }

        // 페이지 관련 세팅
        SearchCondition condition = new SearchCondition(userRoleListParamsDto.getPage(), userRoleListParamsDto.getNumOfRows());
        int total = roleRepository.CountUserRoleList(userRoleListParamsDto);
        condition.pageSetup(total);

        // DB에 보낼 쿼리 파라미터
        UserRoleParamsDto params = new UserRoleParamsDto();
        params.setCondition(condition);
        params.setUserRoleListParamsDto(userRoleListParamsDto);

        // 목록 조회
        List<UserRoleListDto> resList = roleRepository.getUserRoleList(params);

        // 반환 값
        Map<String, Object> res = new HashMap<>();

        res.put("resList", resList);
        res.put("total", total);

        return res;
    }

    // 사용자 역할 추가 수정
    public Object addModUserRole(UserRoleAddModDto userRoleAddModDto) {
        int res = -1;

        // 추가 수정 분기
        if(userRoleAddModDto.getAddMod().equals("A")) addUserRole(userRoleAddModDto);
        else modUserRole(userRoleAddModDto);

        return (long)res;
    }

    // 사용자 역할 추가
    public int addUserRole(UserRoleAddModDto userRoleAddModDto){
        int res = -1;

        // 사용자 역할 중복 체크
        int idCnt = roleRepository.checkUserRoleId(userRoleAddModDto);

        if(idCnt == 0){
            // 중복 X
            res = roleRepository.addUserRole(userRoleAddModDto);
        } else {
            // 중복
            throw new RoleException(USER_ROLE_ID_DUPLICATION);
        }

        return res;
    }

    // 사용자 역할 수정
    public int modUserRole(UserRoleAddModDto userRoleAddModDto){
        int res = -1;
        res = roleRepository.modUserRole(userRoleAddModDto);

        return res;
    }

    // 사용자 역할 삭제
    public Object delUserRole(Object params) {
        int res = -1;
        res = roleRepository.delUserRole(params);

        return (long)res;
    }
}
