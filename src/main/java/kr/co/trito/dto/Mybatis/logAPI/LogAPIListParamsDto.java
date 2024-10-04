package kr.co.trito.dto.Mybatis.logAPI;

import lombok.Data;

@Data
public class LogAPIListParamsDto {
    private String sortBy; // 정렬 컬럼
    private String descending; // 내림차순 오름차순
    private String page; // 페이지
    private String numOfRows; // 페이지 당 row 수
    // 검색
    private String userId; // 사용자 검색
    private String dateFrom; // 조회 시작일
    private String dateTo; // 조회 종료일
}
