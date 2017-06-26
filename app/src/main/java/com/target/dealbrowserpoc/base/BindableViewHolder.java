package com.target.dealbrowserpoc.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Base class containing all common functionality for viewHolders.
 */
public abstract class BindableViewHolder<T> extends RecyclerView.ViewHolder {

    public BindableViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * Binds data from viewHolders defining model to the UI.
     *
     * @param item
     *         The data to bind to the UI
     */
    public abstract void bind(T item);
}