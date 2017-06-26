package com.target.dealbrowserpoc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.target.dealbrowserpoc.data.DealsEndpoints;
import com.target.dealbrowserpoc.data.models.DealItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    RecyclerView dealsListView;

    @BindView(R.id.loading_view)
    ProgressBar progressBar;

    @Inject
    MainPresenter presenter;

    @Inject
    DealsEndpoints dealsEndpoints;

    private DealsItemRecyclerAdapter dealsItemRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        DiInjector.INSTANCE.getAppGraph().inject(this);

        setSupportActionBar(toolbar);

        dealsItemRecyclerAdapter = new DealsItemRecyclerAdapter();
        dealsListView.setLayoutManager(new LinearLayoutManager(this));
        dealsListView.setAdapter(dealsItemRecyclerAdapter);
        dealsListView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void fillDealsList(List<DealItem> dealItems) {
        dealsItemRecyclerAdapter.setData(dealItems);
        progressBar.setVisibility(View.GONE);
        dealsListView.setVisibility(View.VISIBLE);
    }
}
