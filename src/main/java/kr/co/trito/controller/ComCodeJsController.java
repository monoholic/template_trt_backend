package kr.co.trito.controller;

import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.service.ComCodeJsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/comCodeJs")
public class ComCodeJsController {

    private final ComCodeJsService comCodeJsService;

    // 공통코드 opt 불러오기
    @PostMapping("/getOpt")
    public ResponseEntity<TritoResponse<?>> getComCodeJsOpt(
            @Valid @RequestBody String codeGrpId
    ){
        return ResponseEntity.ok(new TritoResponse<>(comCodeJsService.getComCodeJsOpt(codeGrpId)));
//        return null;
    }

}
