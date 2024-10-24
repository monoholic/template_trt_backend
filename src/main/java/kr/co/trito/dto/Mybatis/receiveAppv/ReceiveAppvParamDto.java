package kr.co.trito.dto.Mybatis.receiveAppv;

import kr.co.trito.utils.SearchCondition;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class ReceiveAppvParamDto {
    private SearchCondition condition;
    private ReceiveAppvListParamDto receiveAppvListParamDto;
}
