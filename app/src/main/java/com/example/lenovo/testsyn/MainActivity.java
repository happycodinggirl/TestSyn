package com.example.lenovo.testsyn;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    Object lockObject=new Object();
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        textView = (TextView) findViewById(R.id.textview);
        MyThread thread1=new MyThread("thread 1",3);
        MyThread thread2=new MyThread("thread 2",6);
        MyThread thread3=new MyThread("thread 3",9);
        thread1.start();
        thread2.start();
        thread3.start();
    }
    class MyThread extends Thread{

        int limit;

        public MyThread(String threadName,int limit1){
            super(threadName);
            this.limit=limit1;

        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        @Override
        public void run() {
            super.run();
            synchronized (lockObject) {
                num++;

                Log.v("TAG", "- thread " + Thread.currentThread().getName() + " print  num is " + num);
            }
            if (num>limit){
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }



}
