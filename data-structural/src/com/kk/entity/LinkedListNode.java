package com.kk.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LinkedListNode that = (LinkedListNode) o;
        return Objects.equals(data, that.data) && Objects.equals(next, that.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, next);
    }
}
