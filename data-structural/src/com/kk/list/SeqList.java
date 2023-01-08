package com.kk.list;

import com.kk.constant.SeqListConstant;
import com.kk.enums.ExceptionEnum;
import com.kk.exception.OperationException;

import java.util.Objects;

public class SeqList {

    protected Integer[] elems;
    protected int length;
    protected int maxLength;

    public SeqList() {
        this.maxLength = SeqListConstant.DEFAULT_SIZE;
        this.length = 0;
        this.elems = new Integer[maxLength];
    }

    public SeqList(Integer[] elems, int length, int maxLength) {
        this.elems = elems;
        this.length = length;
        this.maxLength = maxLength;
    }

    public int getLength() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void clear() {
        elems = new Integer[]{};
        this.maxLength = SeqListConstant.DEFAULT_SIZE;
        this.length = 0;
    }

    // 遍历顺序表
    public void traverse() {
        for (int i = 0; i < length; i++) {
            System.out.print(elems[i] + " ");
        }
        System.out.println();
    }

    // 元素定位
    public int locateElem(Integer elem) {
        int pos = 0;
        try {
            for (pos = 0; pos < length; pos++) {
                if (Objects.equals(elem, elems[pos])) {
                    break;
                }
            }
            if (!Objects.equals(elem, elems[pos])) {
                throw new OperationException(ExceptionEnum.ELEM_NOT_FOUND_ERROR.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pos;
    }

    // 获取元素
    public Integer getElem(int pos) {
        try {
            if (pos < 0 || pos >= length) {
                throw new OperationException(ExceptionEnum.INDEX_NOT_EXIST.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return elems[pos];
    }

    // 插入元素
    public void insertElem(int pos, Integer elem) {
        try {
            if (pos < 0 || pos > length) {
                throw new OperationException(ExceptionEnum.WRONG_INDEX.getMsg());
            }
            if (length >= maxLength) {
                maxLength *= 2;
            }
            Integer[] newSeqList = new Integer[length+1];
            System.arraycopy(elems, 0, newSeqList, 0, length);
            for (int i = length - 1; i >= pos; i--) {
                newSeqList[i+1] = newSeqList[i];
            }
            newSeqList[pos] = elem;
            elems = newSeqList;
            length++;
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    // 删除指定位置元素
    public void deleteElem(int pos) {
        try {
            if (pos < 0 || pos >= length) {
                throw new OperationException(ExceptionEnum.WRONG_INDEX.getMsg());
            }
            for (int i = pos - 1; i < length; i++) {
                elems[i-1] = elems[i];
            }
            Integer[] newSeqList = new Integer[length-1];
            System.arraycopy(elems, 0, newSeqList, 0, length - 1);
            elems = newSeqList;
            length--;
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void bubbleSort() {
        int temp;
        boolean flag = true;
        for (int i = 0; i < length; i++) {
            if (!flag) {
                return;
            }
            flag = false;
            for (int j = 0; j < length - 1 - i; j++) {
                if (elems[j+1] < elems[j]) {
                    temp = elems[j];
                    elems[j] = elems[j+1];
                    elems[j+1] = temp;
                    flag = true;
                }
            }
        }
    }

}
