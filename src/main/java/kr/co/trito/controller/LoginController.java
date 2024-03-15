package kr.co.trito.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.request.LoginDto;
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

    @PostMapping("/login")
    public ResponseEntity<TritoResponse<?>> login(
        @Valid @RequestBody LoginDto loginDto,
        HttpSession session
    ){
        loginService.getLogin(loginDto);
        return ResponseEntity.ok(new TritoResponse<>("1"));


//        session.setAttribute("LoginUser", );
//        return ResponseEntity.ok(new BfResponse<>(memberService.login(memberLoginDto)));
    }

}
