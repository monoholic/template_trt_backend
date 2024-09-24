package kr.co.trito.dto.Mybatis.role;

import lombok.Data;

@Data
public class RoleListDto {
    private String roleId;
    private String roleNm;
    private String roleDesc;
    private String useYn;
    private String uptId;
    private String uptDate;
    private String regId;
    private String regDate;
}
