package kr.co.trito.domain.repository.workTime;

import kr.co.trito.domain.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTimeTestRepository extends JpaRepository<WorkTime, String>, WorkTimeTestCustom {

}
