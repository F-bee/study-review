package com.kk.list2;

import com.kk.constant.SeqListConstant;
import com.kk.enums.ExceptionEnum;
import com.kk.exception.OperationException;

/**
 * @author         kk
 * @Date           2023/1/5 18:16
 * @Description    数组实现，基本增删改查
 */
public class Array<E> {

    private E[] data; // 元素空间
    private int size; // 有效元素个数

    public Array(int capacity) {
        try {
            if (capacity <= 0) {
                throw new OperationException(ExceptionEnum.INIT_PARAM_NOT_ILLEGAL.getMsg());
            }
            this.data = (E[]) new Object[capacity];
            this.size = 0;
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public Array() {
        this(SeqListConstant.DEFAULT_SIZE);
    }

    // 尾部添加元素
    public void addLast(E value) {
        try {
            if (size == data.length) {
                throw new OperationException(ExceptionEnum.NO_EXTRA_SPACE.getMsg());
            }
            data[size++] = value;
            if (size >= data.length/2) {
                resize();
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    // 插入元素
    public void add(int index, E value) {
        try {
            if (size == data.length) {
                throw new OperationException(ExceptionEnum.NO_EXTRA_SPACE.getMsg());
            }
            if (index < 0 || index > size) {
                throw new OperationException(ExceptionEnum.WRONG_INDEX.getMsg());
            }
            for (int i = size - 1; i >= index; i--) {
                data[i+1] = data[i];
            }
            data[index] = value;
            size++;
            if (size >= data.length/2) {
                resize();
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    // 删除元素
    public void remove(int index) {
        try {
            if (index < 0 || index >= size) {
                throw new OperationException(ExceptionEnum.WRONG_INDEX.getMsg());
            }
            for (int i = index; i < size - 1; i++) {
                data[i] = data[i+1];
            }
            size--;
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    // 删除所有a元素
    public void removeAlla(E a) {
        for (int i = 0; i < size; i++) {
            if (data[i] == a) {
                for (int j = i; j < size - 1; j++) {
                    data[i] = data[i+1];
                }
                size--;
                i--;
            }
        }
    }

    // 修改元素
    public void update(int index, E newValue) {
        try {
            if (index < 0 || index >= size) {
                throw new OperationException(ExceptionEnum.WRONG_INDEX.getMsg());
            }
            data[index] = newValue;
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    // 获取元素
    public E get(int index) {
        try {
            if (index < 0 || index >= size) {
                throw new OperationException(ExceptionEnum.WRONG_INDEX.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return data[index];
    }

    // 判空
    public boolean isEmpty() {
        return size == 0;
    }

    // 元素是否存在
    public boolean contains(E value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    // 数组扩容
    public void resize() {
        E[] newData = (E[]) new Object[(int)(data.length*1.5)];
        if (size >= 0) {
            System.arraycopy(data, 0, newData, 0, size);
        }
        data = newData;
    }

    // 数组打印
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(data[i]).append("  ");
        }
        return sb.toString();
    }

    // 获取数组大小
    public int getCapacity() {
        return data.length;
    }
}
