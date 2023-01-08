package com.kk.list2;

import com.kk.constant.SeqListConstant;
import com.kk.enums.ExceptionEnum;
import com.kk.exception.OperationException;

/**
 * @author         kk
 * @Date           2023/1/5 20:51
 * @Description    栈实现
 */
public class StackDemo<E> {

    private int maxSize; // 初始化栈空间大小
    private E[] data;    // 元素空间
    private int top;     // 栈顶指针

    public StackDemo(int maxSize) {
        try {
            if (maxSize <= 0) {
                throw new OperationException(ExceptionEnum.INIT_PARAM_NOT_ILLEGAL.getMsg());
            }
            this.maxSize = maxSize;
            this.data = (E[]) new Object[maxSize];
            this.top = -1;
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public StackDemo() {
        this(SeqListConstant.DEFAULT_SIZE);
    }

    // 入栈
    public void push(E value) {
        try {
            if (top + 1 == maxSize) {
//                resize();
                throw new OperationException(ExceptionEnum.STACK_OVER_FLOW.getMsg());
            }
            data[++top] = value;
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    // 查看栈顶元素
    public E peek() {
        try {
            if (isEmpty()) {
                throw new OperationException(ExceptionEnum.STACK_UNDER_FLOW.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return data[top];
    }

    // 出栈
    public E pop() {
        try {
            if (isEmpty()) {
                throw new OperationException(ExceptionEnum.STACK_UNDER_FLOW.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return data[top--];
    }

    // 栈是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    // 扩容
    public void resize() {
        E[] newData = (E[]) new Object[(int)(data.length*1.5)];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }
}
