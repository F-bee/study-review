package com.kk.set;

import com.kk.enums.ExceptionEnum;
import com.kk.exception.OperationException;

/**
 * @author         kk
 * @Date           2023/1/16 12:17
 * @Description    并查集
 */
public class UnionFind implements UF{

    // 组
    private int[] id;

    public UnionFind(int size) {
        this.id = new int[size];
        // 默认索引为组id
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    // 找组id
    private int find(int index) {
        try {
            if (index < 0 || index >= id.length) {
                throw new OperationException(ExceptionEnum.WRONG_INDEX.getMsg());
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
        return id[index];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 将所有p组元素归为q组
     * @param p 操作索引
     * @param q 目标索引
     */
    @Override
    public void unionElement(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
    }

    public void printArr() {
        for (int i : id) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }
}

interface UF {
    // 判断是否相连
    boolean isConnected(int p, int q);
    // 并（归类分组）
    void unionElement(int p, int q);
}