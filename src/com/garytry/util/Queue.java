package com.garytry.util;

/**
 * @Description: 队列接口
 * @Author gengds
 * @Date 2019/10/22
 * @Version V1.0
 */
public interface Queue<E> extends Collection<E> {

    /**
     * 将指定的元素插入此队列
     *
     * @param e 需要添加的元素
     * @return 元素添加成功，返回true
     */
    boolean offer(E e);

    /**
     * 检索并删除此队头元素
     *
     * @return 队头元素
     */
    E poll();

    /**
     * 检索此队头元素
     *
     * @return 队头元素
     */
    E peek();
}
