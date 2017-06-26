package com.target.dealbrowserpoc;

import com.target.dealbrowserpoc.base.BasePresenter;
import com.target.dealbrowserpoc.data.DealsEndpoints;
import com.target.dealbrowserpoc.data.ResultTransformer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * {@link BasePresenter} extension for interacting with {@link MainView} UI.
 */
public class MainPresenter extends BasePresenter<MainView> {

    private DealsEndpoints dealsEndpoints;

    public MainPresenter(DealsEndpoints dealsEndpoints) {
        this.dealsEndpoints = dealsEndpoints;
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        getDealItems();
    }

    /**
     * Retrieve {@link com.target.dealbrowserpoc.data.models.DealItem}s from the backend.
     */
    void getDealItems() {
                dealsEndpoints.getDeals()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(new ResultTransformer<>())
                        .subscribe((items) -> {
                            Timber.d("%d in list", items.getData().size());
                            getView().fillDealsList(items.getData());
                        }, Timber::e, () -> Timber.d("On Complete Called"));
    }
}
