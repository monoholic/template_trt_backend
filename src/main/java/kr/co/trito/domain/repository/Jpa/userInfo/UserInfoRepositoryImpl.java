package kr.co.trito.domain.repository.Jpa.userInfo;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.trito.dto.Jpa.login.LoginDto;
import kr.co.trito.dto.Jpa.login.LoginUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kr.co.trito.domain.QSawonInfo.sawonInfo;
import static kr.co.trito.domain.QUserInfo.userInfo;
import static kr.co.trito.utils.ExpressionUtil.getDeptName;

@Repository
@RequiredArgsConstructor
public class UserInfoRepositoryImpl implements UserInfoCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public LoginUserInfoDto getLogin(LoginDto loginDto) {
        return queryFactory
                .select(Projections.constructor(LoginUserInfoDto.class,
                        userInfo.userId,
                        userInfo.passwd,
                        userInfo.userNm,
                        userInfo.sawonNo,
                        userInfo.useYn,
                        sawonInfo.deptCd,
                        sawonInfo.jikcheck,
                        getDeptName(sawonInfo.deptCd, "Y", "deptNm")
                ))
                .from(userInfo, sawonInfo)
                .where(
                        userInfo.userId.eq(loginDto.userId()),
                        userInfo.passwd.eq(loginDto.passwd()),
                        userInfo.useYn.eq("Y"),
                        sawonInfo.sawonNo.eq(userInfo.sawonNo),
                        sawonInfo.otDate.isNull()
                    )
                .fetchOne();
    }
}
