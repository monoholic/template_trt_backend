package kr.co.trito.service;

import kr.co.trito.domain.repository.mybatis.users.UsesRepository;
import kr.co.trito.dto.Mybatis.users.UserAddMod;
import kr.co.trito.dto.Mybatis.users.UserListDto;
import kr.co.trito.dto.Mybatis.users.UserListParamsDto;
import kr.co.trito.dto.Mybatis.users.UserParmsDto;
import kr.co.trito.exception.HolidayException;
import kr.co.trito.exception.UserInfoException;
import kr.co.trito.utils.SearchCondition;
import kr.co.trito.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.co.trito.enums.HolidayErrorCode.HOLIDAY_DATE_DUPLICATION;
import static kr.co.trito.enums.UserInfoErrorCode.USER_ID_DUPLICATION;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UsesRepository usesRepository;

    // 사용자 목록 조회
    public Object getUserList(UserListParamsDto userListParamsDto) {

        // sortBy를 DB 컬럼 양식으로 변환
        if(userListParamsDto.getSortBy() != null){
            String str = userListParamsDto.getSortBy().replaceAll("([a-z])([A-Z]+)", "$1_$2").toUpperCase();
            userListParamsDto.setSortBy(str);

        }

        // 페이지 관련 세팅
        SearchCondition condition = new SearchCondition(userListParamsDto.getPage(), userListParamsDto.getNumOfRows());
        int total = usesRepository.selectCountUser(userListParamsDto);
        condition.pageSetup(total);

        // DB에 보낼 쿼리 파라미터
        UserParmsDto params = new UserParmsDto();
        params.setCondition(condition);
        params.setUserListParamsDto(userListParamsDto);

        // 목록 조회
        List<UserListDto> resList = usesRepository.getUserList(params);

        // 반환 값
        Map<String, Object> res = new HashMap<>();

        res.put("resList", resList);
        res.put("total", total);

        return res;
    }

    // 사용자 추가, 수정
    public long addModUser(UserAddMod userAddMod) {
        int res = -1;

        // 추가, 수정 분기
        String flag = userAddMod.getAddMod();

        if(flag.equals("A")) res = addUser(userAddMod);
        else res = modUser(userAddMod);

        return (long)res;
    }

    // 사용자 추가
    public int addUser(UserAddMod userAddMod){
        int res = -1;
        // 아이디 중복 확인
        int idCnt = usesRepository.checkUserId(userAddMod.getUserId());

        if(idCnt == 0){
            // 아이디중복X
            // 비밀번호 암호화
            userAddMod.setPassword(SecurityUtil.makeSHA256(userAddMod.getPassword()));

            res = usesRepository.addUser(userAddMod);
        } else {
            // 아이디중복
            throw new UserInfoException(USER_ID_DUPLICATION);
        }

        return res;
    }

    // 사용자 수정
    public int modUser(UserAddMod userAddMod){
        int res = -1;

        res = usesRepository.modUser(userAddMod);
        return res;
    }

    // 사용자 삭제
    public long delUser(List<String> params) {
        int res = -1;
        System.out.println("====> params ::: " + params.getClass().getName());
        res = usesRepository.delUser(params);

        return (long)res;
    }
}