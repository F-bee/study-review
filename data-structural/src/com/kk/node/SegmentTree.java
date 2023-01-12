package com.kk.node;

import com.kk.enums.ExceptionEnum;
import com.kk.exception.OperationException;

/**
 * @author         kk
 * @Date           2023/1/12 13:26
 * @Description    线段树
 */
public class SegmentTree<E> {

    private E[] data; // 接收传入数组
    private E[] tree; // 线段树
    private Merge<E> merge;

    public SegmentTree(E[] arr, Merge<E> merge) {
        this.merge = merge;
        // 声明数据数组空间大小
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        // 声明线段树空间大小
        tree = (E[]) new Object[4*arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    // 查询区间值
    public E queryArea(int queryL, int queryR) {
        try {
            if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryR < queryL){
                throw new OperationException(ExceptionEnum.WRONG_INDEX.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private E query(int index, int left, int right, int queryL, int queryR) {
        E result;
        if (left == queryL && right == queryR) {
            return tree[index];
        }
        int leftChild = leftChild(index);
        int rightChild = rightChild(index);
        //中间边界
        int mid = left + (right - left) / 2;
        if (queryL >= mid + 1) {
            result = query(rightChild, mid + 1, right, queryL, queryR);
        } else if (queryR <= mid) {
            result = query(leftChild, left, mid, queryL, queryR);
        } else {
            E a = query(leftChild, left, mid, queryL, mid);
            E b = query(rightChild, mid + 1, right, mid + 1, queryR);
            result = merge.merge(a, b);
        }
        return result;
    }

    public int leftChild(int index)  {
        try {
            if (index < 0 || index >= data.length) {
                throw new OperationException(ExceptionEnum.WRONG_INDEX.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return 2 * index + 1;
    }

    public int rightChild(int index) {
        try {
            if (index < 0 || index >= data.length) {
                throw new OperationException(ExceptionEnum.WRONG_INDEX.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return 2 * index + 2;
    }

    // 构建线段树
    public void buildSegmentTree(int index, int left, int right) {
        if (left == right) {
            tree[index] = data[left];
            return;
        }
        int leftChild = leftChild(index);
        int rightChild = rightChild(index);
        // 中间边界
        int mid = left + (right - left) / 2;
        buildSegmentTree(leftChild, left, mid);
        buildSegmentTree(rightChild, mid + 1, right);
        // 求和（在测试类匿名内部类中实现）
        tree[index] = merge.merge(tree[leftChild], tree[rightChild]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                sb.append(tree[i]);
            } else {
                sb.append("null");
            }
            if (i != tree.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

interface Merge<E> {
    E merge(E a, E b);
}
