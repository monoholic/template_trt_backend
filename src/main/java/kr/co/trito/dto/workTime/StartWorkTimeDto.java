package kr.co.trito.dto.workTime;

import lombok.Builder;

@Builder
public record StartWorkTimeDto(
        String sawonNo,
        String vStartDt,
        String vEndDt,
        String cause
) {
}
