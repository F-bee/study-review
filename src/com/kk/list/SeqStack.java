package com.kk.list;

import com.kk.enums.ExceptionEnum;
import com.kk.exception.OperationException;

public class SeqStack {
    protected int top;
    protected int maxSize;
    protected Integer[] elems;

    public SeqStack(int size) {
        maxSize = size;
        elems = new Integer[maxSize];
        top = -1;
    }

    public int getLength() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(Integer elem) {
        try {
            if (top + 1 == maxSize) {
                throw new OperationException(ExceptionEnum.STACK_OVER_FLOW.getMsg());
            }
            elems[++top] = elem;
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public Integer peek() {
        try {
            if (isEmpty()) {
                throw new OperationException(ExceptionEnum.STACK_UNDER_FLOW.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return elems[top];
    }

    public Integer pop() {
        try {
            if (isEmpty()) {
                throw new OperationException(ExceptionEnum.STACK_UNDER_FLOW.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return elems[top--];
    }

    public void traverse() {
        for (int i = top; i >= 0 ; i--) {
            System.out.print(elems[i] + " ");
        }
        System.out.println();
    }
}
