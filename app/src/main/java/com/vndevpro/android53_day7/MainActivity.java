package com.vndevpro.android53_day7;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ILoadProductsListener {
    private TextView tvDemo;
    private ImageView imgDemo;
    private ProgressBar pbDemo;
    private LinearLayout llLoading;
    public static final String API_PRODUCTS = "https://dummyjson.com/products";
    public static final String IMAGE_DEMO = "https://i.dummyjson.com/data/products/1/1.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDemo = findViewById(R.id.tvDemo);
        imgDemo = findViewById(R.id.imgDemo);
        llLoading = findViewById(R.id.llLoading);
        pbDemo = findViewById(R.id.pbDemo);

        asyncTask();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "run: ");
                Log.d("TAG", "run: "+Thread.currentThread().getName());
                tvDemo.setText("IM HAI");
            }
        };

        Thread thread = new Thread(runnable);
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("TAG", "run: "+Thread.currentThread().getName());
                tvDemo.setText("IM HAI 2222");
            }
        });
        thread.start();
        thread2.start();

        Handler handler = new Handler();
//        handler.post(runnable);
        handler.postDelayed(runnable, 5000);
    }

    private void asyncTask() {
        LoadProductsAsyncTask loadProductsAsyncTask = new LoadProductsAsyncTask(API_PRODUCTS, tvDemo);
        loadProductsAsyncTask.setProductsListener(this);
        loadProductsAsyncTask.execute();

//        new LoadImageAsyncTask(imgDemo).execute(IMAGE_DEMO);
        llLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadProductSuccess(ArrayList<ProductModel> productModels) {
        llLoading.setVisibility(View.GONE);
    }

    @Override
    public void onLoadProductError(String message) {
        llLoading.setVisibility(View.GONE);
    }
}