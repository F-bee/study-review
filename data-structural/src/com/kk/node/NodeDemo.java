package com.kk.node;

import com.kk.enums.ExceptionEnum;
import com.kk.exception.OperationException;

/**
 * @author         kk
 * @Date           2023/1/6 13:13
 * @Description    some description
 */
public class NodeDemo {
    public static void main(String[] args) {
        SingleLinkedNode<Integer> singleLinkedNode = new SingleLinkedNode<>();
        singleLinkedNode.add(1);
        singleLinkedNode.add(2);
        singleLinkedNode.add(3);
        singleLinkedNode.add(3);
        singleLinkedNode.add(4);
        singleLinkedNode.insertAfterNode(3, 5);
        singleLinkedNode.traverse();
        singleLinkedNode.deleteFront();
        singleLinkedNode.traverse();
        singleLinkedNode.deleteAfterNode(5);
        singleLinkedNode.deleteAfterNode(5);
        singleLinkedNode.traverse();
        Node<Integer> node = singleLinkedNode.reverse();
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }

    }
}

class SingleLinkedNode<E> {
    private Node<E> node;
    private Node<E> tail; // 尾指针

    // 添加节点（尾插）
    public void add(E e) {
        Node<E> newNode = new Node<>(e);
        if (node == null) {
            node = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
    }

    // 某节点后插入
    public void insertAfterNode(E node, E elem) {
        try {
            Node<E> nodeCopy = searchNode(node);
            if (nodeCopy == null) {
                throw new OperationException(ExceptionEnum.ELEM_NOT_FOUND_ERROR.getMsg());
            } else {
                Node<E> newNode = new Node<>(elem);
                newNode.setNext(nodeCopy.getNext());
                nodeCopy.setNext(newNode);
                // 插入节点为尾节点时，将尾节点更新为此节点
                if (tail == nodeCopy) {
                    tail = newNode;
                }
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    // 删除头节点
    public void deleteFront() {
        try {
            if (node == null) {
                throw new OperationException(ExceptionEnum.LINKED_LIST_IS_NULL.getMsg());
            }
            node = node.getNext();
            // 链表中只有一个节点时，头、尾节点都要置空
            if (node == null) {
                tail = null;
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    // 某节点后删除
    public void deleteAfterNode(E node) {
        try {
            Node<E> nodeCopy = searchNode(node);
            if (nodeCopy == null) {
                throw new OperationException(ExceptionEnum.ELEM_NOT_FOUND_ERROR.getMsg());
            } else {
                if (tail != nodeCopy) {
                    nodeCopy.setNext(nodeCopy.getNext().getNext());
                }
                if (nodeCopy.getNext() == null) {
                    tail = nodeCopy;
                }
            }
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    // 链表遍历
    public void traverse() {
        Node<E> nodeCopy = node;
        while (nodeCopy != null) {
            System.out.print(nodeCopy.getValue() + "  ");
            nodeCopy = nodeCopy.getNext();
        }
        System.out.println();
    }

    // 链表反转
    public Node<E> reverse() {
        Node<E> curr = this.node;
        Node<E> next = null; // 反转后的下一个指针
        Node<E> reverseNode = new Node<>(); // 反转后的链表
        Node<E> temp;
        while (curr.getNext() != null) {
            temp = new Node<>();
            temp.setValue(curr.getValue());
            temp.setNext(next);
            reverseNode.setValue(curr.getNext().getValue());
            reverseNode.setNext(temp);
            next = temp;
            curr = curr.getNext();
        }
        return reverseNode;
    }

    // 寻找指定节点
    public Node<E> searchNode(E node) {
        if (this.node == null) {
            throw new OperationException(ExceptionEnum.LINKED_LIST_IS_NULL.getMsg());
        }
        Node<E> nodeCopy = this.node; // 工作节点
        while (nodeCopy.compareTo(node) != 0) {
            nodeCopy = nodeCopy.getNext();
            if (nodeCopy == null) {
                break;
            }
        }
        return nodeCopy;
    }
}

class Node<E> implements Comparable<Object> {
    private E value;
    private Node<E> next;

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node(E value) {
        this.value = value;
    }

    public Node() {}

    @Override
    public int compareTo(Object o) {
        if (o == this.value) {
            return 0;
        } else {
            return 1;
        }
    }
}
