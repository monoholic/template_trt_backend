package kr.co.trito.dto.Mybatis.role;

import kr.co.trito.utils.SearchCondition;
import lombok.Data;

@Data
public class RoleParamsDto {
    private RoleListParamsDto roleListParamsDto;
    private SearchCondition condition;
}
