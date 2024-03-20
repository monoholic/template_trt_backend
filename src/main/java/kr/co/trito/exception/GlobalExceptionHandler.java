package kr.co.trito.exception;

import kr.co.trito.domain.response.ErrorResponse;
import kr.co.trito.enums.UserInfoErrorCode;
import kr.co.trito.enums.WorkTimeErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static kr.co.trito.enums.GlobalErrorCode.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        log.error(">>>>> Internal Server Error : {}", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INTERNAL_SERVER_ERROR.getErrorResponse());
    }

    @ExceptionHandler(UserInfoException.class)
    protected ResponseEntity<ErrorResponse> handleMemberException(UserInfoException ex) {
        log.warn(">>>>> UserInfoException : {}", ex);
        UserInfoErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus()).body(errorCode.getErrorResponse());
    }

    @ExceptionHandler(WorkTimeException.class)
    protected ResponseEntity<ErrorResponse> handleWorkTimeException(WorkTimeException ex) {
        log.warn(">>>>> WorkTimeException : {}", ex);
        WorkTimeErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus()).body(errorCode.getErrorResponse());
    }
}
