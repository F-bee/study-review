package com.kk.enums;

public enum ExceptionEnum {

    WRONG_INDEX(103,  "索引不合法"),
    ELEM_NOT_FOUND_ERROR(104, "没有该元素"),
    INDEX_NOT_EXIST(105, "该位置没有数据"),
    STACK_OVER_FLOW(106, "栈已满"),
    STACK_UNDER_FLOW(107, "栈为空"),
    QUEUE_OVER_FLOW(108, "队列已满"),
    QUEUE_UNDER_FLOW(109, "队列为空"),
    LINKED_LIST_IS_NULL(110, "链表为空"),
    NO_EXTRA_SPACE(201, "没有空余空间，添加失败"),
    INIT_PARAM_NOT_ILLEGAL(202, "初始化空间参数不合法"),
    ;

    private final Integer code;
    private final String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
