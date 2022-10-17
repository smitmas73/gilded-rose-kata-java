package com.gildedrose;

import java.util.Arrays;
import java.util.Objects;

import static com.gildedrose.GildedRoseUtil.whereItemCloseToExpiration;
import static com.gildedrose.GildedRoseUtil.whereItemHasExpired;
import static com.gildedrose.GildedRoseUtil.whereItemHasNotSpoiled;
import static com.gildedrose.GildedRoseUtil.whereItemIsBackstagePasses;
import static com.gildedrose.GildedRoseUtil.whereItemIsCheese;
import static com.gildedrose.GildedRoseUtil.whereItemIsConjured;
import static com.gildedrose.GildedRoseUtil.whereItemVeryCloseToExpiration;

class GildedRose {
    Item[] items;

    private static final Integer MAXIMUM_ITEM_QUALITY = 50;
    private static final Integer MINIMUM_ITEM_QUALITY = 0;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
                .filter(Objects::nonNull)
                .filter(GildedRoseUtil::whereItemIsNotLegendary)
                .forEach(item -> {
                    handleItemQuality(item);
                    handleSellIn(item);
                });
    }

    private void handleItemQuality(Item item) {
        if (whereItemIsCheese(item)) {
            appreciateItemQuality(item);

            return;
        }

        if (whereItemIsBackstagePasses(item)) {
            appreciateItemQuality(item);

            if (whereItemCloseToExpiration(item)) {
                appreciateItemQuality(item);
            }

            if (whereItemVeryCloseToExpiration(item)) {
                appreciateItemQuality(item);
            }

            return;
        }

        if (whereItemIsConjured(item)) {
            depreciateItemQuality(item, 2);

            return;
        }

        if (whereItemHasNotSpoiled(item)) {
            depreciateItemQuality(item, 1);
        }
    }

    private void handleSellIn(Item item) {
        deprecateItemSellIn(item);

        if (whereItemHasExpired(item)) {
            handlePastSellInDate(item);
        }
    }

    private void handlePastSellInDate(Item item) {
        if (whereItemIsCheese(item)) {
            appreciateItemQuality(item);
            return;
        }

        if (whereItemIsBackstagePasses(item)) {
            item.quality = 0;
            return;
        }

        if (whereItemIsConjured(item)) {
            depreciateItemQuality(item, 2);
            return;
        }

        if (whereItemHasNotSpoiled(item)) {
            depreciateItemQuality(item, 1);
        }
    }

    private void depreciateItemQuality(Item item, Integer depreciationValue) {
        item.quality -= depreciationValue;

        if (item.quality < MINIMUM_ITEM_QUALITY) {
            item.quality = MINIMUM_ITEM_QUALITY;
        }
    }

    private void appreciateItemQuality(Item item) {
        if (item.quality < MAXIMUM_ITEM_QUALITY) {
            item.quality++;
        }
    }

    private void deprecateItemSellIn(Item item) {
        item.sellIn--;
    }
}