package kr.co.trito.dto.Mybatis.users;

import lombok.Data;

@Data
public class UserListDto {
    private String userId;
    private String userNm;
    private String deptNm;
    private String jikgyub;
    private String role;
    private String email;
    private String telNo;
    private String gender;
    private String useYn;
    private String regId;
    private String regDate;
    private String uptId;
    private String uptDate;
}
