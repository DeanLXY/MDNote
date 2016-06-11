package com.wxj.mdnote.note;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wxj.mdnote.R;

public class CategoryCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_create);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
