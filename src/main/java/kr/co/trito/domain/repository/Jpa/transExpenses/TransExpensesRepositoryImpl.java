package kr.co.trito.domain.repository.Jpa.transExpenses;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static kr.co.trito.domain.QAllowance.allowance;

@Repository
@RequiredArgsConstructor
public class TransExpensesRepositoryImpl implements TransExpensesCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public String getTransExpensesCnt(String sawonNo) {
        return queryFactory
                .select(
                        Expressions.numberTemplate(Long.class,"COUNT({0})", "*").as("TCNT")
                )
                .from(allowance)
                .where(
                        allowance.sawonNo.eq(sawonNo),
                        Expressions.stringTemplate(
                                "TO_CHAR({0}, {1})",
                                allowance.startDt,
                                "YYYYMMDD"
                        ).eq(Expressions.stringTemplate(
                                        "TO_CHAR({0}, {1})",
                                        "SYSTEM",
                                        "YYYYMMDD"
                                )
                        )
                ).fetchFirst().toString();
    }

}
