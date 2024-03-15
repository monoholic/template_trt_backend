package kr.co.trito.domain.repository;

import kr.co.trito.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Query(value = """
        SELECT A.USER_ID AS USER_ID, A.PASSWD AS PASSWD, A.USER_NM AS USER_NM, A.SAWON_NO AS SAWON_NO, A.USE_YN AS USE_YN,
               B.DEPT_CD, F_GETDEPTNAME(B.DEPT_CD,'Y') AS DEPT_NM
        FROM TBSY_USER_INFO A, TBSY_SAWON_INFO B
        WHERE A.USER_ID = :userId
          AND A.PASSWD = :encPasswd
          AND A.USE_YN = 'Y'
          AND B.SAWON_NO = A.SAWON_NO
          AND B.OT_DATE IS NULL
    """, nativeQuery = true)
    UserInfo getLogin(@Param("userId") String userId, @Param("encPasswd") String encPasswd);
}
