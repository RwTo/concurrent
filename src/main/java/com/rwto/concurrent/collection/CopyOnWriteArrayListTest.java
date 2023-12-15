package com.rwto.concurrent.collection;

/**
 * 写时复制：
 * 在写操作时，先复制一份样本，在新的样本里进行写操作，不对原始版本操作
 * 因此 对于读操作会有弱一致性的问题
 * */

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 这是一个支持并发操作的list数据
 * 实现原理是 通过内部ReentrantLock独占锁 保证线程安全，通过写时复制完成list 所有写操作（增删改），但读操作会有弱一致性的问题
 * 迭代器 迭代的是获取迭代器时的快照list
 * @author renmw
 * @create 2023/12/15 17:11
 **/
public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> arrayList = new CopyOnWriteArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);

        Thread thread = new Thread(() -> {
            arrayList.add(7);
            arrayList.add(8);
        });
        /**
         * 线程开始之前获取迭代器，内部保存当前list中的快照版本
         */
        Iterator<Integer> iterator = arrayList.iterator();
        thread.start();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
