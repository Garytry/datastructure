package com.garytry.linkedlist;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

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
        linkedList.add(new int[]{1, 2, 3});
        linkedList.add("9");
        linkedList.add("weqreqr");
        linkedList.list();
        System.out.println(linkedList.size());
        java.util.LinkedList linkedList1 = new java.util.LinkedList<>();
        linkedList1.add(1);
 Integer[] a1 = new Integer[]{1, 2, 3, 4, 5};
 String[] s1 = new String[]{"one", "two", "three", "four"};
 String[] s2 = new String[]{"一", "二", "三", "四","无","dsadsafds"};
 Object[][] o1 = new Object[][]{a1, s1, s2};

 for (Object[] objects : o1) {
 System.out.println(Arrays.toString(objects));
 }

 List<Queue> list = new ArrayList<>();
 for (Object[] objects : o1) {
 Queue<Object> queue = new LinkedBlockingQueue<Object>();
 for (Object object : objects) {
 queue.add(object);
 }
 list.add(queue);
 }

 List<Object> res = new ArrayList<>();
 for (int i = 0; i < getLengh(o1); i++) {
 List<Object> list1 = new ArrayList<>();
 for (Queue o : list) {
 if (!o.isEmpty()){
 list1.add(o.poll());}
 }
 res.add(list1);
 }

 for (Object re : res) {
 System.out.println(re.toString());

 }



        // 1,1,2,3,5,8,13....
        long s11 = System.currentTimeMillis();
        Stack<Integer> stack = new Stack<Integer>();
        int a11 = 1;
        int a12 = 1;
        int max = 10000000;
        stack.push(a11);
        stack.push(a12);
        int aaa = a12;
        while (true) {
            a12 = stack.pop();
            aaa = a11 + a12;
            stack.push(a11);
            stack.push(a12);
            if (aaa >= max){
                break;
            }
            stack.push(aaa);
        }


        int num = stack.size();
        for (int i = 0; i < num; i++) {
            //System.out.println(stack.pop());
        }
        System.out.println(System.currentTimeMillis()-s11);

        long s22 = System.currentTimeMillis();
        sum(1,1,max);
        System.out.println(System.currentTimeMillis()-s22);

    }

    public static void sum(int a,int b,int max){
        int sum = a+b;
        if (sum <= max){
            sum(b,sum,max);
           // System.out.println(sum);
        }
    }

    public static int getLengh(Object[][] objects) {
        int length = 0;
        for (Object[] object : objects) {
            if (length < object.length) {
                length = object.length;
            }

        }
        return length;
    }


}
