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
public abstract class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder> implements View.OnLongClickListener {
    private Context context;
    private List<Note> all;

    public NoteListAdapter(Context context, List<Note> all) {
        this.context = context;
        this.all = all;
    }

    @Override
    public NoteListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_note, null);

        view.setOnLongClickListener(this);

        return new NoteListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteListViewHolder holder, int position) {
        Note note = all.get(position);
        holder.tvSubject.setText(note.getSubject());
        holder.tvContent.setText(note.getContent());
        if (note.getLastModifyTime() == null || "".equals(note.getLastModifyTime())){
            holder.tvLastModifyTime.setText(note.getCreateTime());
        }else{
            holder.tvLastModifyTime.setText(note.getLastModifyTime());

        }
    }

    @Override
    public int getItemCount() {
        if (all == null) {
            return 0;
        }
        return all.size();
    }

    @Override
    public abstract boolean onLongClick(View v) ;

    public class NoteListViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvSubject;
        private final ImageView ivIcon;
        private final TextView tvContent;
        private final TextView tvLastModifyTime;

        public NoteListViewHolder(View view) {
            super(view);
            tvSubject = (TextView) view.findViewById(R.id.tv_note_subject);
            tvLastModifyTime = (TextView) view.findViewById(R.id.tv_note_last_modify_time);
            tvContent = (TextView) view.findViewById(R.id.et_category_content);
            ivIcon = (ImageView) view.findViewById(R.id.iv_category_icon);
        }
    }
}
