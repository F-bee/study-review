package com.kk.list;

import com.kk.entity.LinkedListNode;

/**
 * @author: fkxia
 * @description 基于链表的队列
 * @date: 2024/4/21 14:30
 */
public class LinkedQueue {

    LinkedListNode head = new LinkedListNode();

    LinkedListNode tail = new LinkedListNode();

    public boolean enqueue(Integer value) {
        LinkedListNode newNode = new LinkedListNode(value, null);
        tail.setNext(newNode);
        tail = tail.getNext();
        return true;
    }

    public Integer dequeue() {
        if (head == tail) {
            return null;
        }
        Integer value = head.getData();
        head = head.getNext();
        return value;
    }
}
