package com.kk.list2;

import com.kk.enums.ExceptionEnum;
import com.kk.exception.OperationException;

/**
 * @author         kk
 * @Date           2023/1/6 0:36
 * @Description    队列实现
 */
public class QueueDemo<E> {

    private E[] data;    // 元素空间
    private int size;    // 有效元素个数
    public int putIndex; // 队尾指针

    public QueueDemo(int capacity) {
        try {
            if (capacity <= 0 ) {
                throw new OperationException(ExceptionEnum.INIT_PARAM_NOT_ILLEGAL.getMsg());
            }
            this.data = (E[]) new Object[capacity];
            this.size = 0;
            this.putIndex = -1;
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public QueueDemo() {
        this(10);
    }

    // 查看队首元素
    public E peekHead() {
        try {
            if (size == 0) {
                throw new OperationException(ExceptionEnum.QUEUE_UNDER_FLOW.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return data[0];
    }

    // 查看队尾元素
    public E peekTail() {
        try {
            if (size == 0) {
                throw new OperationException(ExceptionEnum.QUEUE_UNDER_FLOW.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return data[putIndex];
    }

    // 入队
    public void push(E elem) {
        try {
            if (size == data.length) {
                throw new OperationException(ExceptionEnum.QUEUE_OVER_FLOW.getMsg());
            }
            data[++putIndex] = elem;
            size++;
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    // 出队
    public E poll() {
        try {
            if (size == 0) {
                throw new OperationException(ExceptionEnum.QUEUE_UNDER_FLOW.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        E elem = data[0];
        for(int i = 0; i < size; i++){
            data[i] = data[i+1];
        }
        data[size-1] = null;
        size--;
        putIndex--;
        return elem;
    }
}
