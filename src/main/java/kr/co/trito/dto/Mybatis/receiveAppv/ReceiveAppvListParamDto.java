package kr.co.trito.dto.Mybatis.receiveAppv;

import lombok.Data;

@Data
public class ReceiveAppvListParamDto {
    private String sortBy; // 정렬 컬럼
    private String descending; // true 내림차순 false 오름차순
    private String page; // 페이지
    private String numOfRows; // 페이지 당 row 수

    // 검색
    public String updNm;
    public String appvType;
}
