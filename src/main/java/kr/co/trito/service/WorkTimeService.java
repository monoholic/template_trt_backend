package kr.co.trito.service;

import jakarta.persistence.Tuple;
import kr.co.trito.domain.repository.WorkTimeRepository;
import kr.co.trito.dto.workTime.EndWorkTimeDto;
import kr.co.trito.dto.workTime.StartWorkTimeDto;
import kr.co.trito.exception.WorkTimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.co.trito.enums.WorkTimeErrorCode.NOT_MATCH_WORK_TIME;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WorkTimeService {
    private final WorkTimeRepository workTimeRepository;

    public Map<String, Object> getWorkStartView(String sawonNo) {
        Map<String, Object> workTime = new HashMap<>();

        List<Tuple> startWorkTime = workTimeRepository.getWorkTimeStart(sawonNo)
                .orElseThrow(() -> new WorkTimeException(NOT_MATCH_WORK_TIME));

        List<Tuple> endWorkTime = workTimeRepository.getWorkTimeEnd(sawonNo)
                .orElseThrow(() -> new WorkTimeException(NOT_MATCH_WORK_TIME));

        // 출근 시간
        workTime.put("startWorkTime",
                    startWorkTime.isEmpty() ? StartWorkTimeDto.emptyWorkTime() :
                            StartWorkTimeDto.builder()
                                .sawonNo(startWorkTime.get(0).get("SAWON_NO", String.class))
                                .vstartDt(startWorkTime.get(0).get("VSTART_DT", String.class))
                                .vendDt(startWorkTime.get(0).get("VEND_DT", String.class))
                                .cause(startWorkTime.get(0).get("CAUSE", String.class))
                                .build()
                );

        // 퇴근 시간
        workTime.put("endWorkTime",
                endWorkTime.isEmpty() ? EndWorkTimeDto.emptyWorkTime() :
                        EndWorkTimeDto.builder()
                                .sawonNo(endWorkTime.get(0).get("SAWON_NO", String.class))
                                .vstartDt(endWorkTime.get(0).get("VSTART_DT", String.class))
                                .vendDt(endWorkTime.get(0).get("VEND_DT", String.class))
                                .build()
        );

        return workTime;
    }

    @Transactional
    public Integer regWorkTimeStart(String sawonNo, String acceptIp) {
        return workTimeRepository.insertWorkTimeStart(sawonNo, acceptIp);
    }
}