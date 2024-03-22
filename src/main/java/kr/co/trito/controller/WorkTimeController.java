package kr.co.trito.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.SessionDto;
import kr.co.trito.dto.workTime.WorkTimeStarViewDto;
import kr.co.trito.service.WorkTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
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
            @Valid @RequestBody WorkTimeStarViewDto workTimeStarViewDto,
            HttpSession session
            ) {

        SessionDto sessionDto = SessionDto.builder()
                .sawonNo((String)session.getAttribute("SAWON_NO"))
                .build();

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
        String sawonNo = (String)session.getAttribute("SAWON_NO");
        String acceptIp = request.getRemoteAddr();

        return ResponseEntity.ok(new TritoResponse<>(workTimeService.regWorkTimeStart(sawonNo, acceptIp)));
    }

    /**
     * 퇴근 등록
     */
    @PostMapping("/workTimeEndWrite")
    public ResponseEntity<TritoResponse<?>> regWorkTimeEndWrite(
            HttpServletRequest request,
            HttpSession session
    ) {
        SessionDto sessionDto = SessionDto.builder()
                .sawonNo((String)session.getAttribute("SAWON_NO"))
                .userId((String)session.getAttribute("USER_ID"))
                .build();
        String acceptIp = request.getRemoteAddr();

        workTimeService.regWorkTimeEnd(sessionDto, acceptIp);

        return ResponseEntity.ok(new TritoResponse<>(""));
    }
}
