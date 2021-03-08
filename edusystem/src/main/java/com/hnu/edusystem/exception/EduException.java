package com.hnu.edusystem.exception;

public class EduException extends RuntimeException {

    private Integer code;

    public EduException(EnumExceptions exceptionsEnum){
        super(exceptionsEnum.getMessage());
        this.code = exceptionsEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
