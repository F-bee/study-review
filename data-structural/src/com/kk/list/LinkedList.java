package com.kk.list;

import com.kk.entity.LinkedListNode;
import com.kk.enums.ExceptionEnum;
import com.kk.exception.OperationException;

public class LinkedList {
    protected LinkedListNode head;
    protected int length;

    public LinkedList() {
        head = new LinkedListNode();
        length = 0;
    }

    public LinkedList(Integer[] elems, int length) {
        LinkedListNode p = head = new LinkedListNode();
        for (int i = 0; i < length; i++) {
            p.setNext(new LinkedListNode(elems[i], null));
            p = p.getNext();
        }
        this.length = length;
    }

    public void traverse() {
        LinkedListNode p = this.head;
        for (int i = 0; i < length; i++) {
            System.out.print(p.getNext().getData() + " ");
            p = p.getNext();
        }
        System.out.println();
    }

    public void setElem(int pos, Integer elem) {
        try {
            if (pos < 0 || pos >= length) {
                throw new OperationException(ExceptionEnum.INDEX_NOT_EXIST.getMsg());
            }
            LinkedListNode p = this.head;
            for (int i = 0; i < pos; i++) {
                p = p.getNext();
            }
            p.setData(elem);
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void deleteElem(int pos) {
        try {
            if (pos < 0 || pos >= length) {
                throw new OperationException(ExceptionEnum.INDEX_NOT_EXIST.getMsg());
            }
            LinkedListNode p = this.head;
            for (int i = 0; i < pos; i++) {
                p = p.getNext();
            }
            p.setNext(p.getNext().getNext());
            length--;
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }

    public void insertElem(int pos, Integer elem) {
        try {
            if (pos < 0 || pos > length) {
                throw new OperationException(ExceptionEnum.INDEX_NOT_EXIST.getMsg());
            }
            LinkedListNode p = this.head;
            for (int i = 0; i < pos; i++) {
                p = p.getNext();
            }
            LinkedListNode q = new LinkedListNode(elem, null);
            q.setNext(p.getNext());
            p.setNext(q);
            length++;
        } catch (OperationException e) {
            e.printStackTrace();
        }
    }



}
