package kr.co.trito.dto.login;

import lombok.Builder;

@Builder
public record LoginDto(
    String userId,
    String passwd
) {

}
