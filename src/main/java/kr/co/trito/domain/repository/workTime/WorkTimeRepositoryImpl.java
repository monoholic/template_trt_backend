package kr.co.trito.domain.repository.workTime;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.trito.dto.workTime.WorkTimeEndDto;
import kr.co.trito.dto.workTime.WorkTimeStartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static kr.co.trito.domain.QSawonInfo.sawonInfo;
import static kr.co.trito.domain.QWorkTime.workTime;

@Repository
@RequiredArgsConstructor
public class WorkTimeRepositoryImpl implements WorkTimeCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public WorkTimeStartDto getWorkTimeStart(String sawonNo) {
        return queryFactory
                .select(Projections.constructor(WorkTimeStartDto.class,
                            workTime.sawonNo,
                            sawonInfo.sawonNm,
                            Expressions.stringTemplate(
                                    "TO_CHAR({0}, {1})",
                                    workTime.startDt,
                                    "YYYY/MM/DD HH24:MI:SS"
                            ).as("v_start_dt"),
                            Expressions.stringTemplate(
                                    "TO_CHAR({0}, {1})",
                                    workTime.endDt,
                                    "YYYY/MM/DD HH24:MI:SS"
                            ).as("v_end_dt"),
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
                        Expressions.stringTemplate(
                                "TO_CHAR({0}, {1})",
                                workTime.startDt,
                                "YYYY/MM/DD HH24:MI:SS"
                        ).as("v_start_dt"),
                        Expressions.stringTemplate(
                                "TO_CHAR({0}, {1})",
                                workTime.endDt,
                                "YYYY/MM/DD HH24:MI:SS"
                        ).as("v_end_dt")
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
