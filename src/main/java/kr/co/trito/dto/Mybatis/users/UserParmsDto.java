package kr.co.trito.dto.Mybatis.users;

import kr.co.trito.utils.SearchCondition;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserParmsDto {
    private SearchCondition condition;
    private UserListParamsDto userListParamsDto;
}
