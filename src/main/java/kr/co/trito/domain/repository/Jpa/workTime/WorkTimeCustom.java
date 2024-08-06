package kr.co.trito.domain.repository.Jpa.workTime;

import kr.co.trito.dto.Jpa.workTime.OverTimeDto;
import kr.co.trito.dto.Jpa.workTime.WorkTimeEndDto;
import kr.co.trito.dto.Jpa.workTime.WorkTimeStartDto;

public interface WorkTimeCustom {
    WorkTimeStartDto getWorkTimeStart(String sawonNo);

    WorkTimeEndDto getWorkTimeEnd(String sawonNo);

    Integer insertWorkTimeEnd(String sawonNo, String endIp);

    OverTimeDto getOverTimeFlag();
}
