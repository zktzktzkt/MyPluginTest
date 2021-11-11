package com.lpc.testgradle.thread;

public class WaitThread2 extends Thread{
    private Object look;

    public WaitThread2(Object look) {
//        super();
        this.look = look;
    }

    @Override
    public void run() {
        super.run();

        try {
            synchronized (look){
                System.out.println("notify start...");
                look.notify();
                System.out.println("notify end...");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
