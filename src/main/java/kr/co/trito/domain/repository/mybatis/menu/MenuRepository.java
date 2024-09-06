package kr.co.trito.domain.repository.mybatis.menu;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trito.dto.Mybatis.menu.MenuDto;


@Mapper
public interface MenuRepository {
    
    List<MenuDto> getMenuList();
}
