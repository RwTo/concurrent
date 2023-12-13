package com.rwto.concurrent.basics;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 *
 * @author renmw
 * @create 2023/12/13 16:17
 **/
public class UnSafeTest {

    //获 Unsafe 的实例
    static Unsafe unsafe;
    // 记录变量state在类Te st UnSafe 中的偏移位
    static long stateOffset;

    //变量 2.2  3
    private volatile long state = 0;



    static {
        try{

            Field field = Unsafe.class.getDeclaredField("theUnsafe");

            field.setAccessible(true);

            unsafe = (Unsafe) field.get(null);

            //／获取state 变量在类TestUnSafe 中的偏移值
            stateOffset = unsafe.objectFieldOffset(UnSafeTest.class.getDeclaredField("state"));

        }catch (Exception e){
            System.out.println(e);
        }
    }


    public static void main(String[] args) {
        //／／创建实例 ，并且设置state 直为 1
        UnSafeTest unSafeTest = new UnSafeTest();

        boolean success = unsafe.compareAndSwapInt(unSafeTest, stateOffset, 0, 1);
        System.out.println(success);
    }
}
