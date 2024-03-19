package kr.co.trito.service;

import jakarta.persistence.Tuple;
import kr.co.trito.domain.repository.UserInfoRepository;
import kr.co.trito.dto.request.LoginDto;
import kr.co.trito.dto.response.LoginUserInfoDto;
import kr.co.trito.exception.UserInfoException;
import kr.co.trito.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static kr.co.trito.enums.UserInfoErrorCode.*;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserInfoRepository userInfoRepository;

    public List<LoginUserInfoDto> getLogin(LoginDto loginDto) {

        List<Tuple> userInfo = userInfoRepository.getLogin(
                    loginDto.userId(),
                    SecurityUtil.makeSHA256(loginDto.passwd())
                )
                .orElseThrow(RuntimeException::new);

        if(userInfo.isEmpty()) throw new UserInfoException(NOT_MATCH_USER_INFO);

        return userInfo.stream().map(tuple -> {
            String userId = tuple.get("USER_ID", String.class);
            String userNm = tuple.get("USER_NM", String.class);
            String sawonNo = tuple.get("SAWON_NO", String.class);
            String deptCd = tuple.get("DEPT_CD", String.class);
            String deptNm = tuple.get("DEPT_NM", String.class);

            return new LoginUserInfoDto(
                        userId,
                        userNm,
                        sawonNo,
                        deptCd,
                        deptNm
                    );
        }).collect(Collectors.toList());
    }
}
