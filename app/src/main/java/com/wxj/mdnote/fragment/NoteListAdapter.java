package com.wxj.mdnote.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxj.mdnote.R;
import com.wxj.mdnote.model.entry.Note;

import java.util.List;

/**
 * ====================
 * 版权所有 违法必究
 *
 * @author wangx
 * @project MDNote
 * @file BaseListAdapter
 * @create_time 2016/6/4
 * @github https://github.com/wangxujie
 * @blog http://wangxujie.github.io
 */
public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder> {
    private Context context;
    private List<Note> all;

    public NoteListAdapter(Context context, List<Note> all) {
        this.context = context;
        this.all = all;
    }

    @Override
    public NoteListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_category, null);

        return new NoteListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteListViewHolder holder, int position) {
        Note note = all.get(position);
        holder.tvSymble.setText(note.getSubject());
        holder.tvContent.setText(note.getContent());

    }

    @Override
    public int getItemCount() {
        if (all == null) {
            return 0;
        }
        return all.size();
    }


    public class NoteListViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvSymble;
        private final ImageView ivIcon;
        private final TextView tvContent;

        public NoteListViewHolder(View view) {
            super(view);
            tvSymble = (TextView) view.findViewById(R.id.tv_category_symbol);
            tvContent = (TextView) view.findViewById(R.id.et_category_content);
            ivIcon = (ImageView) view.findViewById(R.id.iv_category_icon);
        }
    }
}
