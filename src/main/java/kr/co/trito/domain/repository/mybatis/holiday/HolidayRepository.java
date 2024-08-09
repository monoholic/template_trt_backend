package kr.co.trito.domain.repository.mybatis.holiday;

import kr.co.trito.dto.Mybatis.holiday.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HolidayRepository {
    
    // 연차 사용 현황 조회
    DayOffDtd getDayOffData(String sawonNo);

    // 휴가 목록 조회
    List<HolidayDto> getHolidayList(HolidayGetDto holidayGetDto);

    // 휴가 중복 검사
    int checkHoliday(HolidayRegDto holidayRegDto);

    // 휴가 등록
    int regHoliday(HolidayRegDto holidayRegDto);

    // 달력 데이터 조회
    List<HolidayCalendar> getCalendarData(HolidayGetDto holidayGetDto);
}
