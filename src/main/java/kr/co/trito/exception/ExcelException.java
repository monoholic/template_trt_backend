package kr.co.trito.exception;

import org.springframework.http.HttpStatusCode;

import kr.co.trito.enums.ExcelErrorCode;
import lombok.Getter;

@Getter
public class ExcelException extends RuntimeException {
     public static HttpStatusCode getErrorResponse;
	private final ExcelErrorCode errorCode;

    public ExcelException(ExcelErrorCode errorCode){
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }

    
    
}
