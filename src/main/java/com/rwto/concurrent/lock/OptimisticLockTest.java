package com.rwto.concurrent.lock;



/**
 * 乐观锁，伪代码
 * 体现一种乐观的态度，假设没人跟我争
 * 在数据处理时不加锁，在数据提交更新时，做检测
 * 所以，乐观锁其实不是锁，而是一种检测机制
 * 乐观锁一般都需要增加一个版本号的字段来协助完成
 * 利用update 或 insert 本身就是原子的特性  类似 CAS
 * 根据 update 和 insert 的结果判断 进行下一步操作
 * 与数据库本身的锁无关，所以不会死锁，性能也比较好
 * @author renmw
 * @create 2023/12/13 21:08
 **/
public class OptimisticLockTest {

    public static void main(String[] args) {
        new OptimisticLockTest().updateEntry(1);

        /**
         * 类似自旋的乐观锁，
         * 提供重试的功能
         */
        new OptimisticLockTest().updateEntryV2(1);
    }

    public void updateEntry(long id){
        /**
         * 从数据库中查询出相应数据 (含有当前版本号字段)
         * */
        //Entry entry = query("select * from tb_entry where id = #{id} for update ",id);

        /**
         * 在内存中计算，修改entry 对象
         */
        //entry.setName()...
        //...

        /**
         * 更新进数据库-update
         * 根据版本号 和 id 进行更新
         */
        //int count = update("update tb_entry set name = #{name}, version = #{version} + 1 where id = #{id} and version = #{version}", entry);

        /**
         * 根据count 是否为 0 判断当前版本是不是已经被更改了
         * 如果count = 0 说明 当前版本已经被更改了，需要重新尝试
         */
        //if(count == 0) 重试
    }

    public void updateEntryV2(long id){
        //最多重试5次
        int retryNum = 5;
        while(retryNum > 0){
            /**
             * 从数据库中查询出相应数据 (含有当前版本号字段)
             * */
            //Entry entry = query("select * from tb_entry where id = #{id} for update ",id);

            /**
             * 在内存中计算，修改entry 对象
             */
            //entry.setName()...
            //...

            /**
             * 更新进数据库-update
             * 根据版本号 和 id 进行更新
             */
            /*
            int count = update("update tb_entry set name = #{name}, version = #{version} + 1 where id = #{id} and version = #{version}", entry);
            if(count > 0){
                break;
            }
            */
            retryNum--;
        }

    }
}
