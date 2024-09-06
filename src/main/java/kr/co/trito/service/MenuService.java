package kr.co.trito.service;

import kr.co.trito.domain.repository.mybatis.menu.MenuRepository;
import kr.co.trito.dto.Mybatis.menu.MenuDto;
import kr.co.trito.dto.SessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MenuService {

    private final MenuRepository menuRepository;

    // 메뉴 목록 조회
    public List<MenuDto> getMenuList(SessionDto sessionDto) {
        return menuRepository.getMenuList();
    }

}