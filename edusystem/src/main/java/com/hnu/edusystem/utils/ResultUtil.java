package com.hnu.edusystem.utils;

import com.hnu.edusystem.domain.Result;
import com.hnu.edusystem.exception.EduException;
import com.hnu.edusystem.exception.EnumExceptions;

public class ResultUtil {
    /**
     * 成功
     *
     * @param data
     * @return
     */

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.setCode(EnumExceptions.SUCCESS.getCode());
        result.setMessage(EnumExceptions.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 成功
     * @return
     */

    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 异常
     *
     * @param eduException
     * @return
     */
    public static <T> Result<T> error(EduException eduException) {
        Result<T> result = new Result<T>();
        result.setCode(eduException.getCode());
        result.setMessage(eduException.getMessage());
        return result;
    }

    /**
     * 异常
     *
     * @param message
     * @return
     */
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<T>();
        result.setCode(-2);
        result.setMessage(message);
        return result;
    }
}