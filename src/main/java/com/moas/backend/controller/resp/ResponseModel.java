package com.moas.backend.controller.resp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseModel<T>{

    protected static final String SUCCESS = "success";
    protected static final String FAILED = "error";

    protected T data;

    protected Integer code;

    protected String msg;

    public ResponseModel(T data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public static <T> ResponseModel<T> success(T data) {
        return new ResponseModel<>(data, 0, SUCCESS);
    }

    public static <T> ResponseModel<T> error(){
        return new ResponseModel<>(null, 1, FAILED);
    }

    public static <T> ResponseModel<T> error(String msg){
        return new ResponseModel<>(null, 1, msg);
    }

}
