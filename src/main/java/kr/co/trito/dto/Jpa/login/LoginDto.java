package kr.co.trito.dto.Jpa.login;

import lombok.Builder;

@Builder
public record LoginDto(
    String userId,
    String passwd
) {

}
