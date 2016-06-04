package com.wxj.mdnote.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.wxj.mdnote.R;
import com.wxj.mdnote.presenter.NoteCreatePresenter;
import com.wxj.mdnote.view.INoteCreateView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoteCreateFragment extends Fragment implements INoteCreateView {


    EditText etSubject;
    EditText etContent;
    private ProgressDialog dialog;
    private NoteCreatePresenter presenter;

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

    }
}
