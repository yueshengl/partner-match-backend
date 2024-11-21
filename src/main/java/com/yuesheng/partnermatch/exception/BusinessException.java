package com.yuesheng.partnermatch.exception;

import com.yuesheng.partnermatch.common.ErrorCode;
import lombok.Getter;

/**
 * @Author: Dai
 * @Date: 2024/11/02 22:40
 * @Description: BusinessException
 * @Version: 1.0
 */
public class BusinessException extends RuntimeException{
    @Getter
    private final int code;
    @Getter
    private final String description;

    public BusinessException(String message, int code, String description)
    {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode)
    {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description)
    {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

}
