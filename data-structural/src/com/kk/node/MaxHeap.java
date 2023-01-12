package com.kk.node;

import com.kk.enums.ExceptionEnum;
import com.kk.exception.OperationException;

/**
 * @author         kk
 * @Date           2023/1/11 21:03
 * @Description    大根堆操作
 */
public class MaxHeap <E extends Comparable<E>> {

    private E[] data; // 存放数据
    private int size; // 有效长度

    public MaxHeap(int capacity) {
        data = (E[]) new Comparable[capacity];
    }

    public MaxHeap() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int left(int index) {
        try {
            if (index < 0) {
                throw new OperationException(ExceptionEnum.WRONG_INDEX.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return 2 * index + 1;
    }

    private int right(int index) {
        try {
            if (index < 0) {
                throw new OperationException(ExceptionEnum.WRONG_INDEX.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return 2 * index + 2;
    }

    // 父节点索引
    private int parent(int index) {
        try {
            if (index < 0) {
                throw new OperationException(ExceptionEnum.WRONG_INDEX.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return (index - 1) / 2;
    }

    // 堆中最大值
    public E findMax() {
        try {
            if (size <= 0) {
                throw new OperationException(ExceptionEnum.HEAP_IS_NULL.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return data[0];
    }

    // 查看数组最后一个值
    public E findLast() {
        try {
            if (size <= 0) {
                throw new OperationException(ExceptionEnum.HEAP_IS_NULL.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return data[size-1];
    }

    // 取出最大值
    public E getMax() {
        try {
            if (size <= 0) {
                throw new OperationException(ExceptionEnum.HEAP_IS_NULL.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        E temp = data[0];
        delete(0);
        return temp;
    }

    // 上浮
    public void up(int index) {
        int parent = this.parent(index);
        E temp;
        while (parent >= 0 && data[parent].compareTo(data[index]) < 0) {
            temp = data[parent];
            data[parent] = data[index];
            data[index] = temp;
            index = parent;
            parent = this.parent(index);
        }
    }

    // 下沉
    public void down(int index) {
        int left = this.left(index);
        int right = this.right(index);
        // max 记录较大孩子的索引
        int max = -1;
        if (right < size) {
            max = data[left].compareTo(data[right]) > 0 ? left : right;
        } else if (left < size) {
            max = left;
        }
        // 大根堆任何节点的值应该小于等于其父节点
        E temp;
        while (max != -1 && data[index].compareTo(data[max]) < 0) {
            temp = data[index];
            data[index] = data[max];
            data[max] = temp;
            index = max;
            left = this.left(index);
            right = this.right(index);
            // max 记录较大孩子的索引
            if (right < size) {
                max = data[left].compareTo(data[right]) > 0 ? left : right;
            } else if (left < size) {
                max = left;
            }
        }
    }

    // 添加
    public void insert(E e) {
        this.data[size] = e;
        this.size++;
        up(size - 1);
    }

    // 删除
    public void delete(int index) {
        if (index == size - 1) {
            this.data[index] = null;
            this.size--;
        } else {
            this.data[index] = data[size - 1];
            this.data[size - 1] = null;
            this.size--;
            down(index);
        }
    }

    // 修改
    public void update(int index, E e) {
        data[index] = e;
        int left = this.left(index);
        int right = this.right(index);
        // max 记录较大孩子的索引
        int max = -1;
        if (right < size) {
            max = data[left].compareTo(data[right]) > 0 ? left : right;
        } else if (left < size) {
            max = left;
        }
        if (max != -1) {
            if (data[index].compareTo(data[max]) < 0) {
                down(index);
            } else {
                up(index);
            }
        }
    }

    // 遍历
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
