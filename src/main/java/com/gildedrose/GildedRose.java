package com.gildedrose;

import java.util.Arrays;
import java.util.Objects;

class GildedRose {
    Item[] items;

    private static final String CHEESE_ITEM_INDICATOR = "Aged Brie";
    private static final String BACKSTAGE_PASSES_ITEM_INDICATOR = "Backstage passes";
    private static final String LEGENDARY_ITEM_INDICATOR = "Sulfuras";
    private static final String CONJURED_ITEM_INDICATOR = "Conjured";
    private static final Integer MAXIMUM_ITEM_QUALITY = 50;
    private static final Integer MINIMUM_ITEM_QUALITY = 0;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
                .filter(Objects::nonNull)
                .filter(this::whereItemIsNotLegendary)
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

    private boolean whereItemIsCheese(Item item) {
        return item.name.contains(CHEESE_ITEM_INDICATOR);
    }

    private boolean whereItemIsBackstagePasses(Item item) {
        return item.name.contains(BACKSTAGE_PASSES_ITEM_INDICATOR);
    }

    private boolean whereItemIsNotLegendary(Item item) {
        return !item.name.contains(LEGENDARY_ITEM_INDICATOR);
    }

    private boolean whereItemIsConjured(Item item) {
        return item.name.contains(CONJURED_ITEM_INDICATOR);
    }

    private boolean whereItemHasNotSpoiled(Item item) {
        return item.quality > 0;
    }

    private boolean whereItemCloseToExpiration(Item item) {
        return item.sellIn < 11;
    }

    private boolean whereItemVeryCloseToExpiration(Item item) {
        return item.sellIn < 6;
    }

    private boolean whereItemHasExpired(Item item) {
        return item.sellIn < 0;
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