package kr.co.trito.dto.Mybatis.users;

import lombok.Data;

@Data
public class UserListParamsDto {
    private String sortBy; // 정렬 컬럼
    private String descending; // true 내림차순 false 오름차순
    private String page; // 페이지
    private String numOfRows; // 페이지 당 row 수
    // 검색
    private String userId;
    private String userNm;
}
