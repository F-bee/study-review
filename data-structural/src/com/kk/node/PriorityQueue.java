package com.kk.node;

/**
 * @author         kk
 * @Date           2023/1/12 13:04
 * @Description    优先队列
 *
 * 1. 最大优先队列：值越大越早出队
 * 2. 最小优先队列：值越小越早出队
 */
public class PriorityQueue<E extends Comparable<E>> {
    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    public PriorityQueue(MaxHeap<E> maxHeap) {
        this.maxHeap = maxHeap;
    }

    // 入队
    public void push(E e) {
        maxHeap.insert(e);
    }

    // 出队
    public E pop() {
        return maxHeap.getMax();
    }

    // 查看队首元素
    public E peekFirst() {
        return maxHeap.findMax();
    }

    // 查看队尾元素
    public E peekLast() {
        return maxHeap.findLast();
    }
}
