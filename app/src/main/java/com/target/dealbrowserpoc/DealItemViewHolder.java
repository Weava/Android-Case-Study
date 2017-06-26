package com.target.dealbrowserpoc;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fernandocejas.arrow.strings.Strings;
import com.squareup.picasso.Picasso;
import com.target.dealbrowserpoc.base.BindableViewHolder;
import com.target.dealbrowserpoc.data.models.DealItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link BindableViewHolder} extension for displaying information from {@link DealItem}s.
 */
public class DealItemViewHolder extends BindableViewHolder<DealItem> {

    @BindView(R.id.item_image)
    ImageView dealItemImage;

    @BindView(R.id.item_name)
    TextView dealItemName;

    @BindView(R.id.item_price)
    TextView dealItemPrice;

    @BindView(R.id.item_aisle)
    TextView dealItemAisle;

    public DealItemViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(DealItem item) {
        Picasso.with(itemView.getContext())
                .load(item.getImageUrl())
                .into(dealItemImage);

        dealItemName.setText(item.getTitle());

        if (Strings.isNullOrEmpty(item.getSalePrice())) {
            dealItemPrice.setText(item.getPrice());
        } else {
            dealItemPrice.setText(item.getSalePrice());
        }
        dealItemAisle.setText(item.getAisle());
    }
}
