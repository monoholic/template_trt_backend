package kr.co.trito.enums;

import kr.co.trito.domain.response.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserInfoErrorCode implements BaseErrorCode {
    USER_ID_DUPLICATION(400, "이미 아이디가 있습니다.", HttpStatus.BAD_REQUEST),
    NOT_MATCH_USER_INFO(400, "아이디 또는 비밀번호를 확인해주세요.", HttpStatus.BAD_REQUEST);

    private final int errorCode;
    private final String errorMessage;
    private final HttpStatus status;

    UserInfoErrorCode(int errorCode, String message, HttpStatus status) {
        this.errorCode = errorCode;
        this.errorMessage = message;
        this.status = status;
    }

    @Override
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(this.errorCode, this.errorMessage);
    }
}
