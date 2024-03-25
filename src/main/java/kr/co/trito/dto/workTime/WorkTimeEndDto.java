package kr.co.trito.dto.workTime;

import lombok.Builder;

@Builder
public record WorkTimeEndDto(
        String sawonNo,
        String sawonNm,
        String vStartDt,
        String vEndDt
) {
}
