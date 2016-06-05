package com.wxj.mdnote.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wxj.mdnote.R;
import com.wxj.mdnote.model.entry.Category;
import com.wxj.mdnote.presenter.NoteCreatePresenter;
import com.wxj.mdnote.view.INoteCreateView;

import java.io.File;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteCreateFragment extends Fragment implements INoteCreateView, View.OnClickListener, AdapterView.OnItemSelectedListener {


    EditText etSubject;
    EditText etContent;
    private ProgressDialog dialog;
    private NoteCreatePresenter presenter;
    private View rl_note_bg_picker;
    private ImageView ivNoteCover;
    private AppCompatSpinner spinner_note_category;
    private String picPath;// 背景 路径
    private int currentCategoryIndex = 0;

    public NoteCreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note_create, container, false);
        etSubject = (EditText) view.findViewById(R.id.et_subject);
        etContent = (EditText) view.findViewById(R.id.et_content);
        rl_note_bg_picker = view.findViewById(R.id.rl_note_bg_picker);
        ivNoteCover = (ImageView) view.findViewById(R.id.iv_note_cover);
        spinner_note_category = (AppCompatSpinner) view.findViewById(R.id.spinner_note_category);
        spinner_note_category.setAdapter(new CategorySpinnerAdapter(getActivity(), Category.Default.categories));
        spinner_note_category.setOnItemSelectedListener(this);
        rl_note_bg_picker.setOnClickListener(this);
        presenter = new NoteCreatePresenter(this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_note_create, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_done) {
            presenter.createNewNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public String getSubject() {
        return etSubject.getText().toString();
    }

    @Override
    public String getContent() {
        return etContent.getText().toString();
    }

    @Override
    public Category getCategory() {
        return Category.Default.categories.get(currentCategoryIndex);
    }

    @Override
    public void showProgress() {
        if (dialog == null)
            dialog = ProgressDialog.show(getActivity(), null, getActivity().getString(R.string.txt_dialog_prompt));
        dialog.show();
    }

    @Override
    public void hideProgress() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    @Override
    public void clearNoteInfo() {

    }

    @Override
    public void finish() {
        getActivity().finish();

    }

    @Override
    public void onClick(View v) {
        if (v == rl_note_bg_picker) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.BaseDialogTheme);
            builder.setTitle(getString(R.string.picture_choice));
            builder.setItems(getResources().getStringArray(R.array.camera_picture_picker), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        startCameraPicker();
                    } else {
                        startPicturePicker();
                    }
                }
            });
            builder.setNegativeButton(android.R.string.cancel, null);
            builder.show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0x1) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                picPath = picturePath;
                System.out.println("picture = " + picturePath);
                Glide.with(this).load(new File(picturePath))
                        .centerCrop()
                        .into(ivNoteCover);
//                ivNoteCover.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                cursor.close();
            } else if (requestCode == 0x2) {
                final Bitmap photo = data.getParcelableExtra("data");
                ivNoteCover.setImageBitmap(photo);
            }
        }
    }

    private void startPicturePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, 0x1);
    }

    private void startCameraPicker() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0x2);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currentCategoryIndex = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class CategorySpinnerAdapter extends ArrayAdapter<Category> {


        public CategorySpinnerAdapter(Context context, List<Category> objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
        }
    }
}
