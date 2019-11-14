package com.garytry.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * @Description:
 * @Author gengds
 * @Date 2019/10/25
 * @Version V1.0
 */
public abstract class AbstractCollection<E> implements Collection<E> {

    //要分配的数组的最大大小
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 唯一的构造器
     */
    protected AbstractCollection() {
    }

    /**
     * 返回集合中元素的迭代器
     *
     * @return 返回集合中元素的迭代器
     */
    public abstract Iterator<E> iterator();

    /**
     * 集合元素数量
     *
     * @return 集合元素数量
     */
    public abstract int size();

    /**
     * 判断集合是否非空
     *
     * @return 如果集合为空返回true
     */
    public boolean isEmpty() {
        //集合元素数量为0，返回true
        return size() == 0;
    }

    /**
     * 集合是否包含指定的元素
     *
     * @param o 指定元素
     * @return 如果包含返回true
     */
    public boolean contains(Object o) {
        //获取集合迭代器
        Iterator<E> it = iterator();
        //如果需要比较的元素为null，则判断当前集合中是否存在null元素，存在返回true
        if (o == null) {
            while (it.hasNext())
                if (it.next() == null)
                    return true;
        } else {
            //如果需要比较的元素非空，则判断当前集合中是否存在相等元素，存在返回true
            while (it.hasNext())
                if (o.equals(it.next()))
                    return true;
        }
        return false;
    }

