package kr.co.trito.dto.workTime;

import lombok.Builder;

@Builder
public record OverTimeDto(
        String overTimeFlag,
        String weekGbn
) {
}
