package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GildedRoseUtilityTest {

    @Test
    public void whereItemIsCheese_shouldReturnTrue_givenItemWithCheeseIndicator() {
        Item item = new Item(
                "Aged Brie",
                1,
                1
        );

        assertTrue(GildedRoseUtil.whereItemIsCheese(item));
    }

    @Test
    public void whereItemIsCheese_shouldReturnFalse_givenItemWithoutCheeseIndicator() {
        Item item = new Item(
                "foo",
                1,
                1
        );

        assertFalse(GildedRoseUtil.whereItemIsCheese(item));
    }

    @Test
    public void whereItemIsBackstagePasses_shouldReturnTrue_givenItemWithBackstagePassesIndicator() {
        Item item = new Item(
                "Backstage passes",
                1,
                1
        );

        assertTrue(GildedRoseUtil.whereItemIsBackstagePasses(item));
    }

    @Test
    public void whereItemIsBackstagePasses_shouldReturnFalse_givenItemWithoutBackstagePassesIndicator() {
        Item item = new Item(
                "foo",
                1,
                1
        );

        assertFalse(GildedRoseUtil.whereItemIsBackstagePasses(item));
    }

    @Test
    public void whereItemIsNotLegendary_shouldReturnTrue_givenItemWithoutLegendaryIndicator() {
        Item item = new Item(
                "foo",
                1,
                1
        );

        assertTrue(GildedRoseUtil.whereItemIsNotLegendary(item));
    }

    @Test
    public void whereItemIsNotLegendary_shouldReturnFalse_givenItemWithLegendaryIndicator() {
        Item item = new Item(
                "Sulfuras",
                1,
                1
        );

        assertFalse(GildedRoseUtil.whereItemIsNotLegendary(item));
    }

    @Test
    public void whereItemIsConjured_shouldReturnTrue_givenItemWithConjuredIndicator() {
        Item item = new Item(
                "Conjured",
                1,
                1
        );

        assertTrue(GildedRoseUtil.whereItemIsConjured(item));
    }

    @Test
    public void whereItemIsConjured_shouldReturnFalse_givenItemWithoutConjuredIndicator() {
        Item item = new Item(
                "Sulfuras",
                1,
                1
        );

        assertFalse(GildedRoseUtil.whereItemIsConjured(item));
    }

    @Test
    public void whereItemHasNotSpoiled_shouldReturnTrue_givenItemWithNonZeroQuality() {
        Item item = new Item(
                "foo",
                1,
                1
        );

        assertTrue(GildedRoseUtil.whereItemHasNotSpoiled(item));
    }

    @Test
    public void whereItemHasNotSpoiled_shouldReturnFalse_givenItemWithZeroQuality() {
        Item item = new Item(
                "foo",
                1,
                0
        );

        assertFalse(GildedRoseUtil.whereItemHasNotSpoiled(item));
    }

    @Test
    public void whereItemCloseToExpiration_shouldReturnTrue_givenItemWith10SellIn() {
        Item item = new Item(
                "foo",
                10,
                1
        );

        assertTrue(GildedRoseUtil.whereItemCloseToExpiration(item));
    }

    @Test
    public void whereItemCloseToExpiration_shouldReturnFalse_givenItemWith11SellIn() {
        Item item = new Item(
                "foo",
                11,
                1
        );

        assertFalse(GildedRoseUtil.whereItemCloseToExpiration(item));
    }

    @Test
    public void whereItemVeryCloseToExpiration_shouldReturnTrue_givenItemWith5SellIn() {
        Item item = new Item(
                "foo",
                5,
                1
        );

        assertTrue(GildedRoseUtil.whereItemVeryCloseToExpiration(item));
    }

    @Test
    public void whereItemVeryCloseToExpiration_shouldReturnFalse_givenItemWith6SellIn() {
        Item item = new Item(
                "foo",
                6,
                1
        );

        assertFalse(GildedRoseUtil.whereItemVeryCloseToExpiration(item));
    }

    @Test
    public void whereItemHasExpired_shouldReturnTrue_givenItemWithNegativeSellIn() {
        Item item = new Item(
                "foo",
                -1,
                1
        );

        assertTrue(GildedRoseUtil.whereItemHasExpired(item));
    }

    @Test
    public void whereItemHasExpired_shouldReturnFalse_givenItemWith0SellIn() {
        Item item = new Item(
                "foo",
                0,
                1
        );

        assertFalse(GildedRoseUtil.whereItemHasExpired(item));
    }
}
