package com.wxj.mdnote;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.konifar.fab_transformation.FabTransformation;
import com.wxj.mdnote.fragment.RunningAdapter;
import com.wxj.mdnote.fragment.RunningFragment;
import com.wxj.mdnote.presenter.INoteListEvent;

import io.realm.Realm;

public class NoteListActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab;
    private RecyclerView rv_ruuning_category;
    private View overlay;
    private RecyclerView rv_running_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        // ralm


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);


        rv_running_task = (RecyclerView) (RecyclerView) findViewById(R.id.rv_running_task);
        rv_ruuning_category = (RecyclerView) findViewById(R.id.rv_ruuning_category);
        overlay = findViewById(R.id.overlay);
        initRecyclerView();
        overlay.setOnClickListener(this);
        fab.setOnClickListener(this);
    }


    private void initRecyclerView() {
        rv_ruuning_category.setHasFixedSize(true);
        rv_ruuning_category.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rv_ruuning_category.setAdapter(new RunningAdapter(this));
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
    public void onClick(View v) {
        if (v == overlay) {
            FabTransformation.with(fab).setOverlay(overlay).transformFrom(rv_ruuning_category);
        } else if (v == fab) {

            FabTransformation.with(fab).setOverlay(overlay).transformTo(rv_ruuning_category);
        }
    }
}
