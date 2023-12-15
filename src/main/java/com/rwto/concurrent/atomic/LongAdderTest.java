package com.rwto.concurrent.atomic;
/**
 * 伪共享：
 * 由于局部性原理：线程每次将数据变量加载到自己的工作内存是按照缓存块添加的
 * 也就是可能一次加入地址相邻的多个变量，这对单线程是有益的
 * 但对于多线程是会影响性能的，多个线程操作同一个缓存行，为保证缓存信息的一直，需要不停的交换数据。
 */

/**
 * LongAdder 适用于高并发的场景
 * 内部维护一个Cell 数组 和一个base 值
 * 在 base cas 操作失败后，不是自旋而是 取Cell数组中的新元素进行CAS操作
 * 最后再 将所有元素求和
 * 为避免伪共享，Cell 类被 注解＠sun.misc Contended 修饰，填充缓存行
 *
 * @author renmw
 * @create 2023/12/15 16:23
 **/
public class LongAdderTest {
    public static void main(String[] args) {

    }
}
