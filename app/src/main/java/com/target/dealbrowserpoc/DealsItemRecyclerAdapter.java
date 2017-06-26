package com.target.dealbrowserpoc;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.target.dealbrowserpoc.base.BindableRecyclerAdapter;
import com.target.dealbrowserpoc.base.BindableViewHolder;
import com.target.dealbrowserpoc.data.models.DealItem;
import com.target.dealbrowserpoc.detail.DetailActivity;

import java.util.List;

/**
 * {@link BindableRecyclerAdapter} extension for handling recycler view with {@link DealItem} data
 */
public class DealsItemRecyclerAdapter extends BindableRecyclerAdapter<DealItem> {

    @Override
    public BindableViewHolder<DealItem> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_deals, parent, false);
        return new DealItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BindableViewHolder<DealItem> holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.itemView.setOnClickListener((view) -> {
            Intent intent = DetailActivity.getCallingIntent(holder.itemView.getContext(), elements.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    public void setData(List<DealItem> dealItems) {
        elements.clear();
        elements.addAll(dealItems);
        notifyDataSetChanged();
    }
}
