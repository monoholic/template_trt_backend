package kr.co.trito.dto.Mybatis.excel;

import lombok.Data;

@Data
public class ExcelListParamDto {
    private String sortBy; // 정렬 컬럼
    private String descending; // true 내림차순 false 오름차순
    private String page; // 페이지
    private String numOfRows; // 페이지 당 row 수
    
    // 검색
    public String sawonNo;
    public String sawonNm;
}