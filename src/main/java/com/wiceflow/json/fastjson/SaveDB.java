package com.wiceflow.json.fastjson;


/**
 * Created by BF on 2017/12/18.
 */
public class SaveDB implements Runnable {
    private BDindexServer b = new BDindexServer();
    private String date;
    public SaveDB(String date){
        this.date = date;
    }
    @Override
    public void run() {
        b.getData(date,true);
    }
}
