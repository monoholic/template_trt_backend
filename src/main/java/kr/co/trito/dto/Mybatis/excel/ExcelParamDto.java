package kr.co.trito.dto.Mybatis.excel;

import kr.co.trito.utils.SearchCondition;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ExcelParamDto {
    private SearchCondition condition;
    private ExcelListParamDto excelListParamDto;
}