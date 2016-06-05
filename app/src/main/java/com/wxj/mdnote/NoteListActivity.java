package com.wxj.mdnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.konifar.fab_transformation.FabTransformation;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.wxj.mdnote.fragment.CategoryAdapter;
import com.wxj.mdnote.fragment.NoteListAdapter;
import com.wxj.mdnote.model.entry.Account;
import com.wxj.mdnote.model.entry.Note;
import com.wxj.mdnote.presenter.NoteListPresenter;
import com.wxj.mdnote.view.INoteListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity implements View.OnClickListener, INoteListView, View.OnLongClickListener {

    boolean isCategoryOpen = false;
    private FloatingActionButton fab;
    private RecyclerView rv_ruuning_category;
    private View overlay;
    private RecyclerView rv_running_task;
    private NoteListPresenter presenter;
    private BottomBar mBottomBar;
    private NoteListAdapter noteListAdapter;
    private List<Note> noteListAll;
    private Toolbar toolbar;

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        presenter.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        // ralm
        presenter = new NoteListPresenter(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        fab = (FloatingActionButton) findViewById(R.id.fab);

        rv_running_task = (RecyclerView) findViewById(R.id.rv_running_task);

        initRunningTaskRecyclerView();

        rv_ruuning_category = (RecyclerView) findViewById(R.id.rv_ruuning_category);
        overlay = findViewById(R.id.overlay);
        initRecyclerView();
        overlay.setOnClickListener(this);
        fab.setOnClickListener(this);
        fab.setOnLongClickListener(this);


        initBottomBar(savedInstanceState);
    }

    private void initBottomBar(Bundle savedInstanceState) {
        mBottomBar = BottomBar.attach(this, savedInstanceState);
//        mBottomBar.noTopOffset();
//        mBottomBar.noNavBarGoodness();
        // bottombar
        mBottomBar.setItems(
                new BottomBarTab(R.drawable.ic_restore_black_24dp, "Recents"),
                new BottomBarTab(R.drawable.ic_person_black_24dp, "Favorites"),
//                new BottomBarTab(R.drawable.ic_person_black_24dp, "Favorites"),
                new BottomBarTab(R.drawable.ic_done_black_24dp, "Nearby")
        );
        // 4个以上才有效果  源码加了判断
//        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
//        mBottomBar.mapColorForTab(1, 0xFFFF0000);
//        mBottomBar.mapColorForTab(2, "#7B1FA2");
//        mBottomBar.mapColorForTab(3, 0xFFFF0000);

//         Instead of attach(), use attachShy():
//        mBottomBar = BottomBar.attachShy((CoordinatorLayout) findViewById(R.id.main_content),
//                findViewById(R.id.ns), savedInstanceState);
    }

    private void initRunningTaskRecyclerView() {
        noteListAll = presenter.findAll();
        rv_running_task.setHasFixedSize(true);
        rv_running_task.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        noteListAdapter = new NoteListAdapter(this, noteListAll){
            @Override
            protected void onInnerClick(Note note) {
                showBottomSheet(note);
            }
        };
        rv_running_task.setAdapter(noteListAdapter);
    }

    private void showBottomSheet(Note note) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);

    }

    private void initRecyclerView() {
        rv_ruuning_category.setHasFixedSize(true);
        rv_ruuning_category.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rv_ruuning_category.setAdapter(new CategoryAdapter(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isCategoryOpen) {
            closeCategoryList();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == overlay) {
            closeCategoryList();

        } else if (v == fab) {
//            openCategoryList();
            presenter.createNewNote();
        }
    }

    private void closeCategoryList() {
        isCategoryOpen = false;
        FabTransformation.with(fab).setOverlay(overlay).transformFrom(rv_ruuning_category);
    }

    private void openCategoryList() {
        isCategoryOpen = true;
        FabTransformation.with(fab).setOverlay(overlay).transformTo(rv_ruuning_category);
    }

    @Override
    public void createNewCategory() {
        startActivity(new Intent(this, CategoryCreateActivity.class));
    }

    @Override
    public void onStartSearch(String keyword) {

    }

    @Override
    public void requestUserInfo(Account account) {

    }

    @Override
    public void createNewNote() {
        startActivity(new Intent(getBaseContext(), NoteCreateActivity.class));
    }


    @Override
    public void notifyDataSetChange() {

        noteListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onLongClick(View v) {
        if (v == fab) {
//            presenter.createNewCategory();
            presenter.createNewNote();
        }
        return false;
    }
}
