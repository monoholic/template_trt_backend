package kr.co.trito.controller;


import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.Mybatis.users.UserInfoDto;
import kr.co.trito.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/getUserInfo")
    public ResponseEntity<TritoResponse<?>> getUserInfo(@Valid @RequestBody UserInfoDto userInfoDto)
    {
        return ResponseEntity.ok(new TritoResponse<>(loginService.getUserInfo(userInfoDto)));
    }
}
