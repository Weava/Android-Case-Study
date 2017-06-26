package com.target.dealbrowserpoc.detail;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fernandocejas.arrow.strings.Strings;
import com.squareup.picasso.Picasso;
import com.target.dealbrowserpoc.R;
import com.target.dealbrowserpoc.data.models.DealItem;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity showing detail view for a specific {@link DealItem}.
 */
public class DetailActivity extends AppCompatActivity {

    private final static String DEAL_ITEM_INTENT = "deal_item";

    public static Intent getCallingIntent(Context context, DealItem dealItem) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DEAL_ITEM_INTENT, dealItem);
        return intent;
    }

    @BindView(R.id.item_image)
    ImageView itemImage;

    @BindView(R.id.item_sale_price)
    TextView itemSalePrice;

    @BindView(R.id.item_original_price)
    TextView itemOriginalPrice;

    @BindView(R.id.item_name)
    TextView itemName;

    @BindView(R.id.item_description)
    TextView itemDescription;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.item_original_price_prefix)
    TextView itemOriginalPricePrefix;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back);
            drawable.setTintList(ColorStateList.valueOf(ContextCompat.getColor(this, android.R.color.white)));
            getSupportActionBar().setHomeAsUpIndicator(drawable);
        }

        Serializable item = getIntent().getSerializableExtra(DEAL_ITEM_INTENT);

        if (item != null && item instanceof DealItem) {
            DealItem dealItem = (DealItem) item;

            Picasso.with(this)
                    .load(dealItem.getImageUrl())
                    .into(itemImage);

            if (Strings.isNullOrEmpty(dealItem.getSalePrice())) {
                itemSalePrice.setText(dealItem.getPrice());
                itemOriginalPrice.setVisibility(View.GONE);
                itemOriginalPricePrefix.setVisibility(View.GONE);
            } else {
                itemSalePrice.setText(dealItem.getSalePrice());

                itemOriginalPrice.setText(dealItem.getPrice());
                itemOriginalPrice.setPaintFlags(itemOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }

            itemName.setText(dealItem.getTitle());

            itemDescription.setText(dealItem.getDescription());

            getSupportActionBar().setTitle(dealItem.getTitle());
        } else {
            // If no item was passed in, then we leave this activity.
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.add_to_list_button)
    protected void onAddToListClicked(View v) {
        Toast.makeText(this, "Add to list coming soon", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.add_to_cart_button)
    protected void onAddToCartClicked(View v) {
        Toast.makeText(this, "Add to cart coming soon", Toast.LENGTH_SHORT).show();
    }
}
