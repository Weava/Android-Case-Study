package com.target.dealbrowserpoc;

import com.target.dealbrowserpoc.data.models.DealItem;

import java.util.List;

public interface MainView {

    /**
     * Fill list UI with {@link List<DealItem>} data
     *
     * @param dealItems
     *      {@link List<DealItem>} data
     */
    void fillDealsList(List<DealItem> dealItems);
}
