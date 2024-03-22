package kr.co.trito.dto;

import lombok.Builder;

@Builder
public record SessionDto(
        String userId,
        String userNm,
        String sawonNo,
        String deptCd,
        String deptNm
) {
}
