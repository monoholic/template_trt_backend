package kr.co.trito.dto.Jpa.workTime;

import lombok.Builder;

@Builder
public record OverTimeDto(
        String overTimeFlag,
        String weekGbn
) {
}
