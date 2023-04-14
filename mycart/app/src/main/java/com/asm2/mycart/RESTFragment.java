package com.asm2.mycart;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.os.HandlerCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class RESTFragment extends Fragment {

    public RESTFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String url =
                "https://hanu-congnv.github.io/mpr-cart-api/products.json";
        Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
        Constants.executorService.execute(new Runnable() {
            @Override
            public void run() {
                String json = loadJSON(url);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
// where you may interact with the UI
                    }
                });
            }
        });
            return null;
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
