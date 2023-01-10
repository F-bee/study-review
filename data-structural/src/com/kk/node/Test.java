package com.kk.node;

import java.util.Arrays;
import java.util.List;

/**
 * @author         kk
 * @Date           2023/1/10 0:08
 * @Description    BinaryTree test class
 */
public class Test {
    public static void main(String[] args) {

//        TreeNode<Integer> a = new TreeNode<>(1);
//        TreeNode<Integer> b = new TreeNode<>(2);
//        TreeNode<Integer> c = new TreeNode<>(3);
//        TreeNode<Integer> d = new TreeNode<>(4);
//        TreeNode<Integer> e = new TreeNode<>(5);
//        TreeNode<Integer> f = new TreeNode<>(6);
//        TreeNode<Integer> g = new TreeNode<>(7);
//        TreeNode<Integer> h = new TreeNode<>(8);
//        d.setLeft(b);
//        d.setRight(f);
//        b.setLeft(a);
//        b.setRight(c);
//        f.setLeft(e);
//        f.setRight(h);
//        h.setLeft(g);

//        a.setLeft(b);
//        a.setRight(c);
//        b.setLeft(d);
//        b.setRight(e);
//        c.setLeft(f);
//        c.setRight(g);
//        d.setLeft(h);

//        System.out.println(BinaryTree.isCompleteBinaryTree(d));

//        BinaryTree.preVisitor(d);
//        System.out.println();
//        BinaryTree.middleVisitor(d);
//        System.out.println();
//        BinaryTree.afterVisitor(d);
//        System.out.println();

//        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
//        int[] mid = {4, 7, 2, 1, 5, 3, 8, 6};
//        TreeNode<Integer> tree = BinaryTree.getTree(pre, mid);
//        BinaryTree.preVisitor(tree);
//        System.out.println();

//        BinaryTree.isContainsK(d, 7);

//        Integer[] arr = {4, 2, 1, 3, 6, 5, 7, 8};
//        List<Integer> list1 = Arrays.asList(arr);
//        TreeNode<Integer> tree1 = BinaryTree.buildBST(list1);
//        middleVisitor(tree1);
//        System.out.println();

//        List<Integer> list2 = BinaryTree.validBST(tree1);
//        System.out.println(list2);
//        assert list2 != null;
//        System.out.println(BinaryTree.isAsc(list2));

        // 左旋
//        TreeNode<Integer> tree11=new TreeNode<>(11);
//        TreeNode<Integer> tree9=new TreeNode<>(9);
//        TreeNode<Integer> tree13=new TreeNode<>(13);
//        TreeNode<Integer> tree12=new TreeNode<>(12);
//        TreeNode<Integer> tree15=new TreeNode<>(15);
//        TreeNode<Integer> tree19=new TreeNode<>(19);
//        tree15.setRight(tree19);
//        tree13.setRight(tree15);
//        tree13.setLeft(tree12);
//        tree11.setLeft(tree9);
//        tree11.setRight(tree13);
//        TreeNode<Integer> tree = BinaryTree.rotateLeft(tree11);
//        System.out.println(tree);

        // 右旋
//        TreeNode<Integer> tree10=new TreeNode<>(10);
//        TreeNode<Integer> tree15=new TreeNode<>(15);
//        TreeNode<Integer> tree7=new TreeNode<>(7);
//        TreeNode<Integer> tree8=new TreeNode<>(8);
//        TreeNode<Integer> tree4=new TreeNode<>(4);
//        TreeNode<Integer> tree5=new TreeNode<>(5);
//        tree4.setRight(tree5);
//        tree7.setRight(tree4);
//        tree7.setLeft(tree8);
//        tree10.setLeft(tree7);
//        tree10.setRight(tree15);
//        TreeNode<Integer> tree = BinaryTree.rotateRight(tree10);
//        System.out.println(tree);

        // LR 先左旋再右旋
        TreeNode<Integer> tree10=new TreeNode<>(10);
        TreeNode<Integer> tree15=new TreeNode<>(15);
        TreeNode<Integer> tree7=new TreeNode<>(7);
        TreeNode<Integer> tree8=new TreeNode<>(8);
        TreeNode<Integer> tree4=new TreeNode<>(4);
        TreeNode<Integer> tree5=new TreeNode<>(5);
        tree4.setRight(tree5);
        tree7.setRight(tree4);
        tree7.setLeft(tree8);
        tree10.setLeft(tree7);
        tree10.setRight(tree15);
        tree10.setLeft(BinaryTree.rotateLeft(tree10.getLeft()));
        System.out.println(tree10);
        TreeNode<Integer> tree = BinaryTree.rotateRight(tree10);
        System.out.println(tree);

    }
}
