package kr.co.trito.service;

import jakarta.persistence.Tuple;
import kr.co.trito.domain.repository.WorkTimeRepository;
import kr.co.trito.dto.workTime.WorkTimeDto;
import kr.co.trito.exception.WorkTimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static kr.co.trito.enums.WorkTimeErrorCode.NOT_MATCH_WORK_TIME;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkTimeService {
    private final WorkTimeRepository workTimeRepository;

    public List<WorkTimeDto> getWorkStartView(String sawonNo) {
        Tuple workStartTime = workTimeRepository.getWorkTimeStart(sawonNo)
                .orElseThrow(() -> new WorkTimeException(NOT_MATCH_WORK_TIME));

        Tuple workEndTime = workTimeRepository.getWorkTimeEnd(sawonNo)
                .orElseThrow(() -> new WorkTimeException(NOT_MATCH_WORK_TIME));

        List<WorkTimeDto> workTime = new ArrayList<>();

        workTime.add(
                    WorkTimeDto.builder()
                        .sawonNo(workStartTime.get("SAWON_NO", String.class))
                        .vstartDt(workStartTime.get("VSTART_DT", String.class))
                        .vendDt(workStartTime.get("VEND_DT", String.class))
                        .cause(workStartTime.get("CAUSE", String.class))
                        .build()
        );

        workTime.add(
                WorkTimeDto.builder()
                        .sawonNo(workEndTime.get("SAWON_NO", String.class))
                        .vstartDt(workEndTime.get("VSTART_DT", String.class))
                        .vendDt(workEndTime.get("VEND_DT", String.class))
                        .build()
        );

        return workTime;
    }

    public Integer regWorkTimeStart(String sawonNo, String acceptIp) {
        return workTimeRepository.insertWorkTimeStart(sawonNo, acceptIp);
    }
}