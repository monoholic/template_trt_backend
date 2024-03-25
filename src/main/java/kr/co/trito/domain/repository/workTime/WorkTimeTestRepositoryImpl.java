package kr.co.trito.domain.repository.workTime;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.trito.dto.workTime.StartWorkTimeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kr.co.trito.domain.QWorkTime.workTime;

@Repository
@RequiredArgsConstructor
public class WorkTimeTestRepositoryImpl implements WorkTimeTestCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public StartWorkTimeDto getWorkTimeStart(String sawonNo) {
        return queryFactory
                .select(Projections.constructor(StartWorkTimeDto.class,
                        workTime.sawonNo,
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
                .where(
                        workTime.sawonNo.eq(sawonNo),
                        startDtEq(0)
                    )
                .fetchOne();
    }

    // 시작날짜 조건
    private BooleanExpression startDtEq(int startDate) {
        String date = "";

        if(startDate > 0) {
            date += " - " + startDate;
        }

        return Expressions.stringTemplate(
                "TO_CHAR({0}, {1})",
                workTime.startDt,
                        "YYYYMMDD"
                ).eq(Expressions.stringTemplate(
                        "TO_CHAR({0}, {1})",
                        Expressions.stringTemplate("SYSDATE" + date),
                        "YYYYMMDD"
                )
        );
    }
}
