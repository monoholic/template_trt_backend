package kr.co.trito.enums;

import kr.co.trito.domain.response.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum HolidayErrorCode implements BaseErrorCode {
    HOLIDAY_DATE_DUPLICATION(400, "입력한 일자가 기존 휴가기간과 겹칩니다.", HttpStatus.BAD_REQUEST),
    NOT_REG_HOLIDAY(500, "통신문제로 인해 휴가입력이 취소되었습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int errorCode;
    private final String errorMessage;
    private final HttpStatus status;

    HolidayErrorCode(int errorCode, String message, HttpStatus status) {
        this.errorCode = errorCode;
        this.errorMessage = message;
        this.status = status;
    }

    @Override
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(this.errorCode, this.errorMessage);
    }
}
