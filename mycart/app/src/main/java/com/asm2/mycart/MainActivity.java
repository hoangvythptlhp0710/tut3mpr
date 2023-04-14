package com.asm2.mycart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.asm2.mycart.adapters.ProductAdapter;
import com.asm2.mycart.models.Category;
import com.asm2.mycart.models.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ProductAdapter adapter;
    private List<Product> products;

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

        addSampleData();


        adapter = new ProductAdapter(this, products);
        RecyclerView recyclerView = findViewById(R.id.productList);
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
//        Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
//        Constants.executorService.execute(() -> {
//            try {
//                JSONArray response = new JSONArray(Constants.getJSONFromAssets(this, "https://hanu-congnv.github.io/mpr-cart-api/products.json"));
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject product1 = response.getJSONObject(i);
//                        long id = product1.getLong("id");
//                        String thumbnail = product1.getString("thumbnail");
//                        String name = product1.getString("name");
//                        String category = product1.getString("category");
//                        double price = product1.getDouble("price");
//                        Product p = new Product(id, thumbnail, name, Category.getCategory(category), price);
//                        products.add(p);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                handler.post(() -> adapter.notifyDataSetChanged());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
    }



}