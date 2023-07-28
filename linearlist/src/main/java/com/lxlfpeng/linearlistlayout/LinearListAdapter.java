package com.lxlfpeng.linearlistlayout;

import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : lxlfpeng
 * @Date : 2018/7/28
 * @Email : 565289282@qq.com
 * @Desc :
 */
public abstract class LinearListAdapter<T> {
    private List<T> list;
    /**
     * 数据变化的监听器
     */
    private OnDataChangedListener onDataChangedListener;
    /**
     * 点击事件的监听器
     */
    private OnItemClickListener<T> itemClickListener;

    public LinearListAdapter() {
        this.list = new ArrayList<>();
    }

    public LinearListAdapter(List<T> list) {
        this.list = list;
    }

    /**
     * 获取 条目总数
     *
     * @return 条目总数
     */
    public int getTotalCount() {
        return list.size();
    }

    /**
     * 获取对应position的数据
     *
     * @param position 条目角标
     * @return 数据
     */
    public T getItemData(int position) {
        return list.get(position);
    }

    /**
     * 获取对应条目的View
     *
     * @param position       条目角标
     * @param data           数据
     * @param layoutInflater 布局填充适配器
     * @return 对应创建的View
     */
    public abstract View getItemView(int position, T data, LayoutInflater layoutInflater);

    /**
     * 刷新所有的列表数据
     */
    public void notifyDataChanged() {
        if (onDataChangedListener != null) {
            onDataChangedListener.onChanged();
        }
    }

    /**
     * 设置新的数据
     *
     * @param data 新的数据集
     */
    public void setNewData(List<T> data) {
        this.list.clear();
        this.list.addAll(data);
        notifyDataChanged();
    }

    public void setOnDataChangedListener(OnDataChangedListener listener) {
        this.onDataChangedListener = listener;
    }


    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        itemClickListener = listener;
    }

    public OnItemClickListener<T> getItemClickListener() {
        return itemClickListener;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, int position, T data);
    }

    public interface OnDataChangedListener {
        void onChanged();
    }
}
