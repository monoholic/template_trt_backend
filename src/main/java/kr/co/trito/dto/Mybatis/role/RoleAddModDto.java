package kr.co.trito.dto.Mybatis.role;

import lombok.Data;

@Data
public class RoleAddModDto {
    private String roleId;
    private String roleNm;
    private String roleDesc;
    private String useYn;
    private String addMod;
}
