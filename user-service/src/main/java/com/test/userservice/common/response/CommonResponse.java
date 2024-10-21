package com.test.userservice.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {
    private ResponseResult result;
    private T data;
    private String message;

    public static <T> CommonResponse<T> success(T data, String message) {
        return CommonResponse.<T>builder()
                .result(ResponseResult.SUCCESS)
                .data(data)
                .message(message)
                .build();
    }

    public static <T> CommonResponse<T> success(T data) {
        return success(data, null);
    }

    public static CommonResponse<Void> fail(String message) {
        return CommonResponse.<Void>builder()
                .result(ResponseResult.FAIL)
                .message(message)
                .build();
    }
}
