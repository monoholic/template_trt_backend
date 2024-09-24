package kr.co.trito.dto.Mybatis.comCode;

import lombok.Data;

@Data
public class ComCodeListParamDto {
    private String sortBy; // 정렬 컬럼
    private String descending; // 내림차순 오름차순
    private String page; // 페이지
    private String numOfRows; // 페이지 당 row 수
    // 검색
    private String searchTxt; // 검색어
    private String searchOpt; // 검색 조건
    private String searchUseYn; // 사용 유무
    private String selectedGrpId; // 선택된 공통코드 그룹 ID
}
