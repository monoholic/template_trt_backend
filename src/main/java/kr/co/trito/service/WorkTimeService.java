package kr.co.trito.service;

import kr.co.trito.domain.repository.transExpenses.TransExpensesRepository;
import kr.co.trito.domain.repository.workTime.WorkTimeRepository;
import kr.co.trito.dto.SessionDto;
import kr.co.trito.dto.workTime.OverTimeDto;
import kr.co.trito.dto.workTime.WorkTimeStartDto;
import kr.co.trito.exception.WorkTimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kr.co.trito.enums.WorkTimeErrorCode.CAUSE_NOT_REGISTERED;
import static kr.co.trito.enums.WorkTimeErrorCode.END_WORK_TIME_NOT_REGISTERED;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WorkTimeService {
    private final WorkTimeRepository workTimeRepository;
    private final TransExpensesRepository transExpensesRepository;

    public WorkTimeStartDto getWorkStartView(SessionDto sessionDto) {
        String sawonNo = sessionDto.getSawonNo();

        return workTimeRepository.getWorkTimeStart(sawonNo);
    }

    @Transactional
    public Integer regWorkTimeStart(SessionDto sessionDto, String acceptIp) {
        String sawonNo = sessionDto.getSawonNo();

        return workTimeRepository.insertWorkTimeStart(sawonNo, acceptIp);
    }

    @Transactional
    public Integer regWorkTimeEnd(SessionDto sessionDto, String acceptIp) {
        String sawonNo = sessionDto.getSawonNo();
        String userId = sessionDto.getUserId();

        int cnt = workTimeRepository.insertWorkTimeEnd(sawonNo, acceptIp);
        String tcnt = transExpensesRepository.getTransExpensesCnt(sawonNo);

        if(cnt > 0) {
            OverTimeDto overTime = workTimeRepository.getOverTimeFlag();

            if(overTime != null) {
                String overTimeFlag = overTime.overTimeFlag();
                String weekGbn = overTime.weekGbn();

                if(overTimeFlag.equals("Y") && !weekGbn.equals("1") && !weekGbn.equals("7") && tcnt.equals("0")){
                    workTimeRepository.insertOverTime(sawonNo, "ND", userId, acceptIp);
                }
            }
            return 1;
        }else {
            throw new WorkTimeException(END_WORK_TIME_NOT_REGISTERED);
        }

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