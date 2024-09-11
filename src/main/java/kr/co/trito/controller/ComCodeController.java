package kr.co.trito.controller;

import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.Mybatis.comCode.ComCodeGrpListParamsDto;
import kr.co.trito.service.ComCodeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/comCode")
public class ComCodeController {

    @Autowired
    private final ComCodeService comCodeService;

    // 공통코드 그룹 목록 조회
    @PostMapping("/getComCodeGrpList")
    public ResponseEntity<TritoResponse<?>> getComCodeGrpList(
            @Valid @RequestBody ComCodeGrpListParamsDto comCodeGrpListParamsDto
    ){
        System.out.println(" ===> comCodeGrpListParamsDto::: " + comCodeGrpListParamsDto);
        return ResponseEntity.ok(new TritoResponse<>(comCodeService.getComCodeGrpList(comCodeGrpListParamsDto)));
    }
}
