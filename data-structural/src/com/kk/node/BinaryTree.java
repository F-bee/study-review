package com.kk.node;

import java.util.*;

/**
 * @author         kk
 * @Date           2023/1/8 14:23
 * @Description    二叉树实例及算法
 */
public class BinaryTree {

    // 用于判断一棵树是否是BST时，接收中序遍历结果
    static List<Integer> list = new ArrayList<>();

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
            /*
             度为1或0的节点之后的节点如果还有孩子，则不是完全二叉树
             此处仅判断左子树是否为空，因为前一处判断已确定了右子树不为空时左子树一定有值
             */
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

    // 已知BST的先序遍历结果，构建BST
    public static TreeNode<Integer> buildBST(List<Integer> list) {
        if (list.size() == 0) {
            return null;
        }
        TreeNode<Integer> tree = new TreeNode<>(list.get(0));
        if (list.size() == 1) {
            return tree;
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int i = 1;
        while (i < list.size() && list.get(i) < list.get(0)) {
            left.add(list.get(i));
            i++;
        }
        tree.setLeft(buildBST(left));
        for (int j = i; j < list.size(); j++) {
            right.add(list.get(j));
        }
        tree.setRight(buildBST(right));
        return tree;
    }

    /*
     判断一棵树是不是BST
       方法一：
         找出左树上最大的节点判断是否小于根，右树上最小的节点是否大于根，对左右子树递归地进行判断
       方法二：
         输出中序遍历的结果，判断是否升序
     */
    public static List<Integer> validBST(TreeNode<Integer> tree) {
        if (tree == null) {
            return null;
        }
        if (tree.getLeft() != null) {
            validBST(tree.getLeft());
        }
        if (tree.getRoot() != null) {
            list.add(tree.getRoot());
        }
        if (tree.getRight() != null) {
            validBST(tree.getRight());
        }
        return list;
    }

    // 判断list中元素是否为升序排列
    public static boolean isAsc(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i+1)) {
                return false;
            }
        }
        return true;
    }

    // AVL 左旋
    public static TreeNode<Integer> rotateLeft(TreeNode<Integer> tree) {
        // 新的根节点为原根节点的右孩子
        TreeNode<Integer> newRoot = tree.getRight();
        // 原根节点右孩子的左子树成为原根节点的右子树
        TreeNode<Integer> temp = tree.getRight().getLeft();
        tree.getRight().setLeft(null);
        tree.setRight(temp);
        // 原根节点变为新的根节点的左子树
        newRoot.setLeft(tree);
        return newRoot;
    }

    // AVL 右旋
    public static TreeNode<Integer> rotateRight(TreeNode<Integer> tree) {
        // 新的根节点为原根节点的左孩子
        TreeNode<Integer> newRoot = tree.getLeft();
        // 原根节点左孩子的右子树成为原根节点的左子树
        TreeNode<Integer> temp = tree.getLeft().getRight();
        tree.getLeft().setRight(null);
        tree.setLeft(temp);
        // 原根节点变为新的根节点的右子树
        newRoot.setRight(tree);
        return newRoot;
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