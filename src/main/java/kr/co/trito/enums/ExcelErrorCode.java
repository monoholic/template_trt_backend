package kr.co.trito.enums;

import org.springframework.http.HttpStatus;

import kr.co.trito.domain.response.ErrorResponse;
import lombok.Getter;

@Getter
public enum ExcelErrorCode implements BaseErrorCode {

    EXCEL_DATA_NULL(400, "값이 비어있습니다.", HttpStatus.BAD_REQUEST),
    EXCEL_DATA_TYPE_ERROR(400, "값은 숫자로 구성되어 있어야 합니다.", HttpStatus.BAD_REQUEST),
    EXCEL_DATA_LENGTH(400, "값의 길이가 맞지 않습니다.", HttpStatus.BAD_REQUEST);

    private final int errorCode;
    private final String errorMessage;
    private final HttpStatus status;

    ExcelErrorCode(int errorCode, String message, HttpStatus status) {
        this.errorCode = errorCode;
        this.errorMessage = message;
        this.status = status;
    }

    @Override
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(this.errorCode, this.errorMessage);
    }
}
