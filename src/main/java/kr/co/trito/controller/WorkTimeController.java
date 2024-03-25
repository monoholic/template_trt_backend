package kr.co.trito.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.SessionDto;
import kr.co.trito.dto.workTime.WorkTimeCauseDto;
import kr.co.trito.dto.workTime.WorkTimeStartViewDto;
import kr.co.trito.service.WorkTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkTimeController {
    private final WorkTimeService workTimeService;

    /**
     * 출근 조회
     */
    @PostMapping("/workTimeStartView")
    public ResponseEntity<TritoResponse<?>> getWorkTimeStarView(
            HttpSession session
    ) {
        SessionDto sessionDto = new SessionDto(session);

        return ResponseEntity.ok(new TritoResponse<>(workTimeService.getWorkStartView(sessionDto)));
    }

    /**
     * 출근 등록
     */
    @PostMapping("/workTimeStartWrite")
    public ResponseEntity<TritoResponse<?>> regWorkTimeStartWrite(
            HttpServletRequest request,
            HttpSession session
    ) {
        SessionDto sessionDto = new SessionDto(session);
        String acceptIp = request.getRemoteAddr();

        return ResponseEntity.ok(new TritoResponse<>(workTimeService.regWorkTimeStart(sessionDto, acceptIp)));
    }

    /**
     * 퇴근 등록
     */
    @PostMapping("/workTimeEndWrite")
    public ResponseEntity<TritoResponse<?>> regWorkTimeEndWrite(
            HttpServletRequest request,
            HttpSession session
    ) {
        SessionDto sessionDto = new SessionDto(session);
        String acceptIp = request.getRemoteAddr();

        return ResponseEntity.ok(new TritoResponse<>(workTimeService.regWorkTimeEnd(sessionDto, acceptIp)));
    }

    /**
     * 사유 등록
     */
    @PostMapping("/workTimeCause")
    public ResponseEntity<TritoResponse<?>> regWorkTimeCause(
            @Valid @RequestBody WorkTimeCauseDto workTimeCauseDto,
            HttpSession session
    ) {
        SessionDto sessionDto = new SessionDto(session);
        String cause = workTimeCauseDto.cause();

        return ResponseEntity.ok(new TritoResponse<>(workTimeService.regWorkTimeCause(sessionDto, cause)));
    }

    /**
     * 전날 퇴근 등록
     */
    @PostMapping("/workTimePreEndWrite")
    public ResponseEntity<TritoResponse<?>> regWorkTimePreEndWrite(
            HttpServletRequest request,
            HttpSession session
    ) {
        SessionDto sessionDto = new SessionDto(session);
        String acceptIp = request.getRemoteAddr();

        return ResponseEntity.ok(new TritoResponse<>(workTimeService.regWorkTimePreEnd(sessionDto, acceptIp)));
    }
}
