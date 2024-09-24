package kr.co.trito.controller;

import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.Mybatis.comCode.ComCodeAddModDto;
import kr.co.trito.dto.Mybatis.comCode.ComCodeGrpAddModDto;
import kr.co.trito.dto.Mybatis.comCode.ComCodeGrpListParamsDto;
import kr.co.trito.dto.Mybatis.comCode.ComCodeListParamDto;
import kr.co.trito.service.ComCodeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/comCode")
public class ComCodeController {

    @Autowired
    private final ComCodeService comCodeService;

    // ------------------ 공통코드 그룹 관리 ---------------------------

    // 공통코드 그룹 목록 조회
    @PostMapping("/getComCodeGrpList")
    public ResponseEntity<TritoResponse<?>> getComCodeGrpList(
            @Valid @RequestBody ComCodeGrpListParamsDto comCodeGrpListParamsDto
    ){
        return ResponseEntity.ok(new TritoResponse<>(comCodeService.getComCodeGrpList(comCodeGrpListParamsDto)));
    }

    // 공통코드 그룹 추가, 수정
    @PostMapping("/grpAddMod")
    public ResponseEntity<TritoResponse<?>> addModComCodeGrp(
            @Valid @RequestBody ComCodeGrpAddModDto comCodeGrpAddModDto
    ){
        return ResponseEntity.ok(new TritoResponse<>(comCodeService.addModComCodeGrp(comCodeGrpAddModDto)));
    }

    // 공통코드 그룹 삭제
    @PostMapping("/grpDel")
    public ResponseEntity<TritoResponse<?>> delComCodeGrp(
            @Valid @RequestBody List<String> params
    ){
        return ResponseEntity.ok(new TritoResponse<>(comCodeService.delComCodeGrp(params)));
    }

    // ------------------ 공통코드 관리 ---------------------------

    // 공통코드 목록 조회
    @PostMapping("/getComCodeList")
    public ResponseEntity<TritoResponse<?>> getComCodeList(
            @Valid @RequestBody ComCodeListParamDto comCodeListParamDto
    ){
        return ResponseEntity.ok(new TritoResponse<>(comCodeService.getComCodeList(comCodeListParamDto)));
    }

    // 공통코드 그룹 추가, 수정
    @PostMapping("/codeAddMod")
    public ResponseEntity<TritoResponse<?>> addModComCode(
            @Valid @RequestBody ComCodeAddModDto comCodeAddModDto
            ){
        return ResponseEntity.ok(new TritoResponse<>(comCodeService.addModComCode(comCodeAddModDto)));
    }

    // 공통코드 그룹 삭제
    @PostMapping("/codeDel")
    public ResponseEntity<TritoResponse<?>> delComCode(
            @Valid @RequestBody Object params
    ){
        return ResponseEntity.ok(new TritoResponse<>(comCodeService.delComCode(params)));
    }
}
