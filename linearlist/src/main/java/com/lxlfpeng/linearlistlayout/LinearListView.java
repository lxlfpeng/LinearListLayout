package com.lxlfpeng.linearlistlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @Author : lxlfpeng
 * @Date : 2018/7/27
 * @Email : 565289282@qq.com
 * @Desc :
 */
public class LinearListView extends LinearLayout implements LinearListAdapter.OnDataChangedListener {

    /**
     * 默认布局参数
     */
    private final LayoutParams defaultItemParams;

    /**
     * 数据适配器
     */
    private LinearListAdapter adapter;

    public LinearListView(Context context) {
        this(context, null);
    }

    public LinearListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        defaultItemParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //子View垂直排列
        setOrientation(LinearLayout.VERTICAL);
    }

    /**
     * 设置数据适配器
     *
     * @param adapter
     */
    public void setAdapter(LinearListAdapter adapter) {
        this.adapter = adapter;
        this.adapter.setOnDataChangedListener(this);
        refreshList();
    }

    /**
     * 获取数据适配器
     *
     * @return
     */
    public LinearListAdapter getAdapter() {
        return adapter;
    }

    /**
     * 刷新列表
     */
    private void refreshList() {
        if (adapter == null) return;
        //重置所有View
        removeAllViews();
        for (int i = 0; i < adapter.getTotalCount(); i++) {
            View itemView = adapter.getItemView(i, adapter.getItemData(i), LayoutInflater.from(getContext()));
            //获取布局参数
            ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
            if (layoutParams == null) {
                itemView.setLayoutParams(defaultItemParams);
            }
            //设置添加事件
            final int position = i;
            itemView.setOnClickListener(view -> {
                if (adapter.getItemClickListener() != null) {
                    adapter.getItemClickListener().onItemClick(view, position, adapter.getItemData(position));
                }
            });
            //添加View
            addView(itemView);
        }
    }

    @Override
    public void onChanged() {
        refreshList();
    }
}

