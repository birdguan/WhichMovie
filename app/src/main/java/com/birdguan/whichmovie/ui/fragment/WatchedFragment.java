package com.birdguan.whichmovie.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.birdguan.whichmovie.R;
import com.birdguan.whichmovie.model.WatchedFilm;
import com.birdguan.whichmovie.model.WatchedFilmAdapter;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: birdguan
 * @Date: 2020/6/19 17:05
 */
public class WatchedFragment extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_watched, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_watched);

        List<WatchedFilm> watchedFilmList = LitePal.findAll(WatchedFilm.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        WatchedFilmAdapter watchedFilmAdapter = new WatchedFilmAdapter(getActivity(), watchedFilmList);
        recyclerView.setAdapter(watchedFilmAdapter);
        return view;
    }
}
