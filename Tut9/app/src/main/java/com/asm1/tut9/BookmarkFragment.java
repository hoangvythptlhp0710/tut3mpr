package com.asm1.tut9;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import javax.net.ssl.ManagerFactoryParameters;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookmarkFragment#} factory method to
 * create an instance of this fragment.
 */
public class BookmarkFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);

        //bind data

        //handle events

        LinearLayout zingmp3 = view.findViewById(R.id.zing);
        zingmp3.setOnClickListener(v -> {
            WebviewFragment webviewFragment = new WebviewFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", "https://zingmp3.vn");
            webviewFragment.setArguments(bundle);
            FragmentManager manager = getActivity().getSupportFragmentManager();

            manager.beginTransaction().replace(R.id.fragment_container, webviewFragment).commit();
        });
        return view;
    }
//
//
//    public View switchWeb() {
//        String url = "";
//        switch (view.getId()) {
//
//        }
//    }
}