package kr.co.trito.exception;

import kr.co.trito.enums.RoleErrorCode;
import lombok.Getter;

@Getter
public class RoleException extends RuntimeException {
    private final RoleErrorCode errorCode;

    public RoleException(RoleErrorCode errorCode){
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
