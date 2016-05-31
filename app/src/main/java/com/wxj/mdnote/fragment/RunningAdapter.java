package com.wxj.mdnote.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wxj.mdnote.R;

/**
 * ====================
 * 版权所有 违法必究
 *
 * @author wangx
 * @project MDNote
 * @file BaseListAdapter
 * @create_time 2016/5/31
 * @github https://github.com/wangxujie
 * @blog http://wangxujie.github.io
 */
public class RunningAdapter extends RecyclerView.Adapter<RunningAdapter.RunningViewHolder> {
    private Context context;

    public RunningAdapter(Context context) {
        this.context = context;
    }


    @Override
    public RunningViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_category, null);
        return new RunningViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RunningViewHolder holder, int position) {
        holder.textView.setText("position" + position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class RunningViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public RunningViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.tv_category_symbol);
        }
    }
}
