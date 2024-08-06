package kr.co.trito.exception;

import kr.co.trito.enums.HolidayErrorCode;
import lombok.Getter;

@Getter
public class HolidayException extends RuntimeException {
    private final HolidayErrorCode errorCode;

    public HolidayException(HolidayErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
