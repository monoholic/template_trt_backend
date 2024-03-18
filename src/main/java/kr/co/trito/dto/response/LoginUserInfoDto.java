package kr.co.trito.dto.response;

public record LoginUserInfoDto(
        String userId,
        String userNm,
        String sawonNo,
        String deptCd,
        String deptNm
) {

}
