package kr.co.trito.enums;

import kr.co.trito.domain.response.ErrorResponse;
import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    int getErrorCode();

    String getErrorMessage();

    HttpStatus getStatus();

    ErrorResponse getErrorResponse();
}
