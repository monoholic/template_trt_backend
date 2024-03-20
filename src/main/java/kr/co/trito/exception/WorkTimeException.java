package kr.co.trito.exception;

import kr.co.trito.enums.WorkTimeErrorCode;
import lombok.Getter;

@Getter
public class WorkTimeException extends RuntimeException {
    private final WorkTimeErrorCode errorCode;

    public WorkTimeException(WorkTimeErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
