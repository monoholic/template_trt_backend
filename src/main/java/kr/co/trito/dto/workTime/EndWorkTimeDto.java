package kr.co.trito.dto.workTime;

import lombok.Builder;

@Builder
public record EndWorkTimeDto(
        String sawonNo,
        String vstartDt,
        String vendDt
) {

    public static EndWorkTimeDto emptyWorkTime(){
        return EndWorkTimeDto.builder()
                .sawonNo("")
                .vstartDt("")
                .vendDt("")
                .build();
    }
}
