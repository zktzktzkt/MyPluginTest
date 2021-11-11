package com.lpc.testgradle.thread;

public class TestThread {
    public static void main(String[] args){
        testWaitNotify();
    }
    static Object name = "aa";
    private static void testWaitNotify(){
        try {

            WaitThread1 thread1 = new WaitThread1(name);
            WaitThread2 thread2 = new WaitThread2(name);
            thread1.start();
//            Thread.sleep(2000);
            thread2.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
