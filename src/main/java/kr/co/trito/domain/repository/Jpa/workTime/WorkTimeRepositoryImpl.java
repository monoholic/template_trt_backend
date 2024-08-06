package kr.co.trito.domain.repository.Jpa.workTime;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.co.trito.dto.Jpa.workTime.OverTimeDto;
import kr.co.trito.dto.Jpa.workTime.WorkTimeEndDto;
import kr.co.trito.dto.Jpa.workTime.WorkTimeStartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static kr.co.trito.domain.QCode.code1;
import static kr.co.trito.domain.QSawonInfo.sawonInfo;
import static kr.co.trito.domain.QWorkTime.workTime;
import static kr.co.trito.utils.ExpressionUtil.toChar;

@Repository
@RequiredArgsConstructor
public class WorkTimeRepositoryImpl implements WorkTimeCustom {
    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public WorkTimeStartDto getWorkTimeStart(String sawonNo) {
        return queryFactory
                .select(Projections.constructor(WorkTimeStartDto.class,
                            workTime.sawonNo,
                            sawonInfo.sawonNm,
                            toChar(workTime.startDt, "YYYY/MM/DD HH24:MI:SS", "v_start_dt"),
                            toChar(workTime.endDt, "YYYY/MM/DD HH24:MI:SS", "v_end_dt"),
                            workTime.cause
                        )
                )
                .from(workTime)
                .leftJoin(sawonInfo)
                .on(workTime.sawonNo.eq(sawonInfo.sawonNo))
                .where(
                        workTime.sawonNo.eq(sawonNo),
                        startDtEq(0)
                )
                .fetchFirst();
    }

    @Override
    public WorkTimeEndDto getWorkTimeEnd(String sawonNo) {
        return queryFactory
                .select(Projections.constructor(WorkTimeEndDto.class,
                        workTime.sawonNo,
                        sawonInfo.sawonNm,
                        toChar(workTime.startDt, "YYYY/MM/DD HH24:MI:SS", "v_start_dt"),
                        toChar(workTime.endDt, "YYYY/MM/DD HH24:MI:SS", "v_end_dt")
                    )
                )
                .from(workTime)
                .leftJoin(sawonInfo)
                .on(workTime.sawonNo.eq(sawonInfo.sawonNo))
                .where(
                        workTime.sawonNo.eq(sawonNo),
                        startDtEq(1)
                )
                .fetchOne();
    }

    @Override
    public Integer insertWorkTimeEnd(String sawonNo, String endIp) {
        LocalDateTime nowDateTime = LocalDateTime.now();

        return Math.toIntExact(queryFactory
                .update(workTime)
                .set(workTime.endDt, nowDateTime)
                .set(workTime.endIp, endIp)
                .where(
                        workTime.sawonNo.eq(sawonNo),
                        startDtEq(0)
                )
                .execute());
    }

    @Override
    public OverTimeDto getOverTimeFlag() {
        NumberExpression<Integer> currentTimeExpr = Expressions.numberTemplate(
                Integer.class,
                "TO_NUMBER(TO_CHAR(SYSDATE,'HH24MI'))"
        );

        NumberExpression<Integer> attVal1AsNumber = Expressions.numberTemplate(
                Integer.class,
                "TO_NUMBER({0})", code1.attVal1
        );

        Expression<String> weekGbn = Expressions.stringTemplate(
                "TO_CHAR(SYSDATE, 'D')"
        ).as("weekGbn");

        Expression<String> overTimeFlag = new CaseBuilder()
                .when(currentTimeExpr.gt(attVal1AsNumber))
                .then("Y")
                .otherwise("N")
                .as("overTimeFlag");

        return queryFactory
                .select(Projections.constructor(OverTimeDto.class,
                        weekGbn,
                        overTimeFlag
                        ))
                .from(code1)
                .fetchFirst();
    }

    // 시작날짜 조건
    private BooleanExpression startDtEq(int days) {
        // 현재 시간에서 일정 일수(days)를 뺌
        LocalDateTime dateTimeMinusDays = LocalDateTime.now().minusDays(days);

        // LocalDateTime을 Timestamp로 변환
        Timestamp timestamp = Timestamp.valueOf(dateTimeMinusDays);

        return Expressions.stringTemplate(
                "TO_CHAR({0}, {1})",
                workTime.startDt,
                "YYYYMMDD"
        ).eq(Expressions.stringTemplate(
                "TO_CHAR({0}, {1})",
                timestamp,
                "YYYYMMDD"
                )
        );
    }
}
