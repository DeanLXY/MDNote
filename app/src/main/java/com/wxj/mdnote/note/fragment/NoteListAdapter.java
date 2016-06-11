package com.wxj.mdnote.note.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxj.mdnote.R;
import com.wxj.mdnote.note.model.entity.Note;

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
public abstract class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder> {
    private Context context;
    private List<Note> all;

    public NoteListAdapter(Context context, List<Note> all) {
        this.context = context;
        this.all = all;
    }

    @Override
    public NoteListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_note, null);

        return new NoteListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NoteListViewHolder holder, int position) {
        final Note note = all.get(position);
        holder.tvSubject.setText(note.getSubject());
        holder.tvContent.setText(note.getContent());
        if (note.getLastModifyTime() == null || "".equals(note.getLastModifyTime())) {
            holder.tvLastModifyTime.setText(note.getCreateTime());
        } else {
            holder.tvLastModifyTime.setText(note.getLastModifyTime());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInnerClick(note);
            }
        });

         holder.iv_note_cover.setImageResource(note.getIconCover());
    }

    /**
     * 条目点击事件
     *
     * @param note
     */
    protected abstract void onInnerClick(Note note);


    @Override
    public int getItemCount() {
        if (all == null) {
            return 0;
        }
        return all.size();
    }

    public class NoteListViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvSubject;
        private final ImageView ivIcon;
        private final TextView tvContent;
        private final TextView tvLastModifyTime;
        private final View actionFavorite;
        private final View actionExpand;
        private final View actionShare;
        private final ImageView iv_note_cover;

        public NoteListViewHolder(View view) {
            super(view);
            tvSubject = (TextView) view.findViewById(R.id.tv_note_subject);
            tvLastModifyTime = (TextView) view.findViewById(R.id.tv_note_last_modify_time);
            tvContent = (TextView) view.findViewById(R.id.et_category_content);
            ivIcon = (ImageView) view.findViewById(R.id.iv_category_icon);
            iv_note_cover= (ImageView) view.findViewById(R.id.iv_note_cover);



            // action
            actionFavorite = view.findViewById(R.id.iv_note_action1);
            actionShare = view.findViewById(R.id.iv_note_action2);
            actionExpand = view.findViewById(R.id.iv_note_action3);


        }
    }
}
