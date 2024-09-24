package kr.co.trito.dto.Mybatis.role;

import lombok.Data;

@Data
public class UserRoleListParamsDto {
    private String sortBy; // 정렬 컬럼
    private String descending; // 내림차순 오름차순
    private String page; // 페이지
    private String numOfRows; // 페이지 당 row 수
    // 검색
    private String searchTxt; // 검색어
    private String searchUseYn; // 사용 유무
    // 선택된 역할
    private String selectedRoleId;
}
