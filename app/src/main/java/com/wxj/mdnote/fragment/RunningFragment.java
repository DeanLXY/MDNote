package com.wxj.mdnote.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.konifar.fab_transformation.FabTransformation;
import com.wxj.mdnote.R;
import com.wxj.mdnote.presenter.INoteListEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class RunningFragment extends Fragment implements View.OnClickListener,INoteListEvent {


    private View view;
    private RecyclerView rv_ruuning_category;
    private FloatingActionButton fab;
    private View overlay;
    private RecyclerView rv_running_task;

    public RunningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_running, container, false);

        rv_running_task = (RecyclerView) (RecyclerView) view.findViewById(R.id.rv_running_task);
        rv_ruuning_category = (RecyclerView) view.findViewById(R.id.rv_ruuning_category);
//        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        overlay = view.findViewById(R.id.overlay);
        initRecyclerView();
//        fab.setOnClickListener(this);
        return view;
    }

    private void initRecyclerView() {
        rv_ruuning_category.setHasFixedSize(true);
        rv_ruuning_category.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_ruuning_category.setAdapter(new RunningAdapter(getContext()));
    }

    @Override
    public void onClick(View v) {
        FabTransformation.with(v).setOverlay(overlay).transformTo(rv_ruuning_category);
    }

    @Override
    public void onFabClick(FloatingActionButton fab) {
        FabTransformation.with(fab).setOverlay(overlay).transformTo(rv_ruuning_category);
    }
}
