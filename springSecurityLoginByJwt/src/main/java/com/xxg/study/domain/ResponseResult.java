package com.xxg.study.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

    private Integer code;
    private Integer msg;

    /**
     * 查询到的结果数据
     */
    private T data;

    public ResponseResult(Integer code, Integer msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
}

