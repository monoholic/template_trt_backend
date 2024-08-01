package kr.co.trito.dto.holiday;

import lombok.Builder;

@Builder
public record HolidayDto(
        String sawonNm,
        String deptNm,
        String startDt,
        String endDt,
        String cause,
        String gubunCd
) {
}