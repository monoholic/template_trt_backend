package kr.co.trito.dto.Mybatis.comCode;

import lombok.Data;

@Data
public class ComCodeGrpListParamsDto {
    private String sortBy;
    private String descending;
    private String page; // 페이지
    private String numOfRows; // 페이지 당 row 수
    // 검색
    private String searchTxt;
    private String searchOpt;
    private String searchUseYn;
}
