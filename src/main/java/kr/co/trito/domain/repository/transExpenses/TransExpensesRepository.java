package kr.co.trito.domain.repository.transExpenses;

import kr.co.trito.domain.Allowance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransExpensesRepository extends JpaRepository<Allowance, String>, TransExpensesCustom {
}
