package com.rwto.concurrent.basics;

public class ThreadJoinTestLock {
 
	public static void main(String[] args) {
		Object object = new Object();
		MThread mythread = new MThread("mythread ", object);
		mythread.start();
		//synchronized (mythread)
		synchronized (object) {
			for (int i = 0; i < 100; i++) {
				if (i == 20) {
					try {
						System.out.println("开始join");
						mythread.join();//main主线程让出CPU执行权，让mythread子线程优先执行
						System.out.println("结束join");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() +"==" + i);
			}
		}
		System.out.println("main方法执行完毕");
	}
}
 
class MThread extends Thread {
	private final String name;
	private final Object obj;
	public MThread(String name, Object obj) {
		this.name = name;
		this.obj = obj;
	}
	@Override
	public void run() {
		synchronized (obj) {
			for (int i = 0; i < 100; i++) {
				System.out.println(name + i);
			}
		}
	}
}