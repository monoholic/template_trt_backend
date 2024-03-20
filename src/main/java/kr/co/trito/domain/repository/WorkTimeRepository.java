package kr.co.trito.domain.repository;

import jakarta.persistence.Tuple;
import kr.co.trito.domain.WorkTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WorkTimeRepository extends JpaRepository<WorkTime, String> {
    @Query(value = """
            SELECT SAWON_NO
                , TO_CHAR(START_DT,'YYYY/MM/DD HH24:MI:SS') AS VSTART_DT
                , TO_CHAR(END_DT,'YYYY/MM/DD HH24:MI:SS')   AS VEND_DT
                , CAUSE
            FROM TBPY_WORKTIME
            WHERE SAWON_NO = :sawonNo
            AND TO_CHAR(START_DT,'YYYYMMDD') = TO_CHAR(SYSDATE,'YYYYMMDD')
    """, nativeQuery = true)
    Optional<Tuple> getWorkTimeStart(@Param("sawonNo") String sawonNo);

    @Query(value = """
        SELECT SAWON_NO
            , TO_CHAR(START_DT,'YYYY/MM/DD HH24:MI:SS') AS VSTART_DT
            , TO_CHAR(END_DT,'YYYY/MM/DD HH24:MI:SS')   AS VEND_DT
        FROM TBPY_WORKTIME
        WHERE SAWON_NO = :sawonNo
        AND TO_CHAR(START_DT,'YYYYMMDD') = TO_CHAR(SYSDATE-1,'YYYYMMDD')
    """, nativeQuery = true)
    Optional<Tuple> getWorkTimeEnd(@Param("sawonNo") String sawonNo);
}
