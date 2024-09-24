package kr.co.trito.dto.Mybatis.comCode;

import kr.co.trito.utils.SearchCondition;
import lombok.Data;

@Data
public class ComCodeParamsDto {
    private ComCodeListParamDto comCodeListParamDto;
    private SearchCondition condition;
}
