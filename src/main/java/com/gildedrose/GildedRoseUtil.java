package com.gildedrose;

public class GildedRoseUtil {

    private static final String CHEESE_ITEM_INDICATOR = "Aged Brie";
    private static final String BACKSTAGE_PASSES_ITEM_INDICATOR = "Backstage passes";
    private static final String LEGENDARY_ITEM_INDICATOR = "Sulfuras";
    private static final String CONJURED_ITEM_INDICATOR = "Conjured";

    public static boolean whereItemIsCheese(Item item) {
        return item.name.contains(CHEESE_ITEM_INDICATOR);
    }

    public static boolean whereItemIsBackstagePasses(Item item) {
        return item.name.contains(BACKSTAGE_PASSES_ITEM_INDICATOR);
    }

    public static boolean whereItemIsNotLegendary(Item item) {
        return !item.name.contains(LEGENDARY_ITEM_INDICATOR);
    }

    public static boolean whereItemIsConjured(Item item) {
        return item.name.contains(CONJURED_ITEM_INDICATOR);
    }

    public static boolean whereItemHasNotSpoiled(Item item) {
        return item.quality > 0;
    }

    public static boolean whereItemCloseToExpiration(Item item) {
        return item.sellIn < 11;
    }

    public static boolean whereItemVeryCloseToExpiration(Item item) {
        return item.sellIn < 6;
    }

    public static boolean whereItemHasExpired(Item item) {
        return item.sellIn < 0;
    }
}
