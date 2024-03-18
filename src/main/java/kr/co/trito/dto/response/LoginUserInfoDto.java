package kr.co.trito.dto.response;

public record LoginUserInfoDto(
        String userNm,
        String sawonNo,
        String deptCd,
        String deptNm
) {

}
