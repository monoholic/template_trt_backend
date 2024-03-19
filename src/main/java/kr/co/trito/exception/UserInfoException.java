package kr.co.trito.exception;

import kr.co.trito.enums.UserInfoErrorCode;
import lombok.Getter;

@Getter
public class UserInfoException extends RuntimeException {
    private final UserInfoErrorCode errorCode;

    public UserInfoException(UserInfoErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
