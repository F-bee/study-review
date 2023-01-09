package com.kk.node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author         kk
 * @Date           2023/1/8 14:23
 * @Description    二叉树实例及算法
 */
public class BinaryTree {

    public static void main(String[] args) {
        TreeNode<Integer> a = new TreeNode<>(1);
        TreeNode<Integer> b = new TreeNode<>(2);
        TreeNode<Integer> c = new TreeNode<>(3);
        TreeNode<Integer> d = new TreeNode<>(4);
        TreeNode<Integer> e = new TreeNode<>(5);
        TreeNode<Integer> f = new TreeNode<>(6);
        TreeNode<Integer> g = new TreeNode<>(7);
        TreeNode<Integer> h = new TreeNode<>(8);
        d.setLeft(b);
        d.setRight(f);
        b.setLeft(a);
        b.setRight(c);
        f.setLeft(e);
        f.setRight(h);
        h.setLeft(g);

//        a.setLeft(b);
//        a.setRight(c);
//        b.setLeft(d);
//        b.setRight(e);
//        c.setLeft(f);
//        c.setRight(g);
//        d.setLeft(h);

//        System.out.println(isCompleteBinaryTree(a));

//        preVisitor(a);
//        System.out.println();
//        middleVisitor(a);
//        System.out.println();
//        afterVisitor(a);
//        System.out.println();

//        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
//        int[] mid = {4, 7, 2, 1, 5, 3, 8, 6};
//        TreeNode<Integer> tree = getTree(pre, mid);
//        preVisitor(tree);
//        System.out.println();

        isContainsK(d, 7);
    }

    /**
     * 判断一棵二叉树是否是完全二叉树
     * 1. 层序遍历
     * 2. 如果节点的度为1，只有左子树没有右子树
     * 3. 如果节点的度为0，此节点后面的节点也一定是叶子节点
     * 4. 完全二叉树也可以是空树
     * （该方法存在问题，有多个度为1的节点的二叉树也会被判断为是完全二叉树）
     *
     * @param tree 二叉树根节点
     * @return 是否是完全二叉树
     */
    public static boolean isCompleteBinaryTreeTest(TreeNode<?> tree) {
        // 完全二叉树也可以是空树
        if (tree == null) {
            return true;
        }
        // 层序遍历
        Queue<TreeNode<?>> queue = new LinkedList<>();
        queue.offer(tree);
        TreeNode<?> root;
        boolean isLeaf = false; // 是否遍历到了叶子节点
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (!isLeaf && root.getLeft() != null) {
                // 如果节点的度为1，只有左子树没有右子树
                if (root.getLeft() == null && root.getRight() != null) {
                    return false;
                }
                if (root.getLeft() != null) {
                    queue.offer(root.getLeft());
                }
                if (root.getRight() != null) {
                    queue.offer(root.getRight());
                }
                if (root.getLeft() == null && root.getRight() == null) {
                    isLeaf = true;
                }
            } else { // 遍历到叶子节点后，判断之后的节点是否都是叶子节点
                if (root.getLeft() != null || root.getRight() != null) {
                    return false;
                }
            }
        }
        return isLeaf;
    }

    /**
     * 判断一棵二叉树是否是完全二叉树
     * 1. 层序遍历
     * 2. 如果节点的度为1，只有左子树没有右子树
     * 3. 如果节点的度为1或0，其后的节点不会再有孩子
     * 4. 完全二叉树也可以是空树
     *
     * @param tree 二叉树根节点
     * @return 是否是完全二叉树
     */
    public static boolean isCompleteBinaryTree(TreeNode<?> tree) {
        // 完全二叉树也可以是空树
        if (tree == null) {
            return true;
        }
        // 层序遍历
        Queue<TreeNode<?>> queue = new LinkedList<>();
        queue.offer(tree);
        TreeNode<?> root, leftLeaf, rightLeaf;
        boolean flag = false;
        while (!queue.isEmpty()) {
            root = queue.poll();
            leftLeaf = root.getLeft();
            rightLeaf = root.getRight();
            // 度为1的节点，只有左子树没有右子树
            if (leftLeaf == null && rightLeaf != null) {
                return false;
            }
            // 度为1或0的节点之后的节点如果还有孩子，则不是完全二叉树
            // 此处仅判断左子树是否为空，因为前一处判断已确定了右子树不为空时左子树一定有值
            if (flag && leftLeaf != null) {
                return false;
            }
            if (leftLeaf != null) {
                queue.add(leftLeaf);
            }
            if (rightLeaf != null) {
                queue.add(rightLeaf);
            } else { // 如果是度为1或0的节点，之后的节点不应再有孩子
                flag = true;
            }
        }
        return true;
    }

    // 先序遍历
    public static void preVisitor(TreeNode<?> tree) {
        if (tree != null) {
            System.out.print(tree.getRoot() + "  ");
            preVisitor(tree.getLeft());
            preVisitor(tree.getRight());
        }
    }

    // 中序遍历
    public static void middleVisitor(TreeNode<?> tree){
        if (tree != null) {
            middleVisitor(tree.getLeft());
            System.out.print(tree.getRoot() + "  ");
            middleVisitor(tree.getRight());
        }
    }

    // 后序遍历
    public static void afterVisitor(TreeNode<?> tree){
        if (tree != null) {
            afterVisitor(tree.getLeft());
            afterVisitor(tree.getRight());
            System.out.print(tree.getRoot() + "  ");
        }
    }

    // 根据先序遍历、中序遍历结果构造二叉树
    public static TreeNode<Integer> getTree(int[] pre, int[] mid){
        if (pre.length != mid.length) {
            return null;
        }
        TreeNode<Integer> tree = new TreeNode<>(pre[0]);
        if (pre.length == 1) {
            return tree;
        }
        for (int i = 0; i < mid.length; i++)  {
            if (mid[i] == pre[0]) {
                // 构建左树
                if (i == 0) { // 没有左树
                    tree.setLeft(null);
                } else {
                    int[] pre_left = new int[i];
                    int[] mid_left = new int[i];
                    for (int j = 0; j < i; j++) {
                        pre_left[j] = pre[j+1];
                        mid_left[j] = mid[j];
                    }
                    tree.setLeft(getTree(pre_left, mid_left));
                }
                // 构建右树
                if (i + 1 == mid.length) { // 没有右树
                    tree.setRight(null);
                } else {
                    int[] pre_right = new int[mid.length-1-i];
                    int[] mid_right = new int[mid.length-1-i];
                    for (int j = 0; j < mid.length -1 - i; j++) {
                        pre_right[j] = pre[j+mid.length-1-i];
                        mid_right[j] = mid[j+mid.length-1-i];
                    }
                    tree.setRight(getTree(pre_right, mid_right));
                }
            }
        }
        return tree;
    }

    // BST中里面是否含有某节点
    public static void isContainsK(TreeNode<Integer> tree, Integer k) {
        if (tree != null) {
            if (k.equals(tree.getRoot())) {
                System.out.println("二叉树中包含值为" + k + "的节点");
            } else if (k > tree.getRoot()) {
                isContainsK(tree.getRight(), k);
            } else if (k < tree.getRoot()) {
                isContainsK(tree.getLeft(), k);
            }
        } else {
            System.out.println("二叉树中没有值为" + k + "的节点");
        }
    }

}

class TreeNode<E> {
    private E root;
    private TreeNode<E> left;
    private TreeNode<E> right;

    public TreeNode(E root) {
        this.root = root;
    }

    public TreeNode() {}

    public E getRoot() {
        return root;
    }

    public void setRoot(E root) {
        this.root = root;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}