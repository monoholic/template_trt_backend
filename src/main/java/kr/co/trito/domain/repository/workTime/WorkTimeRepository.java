package kr.co.trito.domain.repository.workTime;

import com.querydsl.core.Tuple;
import kr.co.trito.domain.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkTimeRepository extends JpaRepository<WorkTime, String>, WorkTimeCustom {
//    @Query(value = """
//        SELECT SAWON_NO
//            , TO_CHAR(START_DT,'YYYY/MM/DD HH24:MI:SS') AS VSTART_DT
//            , TO_CHAR(END_DT,'YYYY/MM/DD HH24:MI:SS')   AS VEND_DT
//        FROM TBPY_WORKTIME
//        WHERE SAWON_NO = :sawonNo
//        AND TO_CHAR(START_DT,'YYYYMMDD') = TO_CHAR(SYSDATE-1,'YYYYMMDD')
//    """, nativeQuery = true)
//    Optional<List<Tuple>> getWorkTimeEnd(@Param("sawonNo") String sawonNo);

    @Modifying
    @Query(value = """
        INSERT INTO TBPY_WORKTIME(SAWON_NO, START_DT, GUBUN_CD, ACCEPT_IP)
        VALUES(
               :sawonNo,
               SYSDATE,
               CASE WHEN TO_CHAR(SYSDATE,'D') = '1' OR TO_CHAR(SYSDATE,'D') = '7' THEN 'WT02' ELSE 'WT01' END,
               :acceptIp
        )
    """, nativeQuery = true)
    Integer insertWorkTimeStart(@Param("sawonNo") String sawonNo, @Param("acceptIp") String acceptIp);

    @Modifying
    @Query(value = """
        UPDATE TBPY_WORKTIME
        SET END_DT = SYSDATE
        WHERE SAWON_NO = :sawonNo
        AND TO_CHAR(START_DT,'YYYYMMDD') = TO_CHAR(SYSDATE,'YYYYMMDD')
    """, nativeQuery = true)
    Integer insertWorkTimeEnd(@Param("sawonNo") String sawonNo);

    @Query(value = """
        SELECT TO_CHAR(SYSDATE,'D') AS WEEK_GBN
             , CASE WHEN TO_NUMBER(TO_CHAR(SYSDATE,'HH24MI')) > ATT_VAL1 THEN 'Y' ELSE 'N' END AS OVERTIME_FLAG
        FROM TBSY_CODE
        WHERE GROUP_CD = 'PY003'
        AND   CODE = 'NDBASE'
    """, nativeQuery = true)
    Optional<List<Tuple>> getOverTimeFlag();

    @Modifying
    @Query(value = """
        INSERT INTO TBPY_ALLOWANCE(SAWON_NO, START_DT, END_DT, REQ_GBN, APPROVAL_ID, APPROVAL_STATUS, AMOUNT, REG_DT, REG_ID, ACCEPT_IP)
        VALUES(
            :sawonNo,
            TO_CHAR(SYSDATE,'YYYYMMDD'),
            SYSDATE,
            :reqGbn,
            F_GETUSERAPPVAL(:sawonNo),
            '10',
            NVL(F_GETCODEATTVAL('PY001', :reqGbn, '1'), 0),
            SYSDATE,
            :userId,
            :acceptIp
        )
    """, nativeQuery = true)
    Integer insertOverTime(
           @Param("sawonNo") String sawonNo,
           @Param("reqGbn") String reqGbn,
           @Param("userId") String userId,
           @Param("acceptIp") String acceptIp
           );

    @Modifying
    @Query(value = """
        UPDATE TBPY_WORKTIME
        SET CAUSE = :cause
        WHERE SAWON_NO = :sawonNo
        AND TO_CHAR(START_DT,'YYYYMMDD') = TO_CHAR(SYSDATE,'YYYYMMDD')
    """, nativeQuery = true)
    Integer insertWorkTimeCause(@Param("sawonNo") String sawonNo, @Param("cause") String cause);

    @Modifying
    @Query(value = """
        UPDATE TBPY_WORKTIME
        SET END_DT = SYSDATE
        WHERE SAWON_NO = :sawonNo
        AND TO_CHAR(START_DT,'YYYYMMDD') = TO_CHAR(SYSDATE-1,'YYYYMMDD')
    """, nativeQuery = true)
    Integer insertWorkTimePreEnd(@Param("sawonNo") String sawonNo);
}