package kr.co.trito.dto.Mybatis.role;

import kr.co.trito.utils.SearchCondition;
import lombok.Data;

@Data
public class UserRoleParamsDto {
    private UserRoleListParamsDto userRoleListParamsDto;
    private SearchCondition condition;
}
