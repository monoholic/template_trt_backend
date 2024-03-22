package kr.co.trito.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.SessionDto;
import kr.co.trito.dto.workTime.WorkTimeStartViewDto;
import kr.co.trito.service.WorkTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            @Valid @RequestBody WorkTimeStartViewDto workTimeStartViewDto,
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

        workTimeService.regWorkTimeEnd(sessionDto, acceptIp);

        return ResponseEntity.ok(new TritoResponse<>(""));
    }

    /**
     * 사유 등록
     */
    @PostMapping("/workTimeCause")
    public ResponseEntity<TritoResponse<?>> regWorkTimeCause(
        @Valid @RequestBody String cause,
        HttpSession session
    ) {
        SessionDto sessionDto = new SessionDto(session);

//        SessionDto sessionDto = SessionDto.builder()
//                .userId((String)session.getAttribute("USER_ID"))
//                .build();

        workTimeService.regWorkTimeCause(sessionDto, cause);

        return ResponseEntity.ok(new TritoResponse<>(""));
    }
}
