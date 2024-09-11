package kr.co.trito.dto.Mybatis.comCode;

import kr.co.trito.utils.SearchCondition;
import lombok.Data;

@Data
public class ComCodeGrpParmsDto {
    private ComCodeGrpListParamsDto comCodeGrpListParamsDto;
    private SearchCondition condition;
}
