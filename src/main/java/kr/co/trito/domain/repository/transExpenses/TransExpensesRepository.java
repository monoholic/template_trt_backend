package kr.co.trito.domain.repository.transExpenses;

import kr.co.trito.domain.Allowance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransExpensesRepository extends JpaRepository<Allowance, String>, TransExpensesCustom {
//    @Query(value = """
//        SELECT COUNT(*) AS TCNT
//                    FROM TBPY_ALLOWANCE
//                    WHERE SAWON_NO = :sawonNo
//                    AND   START_DT = TO_CHAR(SYSDATE,'YYYYMMDD')
//    """, nativeQuery = true)
//    String getTransExpensesCnt(@Param("sawonNo") String sawonNo);
}
