package kr.co.trito.dto.workTime;

import lombok.Builder;

@Builder
public record ExpensesDto(
        String sawonNo,
        String reqGbn,
        String userId,
        String acceptIp
) {

}
