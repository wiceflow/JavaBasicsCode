package com.wiceflow.Thread.pro;

/**
 * 消费者
 * Created by BF on 2017/9/13.
 */
public class Watcher implements Runnable {
    private Movie m ;

    public Watcher(Movie m) {
        super();
        this.m = m;
    }

    @Override
    public void run() {
        for(int i=0;i<20;i++){
            m.watch();
        }
    }

}