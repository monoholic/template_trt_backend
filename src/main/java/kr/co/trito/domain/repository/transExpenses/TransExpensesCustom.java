package kr.co.trito.domain.repository.transExpenses;

import org.springframework.data.repository.query.Param;

public interface TransExpensesCustom {
    String getTransExpensesCnt(@Param("sawonNo") String sawonNo);
}
