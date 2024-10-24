package kr.co.trito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.Mybatis.receiveAppv.ReceiveAppvListDto;
import kr.co.trito.dto.Mybatis.receiveAppv.ReceiveAppvListParamDto;
import kr.co.trito.dto.Mybatis.receiveAppv.ReceiveAppvUserListDto;
import kr.co.trito.service.ReceiveAppvService;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/receiveApproval")

public class ReceiveAppvController {
    @Autowired
    private final ReceiveAppvService receiveAppvService;

    // 조회
    @PostMapping("/list")
    public ResponseEntity<TritoResponse<?>> getAppvList(
        @Valid @RequestBody ReceiveAppvListParamDto receiveAppvListParamDto
    ) {
        return ResponseEntity.ok(new TritoResponse<>(receiveAppvService.getReceiveAppvList(receiveAppvListParamDto)));
    }

    // 결재 분류 조회
    @PostMapping("/typeList")
    public ResponseEntity<TritoResponse<?>> getTypeList(
        ReceiveAppvListDto receiveAppvListDto
    ){
        return ResponseEntity.ok(new TritoResponse<>(receiveAppvService.getTypeList(receiveAppvListDto)));
    }

    // 승인 담당자 조회
    @PostMapping("/userList")
    public ResponseEntity<TritoResponse<?>> getUserList(
        ReceiveAppvUserListDto receiveAppvUserListDto
    ){
        return ResponseEntity.ok(new TritoResponse<>(receiveAppvService.getUserList(receiveAppvUserListDto)));
    }

    // 결재 작성(추가)
    @PostMapping("/addApproval")
    public ResponseEntity<TritoResponse<?>> addApproval(
        @Valid @RequestBody ReceiveAppvListDto receiveAppvListDto
    ) {
        return ResponseEntity.ok(new TritoResponse<>(receiveAppvService.addApproval(receiveAppvListDto)));
    }
    
}
