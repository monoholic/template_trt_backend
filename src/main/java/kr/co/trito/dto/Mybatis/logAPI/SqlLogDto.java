package kr.co.trito.dto.Mybatis.logAPI;

import lombok.Data;

@Data
public class SqlLogDto {
    private String menuId;
    private String gubun;
    private String gubunDet;
    private String sqlTxt;
    private String userId;
}
