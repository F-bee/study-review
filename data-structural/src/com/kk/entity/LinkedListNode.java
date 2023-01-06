package com.kk.entity;

public class LinkedListNode {
    private Integer data;
    private LinkedListNode next;

    public LinkedListNode() {}

    public LinkedListNode(Integer data, LinkedListNode next) {
        this.data = data;
        this.next = next;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public LinkedListNode getNext() {
        return next;
    }

    public void setNext(LinkedListNode next) {
        this.next = next;
    }
}
