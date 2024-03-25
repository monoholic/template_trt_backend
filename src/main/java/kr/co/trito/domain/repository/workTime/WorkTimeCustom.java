package kr.co.trito.domain.repository.workTime;

import kr.co.trito.dto.workTime.WorkTimeEndDto;
import kr.co.trito.dto.workTime.WorkTimeStartDto;

public interface WorkTimeCustom {
    WorkTimeStartDto getWorkTimeStart(String sawonNo);

    WorkTimeEndDto getWorkTimeEnd(String sawonNo);
}
