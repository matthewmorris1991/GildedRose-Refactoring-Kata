package com.platinumrose.advance.quality

import com.platinumrose.ItemConstant.Companion.REGULAR_ITEM_MAX_QUALITY
import com.platinumrose.ItemType.REGULAR


internal class RegularItem : QualityCalculator {

    override fun type(): com.platinumrose.ItemType {
        return REGULAR
    }

    override fun maxQuality(): Int {
        return REGULAR_ITEM_MAX_QUALITY
    }

    override fun computeSellInDecrease(sellIn: Int): Int {
        return -1
    }

    override fun computeQualityIncreaseBeforeSellIn(sellIn: Int, quality: Int): Int {
        return -1
    }

    override fun computeQualityIncreaseAfterSellIn(sellIn: Int, quality: Int): Int {
        return -1
    }
}