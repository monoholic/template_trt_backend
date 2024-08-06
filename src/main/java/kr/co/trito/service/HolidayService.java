package kr.co.trito.service;

import kr.co.trito.domain.repository.mybatis.holiday.HolidayRepository;
import kr.co.trito.dto.SessionDto;
import kr.co.trito.dto.holiday.HolidayDto;
import kr.co.trito.dto.holiday.HolidayRegDto;
import kr.co.trito.exception.HolidayException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.co.trito.enums.HolidayErrorCode.HOLIDAY_DATE_DUPLICATION;
import static kr.co.trito.enums.HolidayErrorCode.NOT_REG_HOLIDAY;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class HolidayService {

    private final HolidayRepository holidayRepository;

    public List<HolidayDto> getHolidayList(SessionDto sessionDto, int year) {
        String sawonNo = sessionDto.getSawonNo();

        Map<String, Object> map = new HashMap<>();
        map.put("sawonNo", sawonNo);
        map.put("year", year);

        return holidayRepository.getHolidayList(map);
    }

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
}