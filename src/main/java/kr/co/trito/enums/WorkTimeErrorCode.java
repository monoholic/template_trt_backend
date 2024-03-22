package kr.co.trito.enums;

import kr.co.trito.domain.response.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum WorkTimeErrorCode implements BaseErrorCode {
    NOT_MATCH_WORK_TIME(400, "WORK_TIME 정상적으로 가져오지 못했습니다.", HttpStatus.BAD_REQUEST),
    NOT_WORK_TIME_START_REGISTERED(400, "오늘 출근 등록한 기록이 없습니다.", HttpStatus.BAD_REQUEST),
    CAUSE_NOT_REGISTERED(400, "정상적으로 사유가 등록되지 않았습니다.", HttpStatus.BAD_REQUEST);

    private final int errorCode;
    private final String errorMessage;
    private final HttpStatus status;

    WorkTimeErrorCode(int errorCode, String message, HttpStatus status) {
        this.errorCode = errorCode;
        this.errorMessage = message;
        this.status = status;
    }

    @Override
    public ErrorResponse getErrorResponse() {
        return new ErrorResponse(this.errorCode, this.errorMessage);
    }
}
