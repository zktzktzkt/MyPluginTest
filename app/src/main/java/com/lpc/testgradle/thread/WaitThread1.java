package com.lpc.testgradle.thread;

import androidx.annotation.NonNull;

public class WaitThread1 extends Thread{
    private Object look;

    public WaitThread1( Object look) {
//        super();
        this.look = look;
    }

    @Override
    public void run() {
        super.run();

        try {
            synchronized (look){
                System.out.println("wait start...");
                look.wait();
                System.out.println("wait end...");

            }

        } catch (IllegalMonitorStateException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
