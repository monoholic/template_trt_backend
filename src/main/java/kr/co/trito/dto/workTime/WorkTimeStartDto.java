package kr.co.trito.dto.workTime;

import lombok.Builder;

@Builder
public record WorkTimeStartDto(
        String sawonNo,
        String sawonNm,
        String vStartDt,
        String vEndDt,
        String cause
) {
}
