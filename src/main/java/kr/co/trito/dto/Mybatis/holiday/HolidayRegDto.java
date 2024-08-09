package kr.co.trito.dto.Mybatis.holiday;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HolidayRegDto {
    String sawonNo;
    String startDt;
    String endDt;
    String cause;
    String gubunCd;
}
