package kr.co.trito.domain.repository.mybatis.holiday;

import kr.co.trito.dto.holiday.HolidayDto;
import kr.co.trito.dto.holiday.HolidayRegDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HolidayRepository {

    List<HolidayDto> getHolidayList(Map<String, Object> map);

    int checkHoliday(HolidayRegDto holidayRegDto);

    int regHoliday(HolidayRegDto holidayRegDto);
}
