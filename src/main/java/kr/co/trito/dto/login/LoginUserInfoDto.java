package kr.co.trito.dto.login;

public record LoginUserInfoDto(
        String userId,
        String userNm,
        String sawonNo,
        String deptCd,
        String deptNm
) {

}
