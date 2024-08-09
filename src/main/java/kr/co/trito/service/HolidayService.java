package kr.co.trito.service;

import jakarta.validation.Valid;
import kr.co.trito.domain.repository.mybatis.holiday.HolidayRepository;
import kr.co.trito.dto.Mybatis.holiday.*;
import kr.co.trito.dto.SessionDto;
import kr.co.trito.exception.HolidayException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.co.trito.enums.HolidayErrorCode.HOLIDAY_DATE_DUPLICATION;
import static kr.co.trito.enums.HolidayErrorCode.NOT_REG_HOLIDAY;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class HolidayService {

    private final HolidayRepository holidayRepository;

    // 연차 사용 현황 조회
    public DayOffDtd getDayOffData(SessionDto sessionDto) {
        String sawonNo = sessionDto.getSawonNo();

        return holidayRepository.getDayOffData(sawonNo);
    }

    // 휴가 목록 조회
    public List<HolidayDto> getHolidayList(SessionDto sessionDto, HolidayGetDto holidayGetDto) {

        holidayGetDto.setSawonNo(sessionDto.getSawonNo());

        return holidayRepository.getHolidayList(holidayGetDto);
    }

    // 휴가 등록
    public Long regHoliday(SessionDto sessionDto, HolidayRegDto holidayRegDto) {

        holidayRegDto.setSawonNo(sessionDto.getSawonNo());

        // 휴가 시작일 중복 확인
        int cnt = holidayRepository.checkHoliday(holidayRegDto);

        // 확인 후 디비 처리
        if (cnt > 0) {
            throw new HolidayException(HOLIDAY_DATE_DUPLICATION);
        } else {
            cnt = holidayRepository.regHoliday(holidayRegDto);
        }

        // 디비 처리 후 코드 처리
        if (cnt < 1) {
            throw new HolidayException(NOT_REG_HOLIDAY);
        }
        return (long)cnt;
    }

    // 달력 데이터 조회
    public List<HolidayCalendar> getCalendarData(SessionDto sessionDto, HolidayGetDto holidayGetDto) {

        holidayGetDto.setSawonNo(sessionDto.getSawonNo());

        System.out.println("holidayGetDto ==> " + holidayGetDto.toString());

        return holidayRepository.getCalendarData(holidayGetDto);
    }
}