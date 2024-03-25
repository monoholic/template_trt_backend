package kr.co.trito.domain.repository.workTime;

import com.querydsl.core.Tuple;
import kr.co.trito.dto.workTime.StartWorkTimeDto;

public interface WorkTimeTestCustom {
    StartWorkTimeDto getWorkTimeStart(String sawonNo);
}
