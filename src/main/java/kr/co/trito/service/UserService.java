package kr.co.trito.service;

import kr.co.trito.domain.repository.mybatis.users.UsesRepository;
import kr.co.trito.dto.Mybatis.users.UserAddModDto;
import kr.co.trito.dto.Mybatis.users.UserListDto;
import kr.co.trito.dto.Mybatis.users.UserListParamsDto;
import kr.co.trito.dto.Mybatis.users.UserParmsDto;
import kr.co.trito.exception.UserInfoException;
import kr.co.trito.utils.ComUtil;
import kr.co.trito.utils.SearchCondition;
import kr.co.trito.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.co.trito.enums.UserInfoErrorCode.USER_ID_DUPLICATION;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UsesRepository usesRepository;

    // 사용자 목록 조회
    public Object getUserList(UserListParamsDto userListParamsDto) {

        ComUtil comUtil = new ComUtil();

        // sortBy를 DB 컬럼 양식으로 변환
        if(userListParamsDto.getSortBy() != null){
            userListParamsDto.setSortBy(comUtil.changeForm(userListParamsDto.getSortBy()));
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
    public long addModUser(UserAddModDto userAddModDto) {
        int res = -1;

        // 추가, 수정 분기
        String flag = userAddModDto.getAddMod();

        if(flag.equals("A")) res = addUser(userAddModDto);
        else res = modUser(userAddModDto);

        return (long)res;
    }

    // 사용자 추가
    public int addUser(UserAddModDto userAddModDto){
        int res = -1;
        // 아이디 중복 확인
        int idCnt = usesRepository.checkUserId(userAddModDto.getUserId());

        if(idCnt == 0){
            // 아이디중복X
            // 비밀번호 암호화
            userAddModDto.setPassword(SecurityUtil.makeSHA256(userAddModDto.getPassword()));

            res = usesRepository.addUser(userAddModDto);
        } else {
            // 아이디중복
            throw new UserInfoException(USER_ID_DUPLICATION);
        }

        return res;
    }

    // 사용자 수정
    public int modUser(UserAddModDto userAddModDto){
        int res = -1;

        // 비밀번호 암호화
        if(userAddModDto.getPassword() != null  && !userAddModDto.getPassword().isEmpty()){
            userAddModDto.setPassword(SecurityUtil.makeSHA256(userAddModDto.getPassword()));
        }

        res = usesRepository.modUser(userAddModDto);
        return res;
    }

    // 사용자 삭제
    public long delUser(List<String> params) {
        int res = -1;
        res = usesRepository.delUser(params);

        return (long)res;
    }
}