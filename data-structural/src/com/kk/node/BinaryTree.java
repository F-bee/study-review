package com.kk.node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author         kk
 * @Date           2023/1/8 14:23
 * @Description    判断二叉树是否是完全二叉树
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
        a.setLeft(b);
        a.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        c.setRight(g);
        d.setLeft(h);
        System.out.println(isCompleteBinaryTree(a));
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