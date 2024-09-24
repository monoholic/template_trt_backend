package kr.co.trito.dto.Mybatis.comCode;

import kr.co.trito.utils.SearchCondition;
import lombok.Data;

@Data
public class ComCodeGrpParamsDto {
    private ComCodeGrpListParamsDto comCodeGrpListParamsDto;
    private SearchCondition condition;
}
