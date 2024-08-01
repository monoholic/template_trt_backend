package kr.co.trito.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.SessionDto;
import kr.co.trito.dto.holiday.HolidayRegDto;
import kr.co.trito.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HolidayController {
    private final HolidayService holidayService;

    @GetMapping("/getHolidayList")
    public ResponseEntity<TritoResponse<?>> getHolidayList(
            HttpSession session,
            @RequestParam int year
    ){
        SessionDto sessionDto = new SessionDto(session);

        return ResponseEntity.ok(new TritoResponse<>(holidayService.getHolidayList(sessionDto, year)));
    }

    @PostMapping("/regHoliday")
    public ResponseEntity<TritoResponse<?>> regHoliday(
            HttpSession session,
            @Valid @RequestBody HolidayRegDto holidayRegDto
    ) {
        SessionDto sessionDto = new SessionDto(session);

        return ResponseEntity.ok(new TritoResponse<>(holidayService.regHoliday(sessionDto, holidayRegDto)));
    }
}
