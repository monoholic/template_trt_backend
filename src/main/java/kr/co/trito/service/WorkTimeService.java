package kr.co.trito.service;

import com.querydsl.core.Tuple;
import kr.co.trito.domain.repository.transExpenses.TransExpensesRepository;
import kr.co.trito.domain.repository.workTime.WorkTimeRepository;
import kr.co.trito.domain.repository.workTime.WorkTimeTestRepository;
import kr.co.trito.dto.SessionDto;
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
import java.util.Optional;

import static kr.co.trito.enums.WorkTimeErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WorkTimeService {
    private final WorkTimeRepository workTimeRepository;
    private final TransExpensesRepository transExpensesRepository;

    // Test WorkTime
    private final WorkTimeTestRepository workTimeTestRepository;

    public Map<String, Object> getWorkStartView(SessionDto sessionDto) {
        Map<String, Object> workTime = new HashMap<>();
        String sawonNo = sessionDto.getSawonNo();

//        List<Tuple> startWorkTime = workTimeRepository.getWorkTimeStart(sawonNo)
//                .orElseThrow(() -> new WorkTimeException(NOT_MATCH_WORK_TIME));
//
//
//        List<Tuple> endWorkTime = workTimeRepository.getWorkTimeEnd(sawonNo)
//                .orElseThrow(() -> new WorkTimeException(NOT_MATCH_WORK_TIME));

        StartWorkTimeDto startWorkTime = workTimeTestRepository.getWorkTimeStart(sawonNo);

        workTime.put("startWorkTime", startWorkTime);

//        // 당일 출퇴근 조회
//        workTime.put("startWorkTime",
//                    startWorkTime.isEmpty() ? StartWorkTimeDto.emptyWorkTime() :
//                            StartWorkTimeDto.builder()
//                                .sawonNo(startWorkTime.get(0).get("SAWON_NO", String.class))
//                                .vstartDt(startWorkTime.get(0).get("VSTART_DT", String.class))
//                                .vendDt(startWorkTime.get(0).get("VEND_DT", String.class))
//                                .cause(startWorkTime.get(0).get("CAUSE", String.class))
//                                .build()
//                );
//
//        // 전날 출퇴근 조회
//        workTime.put("endWorkTime",
//                endWorkTime.isEmpty() ? EndWorkTimeDto.emptyWorkTime() :
//                        EndWorkTimeDto.builder()
//                                .sawonNo(endWorkTime.get(0).get("SAWON_NO", String.class))
//                                .vstartDt(endWorkTime.get(0).get("VSTART_DT", String.class))
//                                .vendDt(endWorkTime.get(0).get("VEND_DT", String.class))
//                                .build()
//        );

        return workTime;
    }

    @Transactional
    public Integer regWorkTimeStart(SessionDto sessionDto, String acceptIp) {
        String sawonNo = sessionDto.getSawonNo();
        return workTimeRepository.insertWorkTimeStart(sawonNo, acceptIp);
    }

    @Transactional
    public Integer regWorkTimeEnd(SessionDto sessionDto, String acceptIp) {
//        String sawonNo = sessionDto.getSawonNo();
//        String userId = sessionDto.getUserId();
//
//        int cnt = workTimeRepository.insertWorkTimeEnd(sawonNo);
//        String tcnt = transExpensesRepository.getTransExpensesCnt(sawonNo);
//
//        if(cnt > 0) {
//            List<Tuple> overTimeObj = workTimeRepository.getOverTimeFlag()
//                    .orElseThrow(RuntimeException::new);
//
//            if(!overTimeObj.isEmpty()) {
//                String overTimeFlag = String.valueOf(overTimeObj.get(0).get("OVERTIME_FLAG", Character.class));
//                String weekGbn = String.valueOf(overTimeObj.get(0).get("WEEK_GBN", Character.class));
//
//                if(overTimeFlag.equals("Y") && !weekGbn.equals("1") && !weekGbn.equals("7") && tcnt.equals("0")){
//
//                    // querydsl 사용해서 개선할 예정
////                    ExpensesDto expensesDto = ExpensesDto.builder()
////                            .sawonNo(sawonNo)
////                            .reqGbn("ND")
////                            .userId(userId)
////                            .acceptIp(acceptIp)
////                            .build();
//
//                    workTimeRepository.insertOverTime(sawonNo, "ND", userId, acceptIp);
//                }
//            }
//
//            return overTimeObj.size();
//        }else {
//            throw new WorkTimeException(NOT_WORK_TIME_START_REGISTERED);
//        }
        return 0;
    }

    @Transactional
    public Integer regWorkTimeCause(SessionDto sessionDto, String cause) {
        int cnt = workTimeRepository.insertWorkTimeCause(sessionDto.getSawonNo(), cause);

        if(cnt < 1)
            throw new WorkTimeException(CAUSE_NOT_REGISTERED);

        return cnt;
    }


    @Transactional
    public Integer regWorkTimePreEnd(SessionDto sessionDto, String acceptIp) {
//        String sawonNo = sessionDto.getSawonNo();
//        String userId = sessionDto.getUserId();
//
//        int cnt = workTimeRepository.insertWorkTimePreEnd(sawonNo);
//        String tcnt = transExpensesRepository.getTransExpensesCnt(sawonNo);
//
//        if(cnt > 0) {
//            List<Tuple> overTimeObj = workTimeRepository.getOverTimeFlag()
//                    .orElseThrow(RuntimeException::new);
//
//            if(!overTimeObj.isEmpty()) {
//                // Empty 일때 "" 값 처리해야 됌
//                String overTimeFlag = String.valueOf(overTimeObj.get(0).get("OVERTIME_FLAG", Character.class));
//                String weekGbn = String.valueOf(overTimeObj.get(0).get("WEEK_GBN", Character.class));
//
//                if(overTimeFlag.equals("Y") && !weekGbn.equals("1") && !weekGbn.equals("7") && tcnt.equals("0")){
//
//                    workTimeRepository.insertOverTime(sawonNo, "ND", userId, acceptIp);
//                }
//            }
//
//            return overTimeObj.size();
//        }else {
//            throw new WorkTimeException(NOT_WORK_TIME_START_REGISTERED);
//        }

        return 0;
    }
}