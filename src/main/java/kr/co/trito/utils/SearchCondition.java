package kr.co.trito.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class SearchCondition {
    private int currentPage; //현재페이지
    private int totalCount;	//조회 전체수
    private int start;	//쿼리 조회 시작 번호
    private int end;	//쿼리 조회 마지막 번호
    private int numOfRows;	//페이지당 글 목록수

    public SearchCondition() {}

    public SearchCondition(String currentPage, String numOfRows) {
        this.currentPage = Integer.parseInt(currentPage);
        this.numOfRows = Integer.parseInt(numOfRows);
    }

    public void pageSetup(int totalCount) {
        setTotalCount(totalCount);
        calcStartEnd();
    }

    // DB 쿼리에서 사용할 start, end값 계산
    public void calcStartEnd() {
        this.end = this.currentPage * this.numOfRows;
        this.start = this.end - this.numOfRows + 1;
    }
}
