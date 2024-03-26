package kr.co.trito.domain.repository.transExpenses;

import kr.co.trito.domain.Allowance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransExpensesRepository extends JpaRepository<Allowance, String>, TransExpensesCustom {
}
