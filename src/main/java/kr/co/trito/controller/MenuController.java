package kr.co.trito.controller;

import jakarta.servlet.http.HttpSession;
import kr.co.trito.domain.response.TritoResponse;
import kr.co.trito.dto.SessionDto;
import kr.co.trito.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    private final MenuService menuService;

    // 휴가 목록 조회
    @GetMapping("/menuList")
    public ResponseEntity<TritoResponse<?>> getMenuList(
            HttpSession session
            ) {
        SessionDto sessionDto = new SessionDto(session);

        return ResponseEntity.ok(new TritoResponse<>(menuService.getMenuList(sessionDto)));
    }

}
