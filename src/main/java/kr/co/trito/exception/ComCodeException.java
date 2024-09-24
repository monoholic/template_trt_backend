package kr.co.trito.exception;

import kr.co.trito.enums.ComCodeErrorCode;
import lombok.Getter;

@Getter
public class ComCodeException extends RuntimeException{
    private final ComCodeErrorCode errorCode;

    public ComCodeException(ComCodeErrorCode errorCode){
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
}
