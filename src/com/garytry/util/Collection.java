package com.garytry.util;

import java.util.Iterator;

/**
 * @Description: 集合接口
 * @Author gengds
 * @Date 2019/10/12
 * @Version V1.0
 */
public interface Collection<E> extends Iterable<E> {

    /**
     * 集合元素数量
     *
     * @return 集合元素数量
     */
    int size();

    /**
     * 判断集合是否非空
     *
     * @return 如果集合为空返回true
     */
    boolean isEmpty();

    /**
     * 集合是否包含指定的元素
     *
     * @param o 指定元素
     * @return 如果包含返回true
     */
    boolean contains(Object o);

    /**
     * 返回集合中元素的迭代器
     *
     * @return 返回集合中元素的迭代器
     */
    Iterator<E> iterator();

    /**
     * 返回包含此集合中所有元素的数组
     *
     * @return 返回包含此集合中所有元素的数组
     */
    Object[] toArray();

    /**
     * 添加元素
     *
     * @param e 元素
     * @return 添加成功返回true
     */
    boolean add(E e);

    /**
     * 刪除元素
     *
     * @param o 元素
     * @return 刪除成功返回true
     */
    boolean remove(Object o);

    /**
     * 如果此集合包含指定集合中的所有元素，则返回true
     *
     * @param c 指定集合
     * @return 如果此集合包含指定集合中的所有元素，则返回true
     */
    boolean containsAll(java.util.Collection<?> c);

    /**
     * 添加指定集合中的所有元素
     *
     * @param c 指定集合
     * @return 添加成功返回true
     */
    boolean addAll(java.util.Collection<? extends E> c);

    /**
     * 刪除指定集合中的所有元素
     *
     * @param c 指定集合
     * @return 刪除成功返回true
     */
    boolean removeAll(java.util.Collection<?> c);

    /**
     * 清空集合
     */
    void clear();

    /**
     * 比较指定的对象与此集合是否相等
     *
     * @param o 指定的对象
     * @return 相等返回true
     */
    boolean equals(Object o);

    /**
     * 返回当前集合的 hash code
     *
     * @return 当前集合的 hash code
     */
    int hashCode();
}
