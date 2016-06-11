package com.wxj.mdnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.konifar.fab_transformation.FabTransformation;
import com.roughike.bottombar.BottomBar;
import com.wxj.mdnote.cpf.CitySupportActivity;
import com.wxj.mdnote.note.fragment.CategoryAdapter;
import com.wxj.mdnote.note.fragment.NoteListAdapter;
import com.wxj.mdnote.note.CategoryCreateActivity;
import com.wxj.mdnote.note.NoteCreateActivity;
import com.wxj.mdnote.note.model.entity.Account;
import com.wxj.mdnote.note.model.entity.Note;
import com.wxj.mdnote.note.presenter.NoteListPresenter;
import com.wxj.mdnote.note.view.INoteListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, INoteListView, View.OnLongClickListener, NavigationView.OnNavigationItemSelectedListener {

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
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        presenter.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ralm
        presenter = new NoteListPresenter(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("笔记");
        fab = (FloatingActionButton) findViewById(R.id.fab);
        rv_running_task = (RecyclerView) findViewById(R.id.rv_running_task);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        initRunningTaskRecyclerView();

        rv_ruuning_category = (RecyclerView) findViewById(R.id.rv_ruuning_category);
        overlay = findViewById(R.id.overlay);
        initRecyclerView();
        overlay.setOnClickListener(this);
        fab.setOnClickListener(this);
        fab.setOnLongClickListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.openDrawerContentDescRes,
                R.string.closeDrawerContentDescRes) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                fab.hide();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                fab.show();
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initRunningTaskRecyclerView() {
        noteListAll = presenter.findAll();
        rv_running_task.setHasFixedSize(true);
        rv_running_task.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        noteListAdapter = new NoteListAdapter(this, noteListAll) {
            @Override
            protected void onInnerClick(Note note) {

            }
        };
        rv_running_task.setAdapter(noteListAdapter);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_other){
            startActivity(new Intent(getBaseContext(),CitySupportActivity.class));
        }



        drawerLayout.closeDrawer(Gravity.LEFT);
        return true;
    }
}
