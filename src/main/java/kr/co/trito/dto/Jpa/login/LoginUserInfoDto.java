package kr.co.trito.dto.Jpa.login;

public record LoginUserInfoDto(
        String userId,
        String password,
        String userNm,
        String userYn,
        String deptCd,
        String jikcheck,
        String useYn,
        String email,
        String telNo
        ) {
}
