package kr.co.trito.service;

import kr.co.trito.domain.repository.transExpenses.TransExpensesRepository;
import kr.co.trito.domain.repository.workTime.WorkTimeRepository;
import kr.co.trito.dto.SessionDto;
import kr.co.trito.dto.workTime.WorkTimeEndDto;
import kr.co.trito.dto.workTime.WorkTimeStartDto;
import kr.co.trito.exception.WorkTimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static kr.co.trito.enums.WorkTimeErrorCode.CAUSE_NOT_REGISTERED;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WorkTimeService {
    private final WorkTimeRepository workTimeRepository;
    private final TransExpensesRepository transExpensesRepository;

    public Map<String, Object> getWorkStartView(SessionDto sessionDto) {
        Map<String, Object> workTime = new HashMap<>();
        String sawonNo = sessionDto.getSawonNo();

        WorkTimeStartDto startWorkTime = workTimeRepository.getWorkTimeStart(sawonNo);
        WorkTimeEndDto endWorkTime = workTimeRepository.getWorkTimeEnd(sawonNo);

        workTime.put("startWorkTime", startWorkTime);   // 오늘 출퇴근 조회
        workTime.put("endWorkTime", endWorkTime);       // 전날 출퇴근 조회

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