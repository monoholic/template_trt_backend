package kr.co.trito.controller;

import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.Mybatis.logAPI.LogAPIListParamsDto;
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

    // 로그 관리 조회
    @PostMapping("/getLogList")
    public ResponseEntity<TritoResponse<?>> getLogList(
            @Valid @RequestBody LogAPIListParamsDto logAPIListParamsDto
    ){
        return ResponseEntity.ok(new TritoResponse<>(logAPIService.getLogList(logAPIListParamsDto)));
    }

}
