package kr.co.trito.controller;

import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.Mybatis.users.UserAddMod;
import kr.co.trito.dto.Mybatis.users.UserListParamsDto;
import kr.co.trito.service.UserService;
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
@RequestMapping("/userMng")
public class UserController {

    @Autowired
    private final UserService userService;

    // 사용자 목록 조회
    @PostMapping("/list")
    public ResponseEntity<TritoResponse<?>> getUserList(
            @Valid @RequestBody UserListParamsDto userListParamsDto
    ){

        return ResponseEntity.ok(new TritoResponse<>(userService.getUserList(userListParamsDto)));
    }

    // 사용자 추가
    @PostMapping("/addMod")
    public ResponseEntity<TritoResponse<?>> addModUser(
            @Valid @RequestBody UserAddMod userAddMod
    ){
        return ResponseEntity.ok(new TritoResponse<>(userService.addModUser(userAddMod)));
    }

    // 사용자 삭제
    @PostMapping("/del")
    public ResponseEntity<TritoResponse<?>> delUser(
            @Valid @RequestBody List<String> params
    ){
        return ResponseEntity.ok(new TritoResponse<>(userService.delUser(params)));
    }
}
