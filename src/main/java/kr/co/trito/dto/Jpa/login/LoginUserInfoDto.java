package kr.co.trito.dto.Jpa.login;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record LoginUserInfoDto(
        String userId,
        @JsonIgnore
        String passwd,
        String userNm,
        String sawonNo,
        @JsonIgnore
        String userYn,
        String deptCd,
        String deptNm
) {
}
