package com.asm.demo;

import android.util.Log;

import java.util.concurrent.Executors;

public class ShadowThread extends Thread {

    private final Runnable mRunnable;

    public ShadowThread(Runnable target) {
        this.mRunnable = target;
    }

    @Override
    public synchronized void start() {
        Log.e("ShadowThread", "start,name=" + getName());
        Executors.newCachedThreadPool().submit(new MyRunnable(getName()));
    }

    @Override
    public void run() {
        if (mRunnable != null) {
            mRunnable.run();
        }
    }

    class MyRunnable implements Runnable {

        String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                ShadowThread.this.run();
            } catch (Exception e) {
                RuntimeException exception = new RuntimeException("threadName=" + name + ",exception:" + e.getMessage());
                exception.setStackTrace(e.getStackTrace());
                throw exception;
            }
        }
    }
}