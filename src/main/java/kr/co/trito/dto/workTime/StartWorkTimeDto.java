package kr.co.trito.dto.workTime;

import lombok.Builder;

@Builder
public record StartWorkTimeDto(
        String sawonNo,
        String vstartDt,
        String vendDt,
        String cause
) {
    public static StartWorkTimeDto emptyWorkTime(){
        return StartWorkTimeDto.builder()
                .sawonNo("")
                .vstartDt("")
                .vendDt("")
                .cause("")
                .build();
    }
}
