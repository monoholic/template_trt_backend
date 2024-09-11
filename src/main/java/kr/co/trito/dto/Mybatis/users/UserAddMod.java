package kr.co.trito.dto.Mybatis.users;

import lombok.Data;

@Data
public class UserAddMod {
    private String userId;
    private String password;
    private String userNm;
    private String deptNm;
    private String jikgyub;
    private String role;
    private String email;
    private String telNo;
    private String gender;
    private String useYn;
    private String addMod;
}
