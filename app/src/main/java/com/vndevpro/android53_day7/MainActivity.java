package com.vndevpro.android53_day7;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ILoadProductsListener {
    private TextView tvDemo;
    private ImageView imgDemo;
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

        LoadProductsAsyncTask loadProductsAsyncTask = new LoadProductsAsyncTask(API_PRODUCTS,tvDemo);
        loadProductsAsyncTask.setProductsListener(this);
        loadProductsAsyncTask.execute();

        new LoadProductsAsyncTask(API_PRODUCTS,tvDemo).execute();
//        LoadImageAsyncTask  loadImageAsyncTask = new LoadImageAsyncTask(imgDemo){
//            @Override
//            protected void onPostExecute(Bitmap bitmap) {
//                super.onPostExecute(bitmap);
//                imgDemo.setImageBitmap(bitmap);
//            }
//        };
//        loadImageAsyncTask.execute(IMAGE_DEMO);
        llLoading.setVisibility(View.VISIBLE);
//        try {
//            URL url = new URL(API_PRODUCTS);
//            try {
//                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//                InputStream stream = urlConnection.getInputStream();
//
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
//                StringBuilder stringBuilder = new StringBuilder();
//
//                String inputString;
//                while ((inputString = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(inputString);
//                }
//                Log.d("TAG", "doInBackground: " + stringBuilder);
//                Gson gson = new Gson();
//                ProductsResponse productsResponse =gson.fromJson(String.valueOf(stringBuilder),ProductsResponse.class);
//
//                Log.d("TAG", "doInBackground: limit : "+productsResponse.getProducts().size());
//                try {
//                    JSONObject jsonData = new JSONObject(String.valueOf(stringBuilder));
//                    int limit = jsonData.getInt("limit");
////                    Log.d("TAG", "doInBackground: " + limit);
//
//                    JSONArray productsData = jsonData.getJSONArray("products");
//                    for (int i = 0; i < productsData.length(); i++){
//                        JSONObject productData = productsData.getJSONObject(i);
//                        ProductModel productModel = new ProductModel();
////                        productModel.setId(productData.getInt("id"));
////                        res.add(productModel);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

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