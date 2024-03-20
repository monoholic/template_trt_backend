package kr.co.trito.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.request.WorkTimeCommuteDto;
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

    @PostMapping("/workTimeStartView")
    public ResponseEntity<TritoResponse<?>> workTimeCommute(
            @Valid @RequestBody WorkTimeCommuteDto workTimeCommuteDto,
            HttpSession session
            ) {
        String sawonNo = (String)session.getAttribute("SAWON_NO");

        workTimeService.getWorkStartView(sawonNo);
        return ResponseEntity.ok(new TritoResponse<>(""));
    }
}
