package com.gildedrose.items;

import com.gildedrose.Item;
import com.gildedrose.item_helpers.ItemHandler;
import com.gildedrose.item_helpers.ItemType;

public class AgedBrie implements ItemType {

    private final ItemHandler item;

    public AgedBrie(Item item) {
        this.item = new ItemHandler(item);
    }

    @Override
    public void updateQuality() {
        item.decrementSellInDate();
        if (item.beforeSellInDate()) {
            item.incrementQuality();
        } else {
            item.incrementQualityByTwo();
        }
    }

}
