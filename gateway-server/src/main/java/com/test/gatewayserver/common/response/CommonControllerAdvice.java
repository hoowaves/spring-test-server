package com.test.gatewayserver.common.response;

import com.test.gatewayserver.common.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CommonControllerAdvice {

    /**
     * http status: 500 AND result: FAIL
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public CommonResponse<Void> onException(Exception e) {
        return CommonResponse.fail("내부 서버 에러");
    }

    /**
     * BaseException
     */
    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<CommonResponse<Void>> handleBaseException(BaseException e) {
        return new ResponseEntity<>(CommonResponse.fail(e.getMessage()), e.getStatus());
    }

}
