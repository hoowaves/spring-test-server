package com.test.userservice.common.response;

import com.test.userservice.common.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

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
     * http status: 400 AND result: FAIL
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public CommonResponse<Void> onBadRequest(Exception e) {
        return CommonResponse.fail("잘못된 요청방식입니다.");
    }

    /**
     * http status: 422 AND result: FAIL
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public CommonResponse<Void> handleValidationExceptions(MethodArgumentNotValidException e) {
//        String errorMessages = e.getBindingResult().getAllErrors().stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .collect(Collectors.joining(", "));

//        return CommonResponse.fail(e.getBindingResult().getAllErrors().getFirst().getDefaultMessage());
        return CommonResponse.fail(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * BaseException
     */
    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<CommonResponse<Void>> handleBaseException(BaseException e) {
        return new ResponseEntity<>(CommonResponse.fail(e.getMessage()), e.getStatus());
    }

    /**
     * http status: 404
     * */
    @ExceptionHandler(NoResourceFoundException.class)
    public ModelAndView handleNoResourceFoundException(NoResourceFoundException e) {
        return new ModelAndView("redirect:/swagger-ui/index.html");
    }

    /**
     * http status: 400
     * */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public CommonResponse<Void> handleNotReadable(HttpMessageNotReadableException e) {
        return CommonResponse.fail("잘못된 요청입니다. 데이터를 읽을 수 없습니다.");
    }

}
