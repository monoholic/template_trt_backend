package kr.co.trito.dto.Mybatis.users;

public record UserInfoDto(
        String userId,
        String userNm,
        String deptCd,
        String jikgyub,
        String email,
        String useYn,
        String telNo
) {
}
