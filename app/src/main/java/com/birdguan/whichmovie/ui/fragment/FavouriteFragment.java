package com.birdguan.whichmovie.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.birdguan.whichmovie.R;
import com.birdguan.whichmovie.model.CollectedFilm;
import com.birdguan.whichmovie.model.CollectedFilmAdapter;

import org.litepal.LitePal;

import java.util.List;

/**
 * @Author: birdguan
 * @Date: 2020/6/19 17:05
 */

/**
 * 收藏页Fragment
 */
public class FavouriteFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favourite, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_favourite);

        List<CollectedFilm> collectedFilmList = LitePal.findAll(CollectedFilm.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        CollectedFilmAdapter collectedFilmAdapter = new CollectedFilmAdapter(getActivity(), collectedFilmList);
        recyclerView.setAdapter(collectedFilmAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
