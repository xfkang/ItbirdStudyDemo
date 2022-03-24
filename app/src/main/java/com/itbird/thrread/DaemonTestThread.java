package com.itbird.thrread;

/**
 * 守护线程Demo
 * Created by itbird on 2022/3/24
 */
public class DaemonTestThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Current Thread is running!");
                }
            }
        });
        //设置为守护线程
        thread.setDaemon(true);
        thread.start();
    }
}
