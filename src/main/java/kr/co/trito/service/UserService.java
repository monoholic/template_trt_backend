package kr.co.trito.service;

import kr.co.trito.domain.repository.mybatis.users.UsesRepository;
import kr.co.trito.dto.Mybatis.users.UserListDto;
import kr.co.trito.dto.Mybatis.users.UserListParamsDto;
import kr.co.trito.dto.Mybatis.users.UserParmsDto;
import kr.co.trito.utils.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UsesRepository usesRepository;

    // 사용자 목록 조회
    public Object getUserList(UserListParamsDto userListParamsDto) {

        System.out.println("userListParamsDto ::: " + userListParamsDto);

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
}