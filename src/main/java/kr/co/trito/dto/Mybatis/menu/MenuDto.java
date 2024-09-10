package kr.co.trito.dto.Mybatis.menu;

import lombok.Data;

@Data
public class MenuDto{
    private String menuId;
    private String menuNm;
    private String uppMenuId;
    private String menuLvl;
    private String url;
    private String menuDesc;
    private String routeYn;
    private String menuIcon;
}