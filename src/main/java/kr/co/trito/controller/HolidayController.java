package kr.co.trito.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.Mybatis.holiday.HolidayGetDto;
import kr.co.trito.dto.SessionDto;
import kr.co.trito.dto.Mybatis.holiday.HolidayRegDto;
import kr.co.trito.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HolidayController {
    private final HolidayService holidayService;

    // 연차 사용 현황 조회
    @GetMapping("/getDayOffData")
    public ResponseEntity<TritoResponse<?>> getDayOffData(
            HttpSession session
    ) {
        SessionDto sessionDto = new SessionDto(session);

        return ResponseEntity.ok(new TritoResponse<>(holidayService.getDayOffData(sessionDto)));
    }

    // 휴가 목록 조회
    @PostMapping("/getHolidayList")
    public ResponseEntity<TritoResponse<?>> getHolidayList(
            HttpSession session,
            @Valid @RequestBody HolidayGetDto holidayGetDto
            ) {
        SessionDto sessionDto = new SessionDto(session);

        return ResponseEntity.ok(new TritoResponse<>(holidayService.getHolidayList(sessionDto, holidayGetDto)));
    }

    // 휴가 등록
    @PostMapping("/regHoliday")
    public ResponseEntity<TritoResponse<?>> regHoliday(
            HttpSession session,
            @Valid @RequestBody HolidayRegDto holidayRegDto
    ) {
        SessionDto sessionDto = new SessionDto(session);

        return ResponseEntity.ok(new TritoResponse<>(holidayService.regHoliday(sessionDto, holidayRegDto)));
    }

    // 달력 데이터 조회
    @PostMapping("/getCalendarData")
    public ResponseEntity<TritoResponse<?>> getCalendarData(
            HttpSession session,
            @Valid @RequestBody HolidayGetDto holidayGetDto
    ){
        SessionDto sessionDto = new SessionDto(session);

        return ResponseEntity.ok(new TritoResponse<>(holidayService.getCalendarData(sessionDto, holidayGetDto)));
    }
}
