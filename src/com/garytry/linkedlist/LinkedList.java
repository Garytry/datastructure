package com.garytry.linkedlist;

/**
 * @Description: 自定义链表(单向链表)
 * @Author gengds
 * @Date 2019/10/2
 * @Version V1.0
 */
public class LinkedList<E> {
    //链表大小
    private int size;
    //链表首节点
    private Node first;

    /**
     * 添加节点元素
     *
     * @param element 节点元素
     */
    public void add(E element) {
        //创建节点对象
        Node node = new Node(element, null);
        //给链表添加首节点
        if (null == first) {
            this.first = node;
            this.size = 1;
            return;
        }
        //追加新节点
        Node node1 = this.first;
        while (null != next(node1)) {
            node1 = next(node1);
        }
        node1.next = node;
        this.size = size + 1;
    }

    public int size() {
        return this.size;
    }

    //当前节点的下一节点
    public Node next(Node node) {
        return node.next;
    }

    /**
     * 打印列表
     */
    public void list() {
        Node node = this.first;
        while (null != next(node)) {
            node = next(node);
            System.out.println(node.element);
        }
    }

    /**
     * 节点内部类
     *
     * @param <E>
     */
    private static class Node<E> {
        //节点元素值
        E element;
        //下一个节点元素
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

}
