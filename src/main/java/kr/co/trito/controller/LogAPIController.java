package kr.co.trito.controller;

import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.service.LogAPIService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logManage")
@AllArgsConstructor
public class LogAPIController {

    private final LogAPIService logAPIService;

    // 메뉴 진입시 로그 추가
    @PostMapping("/addLog")
    public ResponseEntity<TritoResponse<?>> addLog(
            @Valid @RequestBody String pagePath
    ){
        return ResponseEntity.ok(new TritoResponse<>(logAPIService.addLog(pagePath)));
    }

}
