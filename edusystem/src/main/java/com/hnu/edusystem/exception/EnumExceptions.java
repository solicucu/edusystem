package com.hnu.edusystem.exception;

/**
 * 所有异常类型
 * 
 * @author zhouweixin
 *
 */
public enum EnumExceptions {
	UNKNOWN_ERROR(-1, "未知错误"),
	SUCCESS(0, "操作成功"),
	ADD_FAILED_DUPLICATE(1, "新增失败, 数据已存在"),
	UPDATE_FAILED_NOT_EXIST(2, "更新失败, 数据不存在"),
	DELETE_FAILED_NOT_EXIST(3, "删除失败, 数据不存在"),
	REQUEST_METHOD(4, "请求方法不匹配"),
	ARGB_MISMATCH_EXCEPTION(5, "参数类型不匹配错误, 请检查"),
	ID_NOT_EXIST(6,"账号不存在"),
    PASSWORD_ERROR(7,"密码错误"),
    ADD_FAILED_CNAME_EXIST(8,"课程名已存在"),
    FAILED_COURSE_NOT_EXIST(9,"课程不存在"),
    FAILED_STUDENT_NOT_EXIST(10,"学生不存在"),
	FAILED_TIME_CONFLICT(11,"时间冲突"),
    FAILED_NUM_MAX(12,"课程人数已满"),
    FAILED_TEACHER_NOT_EXIST(13,"老师不存在"),
	;

	/** 编码 */
	private Integer code;
	/** 信息 */
	private String message;

	/**
	 * 构造函数
	 * 
	 * @param code
	 * @param message
	 */
    EnumExceptions(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
