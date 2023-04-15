package com.asm2.mycart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.asm2.mycart.adapters.ProductAdapter;
import com.asm2.mycart.models.Category;
import com.asm2.mycart.models.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProductAdapter adapter;
    private List<Product> products;
    private String url = "https://hanu-congnv.github.io/mpr-cart-api/products.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        mRecyclerView = findViewById(R.id.productList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        // Initialize product list and adapter
        products = new ArrayList<>();

        getSampleData(url);

        @SuppressLint("CutPasteId") RecyclerView recyclerView = findViewById(R.id.productList);
        recyclerView.setAdapter(adapter);

    }

    public void addSampleData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://hanu-congnv.github.io/mpr-cart-api/products.json";
        @SuppressLint("NotifyDataSetChanged") JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject product1 = response.getJSONObject(i);
                    long id = product1.getLong("id");
                    String thumbnail = product1.getString("thumbnail");
                    String name = product1.getString("name");
                    String category = product1.getString("category");
                    double price = product1.getDouble("unitPrice");
                    Product p = new Product(id, thumbnail, name, Category.getCategory(category), price);
                    products.add(p);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            adapter.notifyDataSetChanged();
        }, error -> Log.e("Volley", error.toString()));
        requestQueue.add(jsonArrayRequest);
    }

    public List<Product> getSampleData(String url) {
        Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
        Constants.executor.execute(new Runnable() {
            @Override
            public void run() {
                String json = loadJSON(url);
                Log.d("JSON", json);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
//                            JSONObject jsonObject = new JSONObject(json);
                            JSONArray jsonArray = new JSONArray(json);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                long id = object.getLong("id");
                                String thumbnail = object.getString("thumbnail");
                                String name = object.getString("name");
                                String category = object.getString("category");
                                double unitPrice = object.getDouble("unitPrice");
                                Product p = new Product(id, thumbnail, name, Category.getCategory(category), unitPrice);
                                products.add(p);
                                adapter = new ProductAdapter(MainActivity.this, products);
                                mRecyclerView.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        return products;
    }


    public String loadJSON(String link) {
        URL url;
        HttpURLConnection urlConnection;
        try {
            url = new URL(link);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            InputStream is = urlConnection.getInputStream();
            Scanner sc = new Scanner(is);
            StringBuilder result = new StringBuilder();
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}