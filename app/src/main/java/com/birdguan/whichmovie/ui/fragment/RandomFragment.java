package com.birdguan.whichmovie.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.birdguan.whichmovie.R;

/**
 * @Author: birdguan
 * @Date: 2020/6/19 16:49
 */
public class RandomFragment extends Fragment {

    private View view;
    private Button buttonRandomSelect;
    private TextView textViewRandom;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_random, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonRandomSelect = (Button) view.findViewById(R.id.btn_random_select);
        textViewRandom = (TextView) view.findViewById(R.id.tv_random);

    }

    private void showResponse(String response) {

    }
}