    /**
     * 返回包含此集合中所有元素的数组
     *
     * @return 返回包含此集合中所有元素的数组
     */
    public Object[] toArray() {
        //(1)根据当前集合数量创建数组
        Object[] r = new Object[size()];
        //(2)获取集合迭代器
        Iterator<E> it = iterator();
        //(3)循环数组，将当前集合中的元素复制到数组中（复制引用）
        for (int i = 0; i < r.length; i++) {
            if (!it.hasNext())
                //(4)如果集合中元素比预期的少，则调用Arrays.copyOf()方法将数组的元素复制到新数组中，并返回新数组,数组元素为i个
                return Arrays.copyOf(r, i);
            r[i] = it.next();
        }
        //(5)如果集合中元素比预期的多，则调用finishToArray方法生成新数组，并返回新数组，否则返回(1)中创建的数组
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    /**
     * 通过泛型约束将此集合中所有元素转换成指定类型的数组
     * @param a   需要转换为数组的集合
     * @param <T> 集合的运行类型
     * @return 含此集合中所有元素的数组
     */
    public <T> T[] toArray(T[] a) {
        // 估计数组的大小；准备看到更多或更少的元素
        int size = size();
        //如果数组a的长度大于当前集合的长度，则数组r=a；否则根据集合大小size创建新数组赋值给r
        //通过 数组的 class 对象的getComponentType()方法可以取得一个数组的Class对象
        //通过Array.newInstance()可以反射生成数组对象
        T[] r = a.length >= size ? a :
                (T[]) java.lang.reflect.Array
                        .newInstance(a.getClass().getComponentType(), size);
        //获取集合迭代器
        Iterator<E> it = iterator();
        //循环数组r，将当前集合中的元素复制到数组中（复制引用）
        for (int i = 0; i < r.length; i++) {
            if (!it.hasNext()) { //集合元素比预期的少
                if (a == r) {
                    //如果数组是参数中的数组，则将剩余部分的值都设置为null
                    r[i] = null; // null-terminate
                } else if (a.length < i) {
                    //如果传入的数组长度小于集合长度，则调用Arrays.copyOf()方法将数组的元素复制到新数组中，并返回新数组,数组元素为i个
                    return Arrays.copyOf(r, i);
                } else {
                    //如果传入数组的长度比集合大，则将多的元素设置为空
                    //src:源数组；srcPos:源数组要复制的起始位置；dest:目的数组；destPos:目的数组放置的起始位置；length:复制的长度
                    System.arraycopy(r, 0, a, 0, i);
                    if (a.length > i) {
                        a[i] = null;
                    }
                }
                return a;
            }
            r[i] = (T) it.next();
        }
        //如果集合中元素比预期的多，则调用finishToArray方法生成新数组，并返回新数组，否则返回数组r
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    /**
     * 当迭代器返回的元素比预期的多时，重新分配ToArray中使用的数组，并从迭代器中完成填充
     *
     * @param r  数组，充满了以前存储的元素
     * @param it 此集合上正在进行的迭代器
     * @return 包含给定数组中元素的数组，再加上迭代器返回的任何其他元素，按大小调整
     */
    private static <T> T[] finishToArray(T[] r, Iterator<?> it) {
        int i = r.length;
        while (it.hasNext()) {
            int cap = r.length;
            //当数组索引指向最后一个元素+1时，对数组进行扩容：即创建一个更长的数组，然后将原数组的内容复制到新数组中
            if (i == cap) {
                //扩容大小：cap + cap/2 +1
                int newCap = cap + (cap >> 1) + 1;
                // 扩容前需要先判断是否数组长度是否溢出
                if (newCap - MAX_ARRAY_SIZE > 0)
                    newCap = hugeCapacity(cap + 1);
                r = Arrays.copyOf(r, newCap);
            }
            r[i++] = (T) it.next();
        }
        // trim if overallocated
        return (i == r.length) ? r : Arrays.copyOf(r, i);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError
                    ("Required array size too large");
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * 添加元素
     *
     * @param e 元素
     * @return 添加成功返回true
     */
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    /**
     * 刪除元素
     *
     * @param o 元素
     * @return 刪除成功返回true(一次只能删除一个)
     */
    public boolean remove(Object o) {
        //获取集合迭代器
        Iterator<E> it = iterator();
        //如果需要删除的元素为null，则删除当前集合中的null元素，并返回true
        if (o == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    it.remove();
                    return true;
                }
            }
        } else {
            //如果需要删除的元素非空
            while (it.hasNext()) {
                //如果元素相同，则删除，并返回true
                if (o.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 如果此集合包含指定集合中的所有元素，则返回true
     *
     * @param c 指定集合
     * @return 如果此集合包含指定集合中的所有元素，则返回true
     */
    public boolean containsAll(Collection<?> c) {
        //循环遍历集合c
        for (Object e : c)
            //如果集合c中的元素e不存在当前集合中，则返回false，否则返回true
            if (!contains(e))
                return false;
        return true;
    }

    /**
     * 添加指定集合中的所有元素
     *
     * @param c 指定集合
     * @return 存在添加成功返回true
     */
    public boolean addAll(Collection<? extends E> c) {
        //标识符
        boolean modified = false;
        //循环遍历集合c
        for (E e : c)
            //如果添加成功，并修改标识符modified为true
            if (add(e))
            modified = true;
        //返回结果：存在添加成功操作返回true
        return modified;
    }

    /**
     * 刪除指定集合中的所有元素
     *
     * @param c 指定集合
     * @return 存在删除成功操作则返回true
     */
    public boolean removeAll(Collection<?> c) {
        //判断集合c非空，为空抛NullPointerException异常
        Objects.requireNonNull(c);
        //标识符
        boolean modified = false;
        //获取集合迭代器
        Iterator<?> it = iterator();
        //判断是否存在下一元素
        while (it.hasNext()) {
            //如果c包含当前元素，则删除，并修改标识符modified为true
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        //返回修改结果：有修改则返回true，否则返回false
        return modified;
    }

    /**
     * 保留指定集合之中的所有元素，不在指定集合中的元素删除
     *
     * @param c 指定集合
     * @return 存在删除操作则返回true
     */
    public boolean retainAll(Collection<?> c) {
        //判断集合c非空，为空抛NullPointerException异常
        Objects.requireNonNull(c);
        //标识符
        boolean modified = false;
        //获取集合迭代器
        Iterator<E> it = iterator();
        //判断是否存在下一元素
        while (it.hasNext()) {
            //如果c不包含当前元素，则删除，并修改标识符modified为true
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        //返回修改结果：有修改则返回true，否则返回false
        return modified;
    }

    /**
     * 清空集合
     */
    public void clear() {
        //获取集合迭代器
        Iterator<E> it = iterator();
        //判断是否存在下一元素
        while (it.hasNext()) {
            //移动指针
            it.next();
            //删除元素
            it.remove();
        }
    }

    /**
     * 返回此集合的字符串表示形式
     *
     * @return 返回此集合的字符串表示形式
     */
    public String toString() {
        //获取迭代器
        Iterator<E> it = iterator();
        //集合为空，返回"[]"
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        //拼接字符串
        sb.append('[');
        for (; ; ) {
            //迭代，获取下一个集合元素
            E e = it.next();
            //如果e为当前集合拼接"(this Collection)" ,否则拼接元素内容
            sb.append(e == this ? "(this Collection)" : e);
            //如果不存在下一元素，拼接"]"后返回，结束循环
            if (!it.hasNext())
                return sb.append(']').toString();
            //拼接", "
            sb.append(',').append(' ');
        }
    }
}
