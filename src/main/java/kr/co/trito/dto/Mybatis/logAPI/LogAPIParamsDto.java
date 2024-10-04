package kr.co.trito.dto.Mybatis.logAPI;

import kr.co.trito.utils.SearchCondition;
import lombok.Data;

@Data
public class LogAPIParamsDto {
    private LogAPIListParamsDto logAPIListParamsDto;
    private SearchCondition condition;
}
