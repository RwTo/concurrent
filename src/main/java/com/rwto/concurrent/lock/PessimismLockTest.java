package com.rwto.concurrent.lock;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 体现一种悲观的态度，假设有人跟我争，我要做好防御
 * 在数据处理前，就加锁
 * 悲观锁，伪代码
 * @author renmw
 * @create 2023/12/13 21:08
 **/
public class PessimismLockTest {
    public static void main(String[] args) {
        /**
         * 这是一个悲观锁的标准实现
         * 在一个事务中，使用 for update 语句，对该id 对应的行进行加锁
         * 同一时间，其他事务被阻塞
         * 只有当执行完update，锁释放后，其他线程才开始执行
         */
        new PessimismLockTest().updateEntry(1);
    }

    /**
     * 使用REQUIRED 事务传播机制，如果当前存在事务，则加入当前事务，否则自己开启一个事务
     * 因此，此方法内的所有sql 处于同一个事物中
     * @param id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateEntry(long id){
        /**
         * 从数据库中查询出相应数据
         * 使用for update 关键字，进行显示加锁
         * */
        //Entry entry = query("select * from tb_entry where id = #{id} for update ",id);

        /**
         * 在内存中计算，修改entry 对象
         */
        //entry.setName()...
        //...

        /**
         * 更新进数据库
         * update
         */
        //update("update tb_entry set name = #{name} where id = #{id}", entry);
    }
}
