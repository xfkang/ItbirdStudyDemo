package com.itbird.thrread;

/**
 * 線程正確的退出方式
 * Created by itbird on 2022/3/24
 */
public class ThreadExitMethodTest {
    public static class WorkTask extends Thread {
        boolean isExit = false;

        @Override
        public void run() {
            super.run();
            while (!isExit) {
                System.out.println("Current Thread is running!");
            }
        }

        public void shutDown() {
            if (isAlive()) {
                //注意，interrupt()的作用是通过抛出异常，中断本线程，对于死锁情况无法停止
                interrupt();
                isExit = true;
            }
        }
    }

    public static void main(String[] args) {
        WorkTask task = new WorkTask();
        task.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task.shutDown();
        task = null;
        System.out.println("Main Thread exit!");
    }
}
