package com.garytry.linkedlist;

import java.util.ArrayList;

/**
 * @Description:
 * @Author gengds
 * @Date 2019/10/11
 * @Version V1.0
 */
public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        linkedList.add("5");
        linkedList.add(5);
        linkedList.add("7");
        linkedList.add("8");
        linkedList.add(new int[]{1,2,3});
        linkedList.add("9");
        linkedList.add("weqreqr");
        linkedList.list();
        System.out.println(linkedList.size());

        java.util.LinkedList linkedList1 = new java.util.LinkedList<>();
        linkedList1.add(1);

    }




}
