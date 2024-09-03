package kr.co.trito.controller;

import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.Mybatis.users.UserListParamsDto;
import kr.co.trito.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/userMng")
public class UserController {

    @Autowired
    private static UserService userService;

    @PostMapping("/list")
    public ResponseEntity<TritoResponse<?>> getUserList(
            @Valid @RequestBody UserListParamsDto userListParamsDto
    ){
        return ResponseEntity.ok(new TritoResponse<>(userService.getUserList(userListParamsDto)));
    }

}
