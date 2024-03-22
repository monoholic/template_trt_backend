package kr.co.trito.dto;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;

@Getter
public class SessionDto {
    private String userId;
    private String userNm;
    private String sawonNo;
    private String deptCd;
    private String deptNm;

    public SessionDto(HttpSession session) {
        this.userId = (String) session.getAttribute("USER_ID");
        this.userNm = (String) session.getAttribute("USER_NM");
        this.sawonNo = (String) session.getAttribute("SAWON_NO");
        this.deptCd = (String) session.getAttribute("DEPT_CD");
        this.deptNm = (String) session.getAttribute("DEPT_NM");
    }
}
