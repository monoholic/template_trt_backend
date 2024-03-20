package kr.co.trito.dto.response;

import lombok.Builder;

@Builder
public record WorkTimeDto(
    String sawonNo,
    String vstartDt,
    String vendDt,
    String cause
) {
}
