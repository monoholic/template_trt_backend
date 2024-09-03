package kr.co.trito.dto.Mybatis.users;

import lombok.Data;

@Data
public class UserListParamsDto {
    private String sortBy; // 정렬 컬럼
    private boolean descending; // true 내림차순 false 오름차순
    private int page; // 페이지
    // 검색
    private String userId;
    private String userNm;
}
