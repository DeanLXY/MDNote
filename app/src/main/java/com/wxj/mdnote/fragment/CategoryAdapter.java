package com.wxj.mdnote.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wxj.mdnote.R;
import com.wxj.mdnote.model.entry.Category;

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
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.RunningViewHolder> {
    private Context context;

    public CategoryAdapter(Context context) {
        this.context = context;
    }


    @Override
    public RunningViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_category, null);
        return new RunningViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RunningViewHolder holder, int position) {
        Category category = Category.Default.categories.get(position);
        holder.textView.setText( category.toString());
        holder.ivIcon.setImageResource(category.getBg());
    }

    @Override
    public int getItemCount() {
        return Category.Default.categories.size();
    }

    public class RunningViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private final ImageView ivIcon;

        public RunningViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.tv_category_symbol);
            ivIcon = (ImageView) view.findViewById(R.id.iv_category_icon);
        }
    }
}
