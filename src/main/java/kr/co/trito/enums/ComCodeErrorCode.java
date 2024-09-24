package kr.co.trito.enums;

import kr.co.trito.domain.response.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ComCodeErrorCode implements BaseErrorCode {

    CODE_GRP_ID_DUPLICATION(400, "이미 그룹코드 아이디가 있습니다.", HttpStatus.BAD_REQUEST),
    CODE_ID_DUPLICATION(400, "이미 코드 아이디가 있습니다.", HttpStatus.BAD_REQUEST);

    private final int errorCode;
    private final String errorMessage;
    private final HttpStatus status;

    ComCodeErrorCode(int errorCode, String message, HttpStatus status) {
        this.errorCode = errorCode;
        this.errorMessage = message;
        this.status = status;
    }

    @Override
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(this.errorCode, this.errorMessage);
    }
}
