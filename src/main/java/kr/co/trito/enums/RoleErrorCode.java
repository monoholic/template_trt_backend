package kr.co.trito.enums;

import kr.co.trito.domain.response.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum RoleErrorCode implements BaseErrorCode {

    ROLE_ID_DUPLICATION(400, "이미 Role 아이디가 있습니다.",HttpStatus.BAD_REQUEST),
    USER_ROLE_ID_DUPLICATION(400, "이미 같은 역할의 사용자Id가 있습니다.",HttpStatus.BAD_REQUEST);

    private final int errorCode;
    private final String errorMessage;
    private final HttpStatus status;

    RoleErrorCode(int errorCode, String message, HttpStatus status) {
        this.errorCode = errorCode;
        this.errorMessage = message;
        this.status = status;
    }

    @Override
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(this.errorCode, this.errorMessage);
    }
}
