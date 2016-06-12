package com.wxj.mdnote.note.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.konifar.fab_transformation.FabTransformation;
import com.wxj.mdnote.R;
import com.wxj.mdnote.note.CategoryCreateActivity;
import com.wxj.mdnote.note.NoteCreateActivity;
import com.wxj.mdnote.note.model.entity.Account;
import com.wxj.mdnote.note.model.entity.Note;
import com.wxj.mdnote.note.presenter.NoteListPresenter;
import com.wxj.mdnote.note.view.INoteListView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteMainFragment extends Fragment implements INoteListView, View.OnClickListener {

    private RecyclerView rv_running_task;
    private NoteListPresenter presenter;
    private List<Note> noteListAll;

    private NoteListAdapter noteListAdapter;
    private FloatingActionButton fab;

    public NoteMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.layout_main, null);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        // ralm
        presenter = new NoteListPresenter(this);
        rv_running_task = (RecyclerView) view.findViewById(R.id.rv_running_task);
        initRunningTaskRecyclerView();
        return view;
    }
    private void initRunningTaskRecyclerView() {
        noteListAll = presenter.findAll();
        rv_running_task.setHasFixedSize(true);
        rv_running_task.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        noteListAdapter = new NoteListAdapter(getActivity(), noteListAll) {
            @Override
            protected void onInnerClick(Note note) {

            }
        };
        rv_running_task.setAdapter(noteListAdapter);
    }


    @Override
    public void createNewCategory() {
        startActivity(new Intent(getActivity(), CategoryCreateActivity.class));
    }

    @Override
    public void onStartSearch(String keyword) {

    }

    @Override
    public void requestUserInfo(Account account) {

    }

    @Override
    public void createNewNote() {
        startActivity(new Intent(getActivity(), NoteCreateActivity.class));
    }


    @Override
    public void notifyDataSetChange() {
        noteListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), NoteCreateActivity.class));
    }
}
