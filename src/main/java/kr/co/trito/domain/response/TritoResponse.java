package kr.co.trito.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.co.trito.domain.common.enums.GlobalSuccessCode;
import lombok.Getter;

import static kr.co.trito.domain.common.enums.GlobalSuccessCode.SUCCESS;

@Getter
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class TritoResponse<T> {
    private int code;
    private String message;
    private T data;

    public TritoResponse(T data) {
        this.code = SUCCESS.getCode();
        this.message = SUCCESS.getMessage();
        this.data = data;
    }

    public TritoResponse(GlobalSuccessCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
        this.data = data;
    }
}