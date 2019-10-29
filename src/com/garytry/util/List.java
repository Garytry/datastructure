package com.garytry.util;

import java.util.ListIterator;

/**
 * @Description: 列表接口
 * @Author gengds
 * @Date 2019/10/22
 * @Version V1.0
 */
public interface List<E> extends Collection<E> {

    /**
     * 根据索引位置获取元素
     *
     * @param index 索引位置
     * @return 元素
     */
    E get(int index);

    /**
     * 替换指定索引位置的元素
     *
     * @param index   要替换的元素的索引
     * @param element 要存储在指定位置的元素
     * @return 以前位于指定位置的元素
     */
    E set(int index, E element);


    /**
     * 在指定索引位置插入元素
     *
     * @param index   要插入指定元素的索引
     * @param element 要插入的元素
     */
    void add(int index, E element);

    /**
     * 移除指定索引位置的元素
     *
     * @param index 指定索引位置
     * @return 移除的元素
     */
    E remove(int index);

    /**
     * 在这个列表中返回指定元素的第一个匹配项的索引
     *
     * @param o 指定元素
     * @return 第一个匹配项的索引
     */
    int indexOf(Object o);

    /**
     * 在这个列表中返回指定元素的最后一个匹配项的索引
     *
     * @param o 指定元素
     * @return 最后一个匹配项的索引
     */
    int lastIndexOf(Object o);


    /**
     * 返回此列表中元素的列表迭代器,遍历该列表中的元素（按正确的顺序)
     *
     * @return 返回此列表中元素的列表迭代器
     */
    ListIterator<E> listIterator();

    /**
     * 返回一个列表迭代器，从列表中的指定位置开始，遍历该列表中的元素（按正确的顺序)
     *
     * @param index 指定位置
     * @return 返回一个列表迭代器
     */
    ListIterator<E> listIterator(int index);

    /**
     * 返回一个子列表，在fromIndex和toIndex之间的元素
     *
     * @param fromIndex 开始位置索引
     * @param toIndex   结束位置索引
     * @return 返回一个子列表
     */
    List<E> subList(int fromIndex, int toIndex);
}
