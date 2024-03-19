package kr.co.trito.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.request.LoginDto;
import kr.co.trito.dto.response.LoginUserInfoDto;
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

    @PostMapping("/sysLogin")
    public ResponseEntity<TritoResponse<?>> login(
        @Valid @RequestBody LoginDto loginDto,
        HttpSession session
    ){
        LoginUserInfoDto login = loginService.getLogin(loginDto).get(0);

        session.setAttribute("USER_ID" , login.userId());
        session.setAttribute("USER_NM" , login.userNm());
        session.setAttribute("SAWON_NO", login.sawonNo());
        session.setAttribute("DEPT_CD" , login.deptCd());
        session.setAttribute("DEPT_NM" , login.deptNm());

        return ResponseEntity.ok(new TritoResponse<>(login));
    }
}
