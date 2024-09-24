package kr.co.trito.dto.Mybatis.role;

import lombok.Data;

@Data
public class UserRoleListDto {
    private String userId;
    private String roleId;
    private String useYn;
    private String uptId;
    private String uptDate;
    private String regId;
    private String regDate;
}
