package kr.co.trito.dto.Mybatis.role;

import lombok.Data;

@Data
public class UserRoleAddModDto {
    private String userId;
    private String roleId;
    private String useYn;
    private String addMod;
}
