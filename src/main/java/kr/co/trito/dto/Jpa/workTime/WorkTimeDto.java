package kr.co.trito.dto.Jpa.workTime;

import lombok.Builder;

@Builder
public record WorkTimeDto(
    String sawonNo,
    String vstartDt,
    String vendDt,
    String cause
) {
}
