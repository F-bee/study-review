package com.kk.set;

/**
 * @author         kk
 * @Date           2023/1/16 13:26
 * @Description    测试类（并查集）
 */
public class Test {
    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(10);
        System.out.println("合并前：");
        unionFind.printArr();
        unionFind.unionElement(5, 6);
        System.out.println("合并5，6后：");
        unionFind.printArr();
        System.out.println(unionFind.isConnected(4, 6));
        System.out.println(unionFind.isConnected(5, 6));
    }
}
