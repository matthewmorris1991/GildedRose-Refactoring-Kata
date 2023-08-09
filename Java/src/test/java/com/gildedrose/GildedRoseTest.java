package com.gildedrose;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GildedRoseTest {

    private Item[] items;
    private GildedRose app;

    @BeforeEach
    public void init() {
        items = new Item[]{
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new Item("Conjured Mana Cake", 3, 6)
        };
        app = new GildedRose(items);
    }

    @Nested
    class NormalTests{
        // At the end of each day our system lowers both values (Quality and SellIn) for every item
        @Test
        public void testNormalItemQualityAndSellInDecrease() {
            app.updateQuality();
            assertEquals(19, items[0].quality, "After first day: Normal Quality should decrease by 1");
            assertEquals(9, items[0].sellIn, "After first day: Normal SellIn should decrease by 1");

            app.updateQuality();
            assertEquals(18, items[0].quality, "After second day: Normal Quality should decrease by 2");
            assertEquals(8, items[0].sellIn, "After second day: Normal SellIn should decrease by 2");
        }

        // The Quality of an item is never negative, quality of a normal item is decreasing each day
        @Test
        public void testNormalItemQualityCannotGoNegative() {
            for(int i=0; i<10; i++) {
                app.updateQuality();
            }
            assertTrue(items[2].quality > -1, "Normal Quality should never be negative");
        }

        // Once the sell by date has passed, Quality degrades twice as fast
        @Test
        public void testQualityDegradesTwiceAfterSellPassed() {
            for(int i=0; i<11; i++) {
                app.updateQuality();
            }
            assertEquals(8, items[0].quality, "Normal Quality should degrade twice after sell passed");

            app.updateQuality();
            assertEquals(6, items[0].quality, "Normal Quality should degrade twice after sell passed");
        }
    }


    @Nested
    class AgedBrieTests {
        // The Quality of an item is never more than 50, "Aged Brie" actually increases in Quality the older it gets
        @Test
        public void testAgedBrieItemQualityNeverMoreThan50() {
            for(int i=0; i<100; i++) {
                app.updateQuality();
            }
            assertTrue(items[1].quality <= 50, "Aged Brie Quality should never be more than 50");
        }

        // "Aged Brie" actually increases in Quality the older it gets
        @Test
        public void testAgedBrieItemQualityIncreases() {
            app.updateQuality();
            assertEquals(1, items[1].quality, "Aged Brie Quality should increase");

            app.updateQuality();
            assertEquals(2, items[1].quality, "Aged Brie Quality should increase");
        }
    }

    @Nested
    class SulfurasTests {
        // "Sulfuras" never has to be sold or decreases in Quality
        @Test
        @Disabled
        public void testSulfurasItemQualityUnchanged() {
        }
    }

    @Nested
    class BackstagePassesTests {
        // The Quality of an item is never more than 50, "Backstage passes" increases in Quality
        @Test
        @Disabled
        public void testBackstagePassesItemQualityNeverMoreThan50() {
        }

        // "Backstage passes" increases in Quality as its SellIn value approaches
        @Test
        @Disabled
        public void testBackstagePassesItemQualityIncreases() {
        }

        // "Backstage passes" Quality increases by 2 when there are 10 days or less
        @Test
        @Disabled
        public void testBackstagePassesItemQualityIncreasesWhenSellInLessThan11() {
        }

        // "Backstage passes" Quality increases by 3 when there are 5 days or less
        @Test
        @Disabled
        public void testBackstagePassesItemQualityIncreasesWhenSellInLessThan6() {
        }

        // "Backstage passes" Quality drops to 0 after the concert
        @Test
        @Disabled
        public void testBackstagePassesItemQualityDropsToZeroAfterConcert() {
        }
    }

    @Nested
    class ConjuredTests{
        // The Quality of an item is never negative, "Conjured" items degrade in Quality
        @Test
        @Disabled
        public void testConjuredItemQualityCannotGoNegative() {
        }

        // "Conjured" items degrade in Quality twice as fast as normal items
        @Test
        @Disabled
        public void testConjuredItemQualityDecreasesTwiceAsFast() {
        }
    }

}
