using System;
using System.Collections.Generic;

namespace GildedRoseKata;

public class GildedRose
{
    IList<Item> Items;

    public GildedRose(IList<Item> Items)
    {
        this.Items = Items;
    }

    public void UpdateQuality()
    {
        foreach (var item in Items)
        {
            //Compare item name with list of pre-existing products
            var itemType = ItemTypes.CheckItemType(item.Name);
            //Dont allow Quality to go below 0 or above the itemType Max
            item.Quality = Math.Clamp(item.Quality + itemType.ItemTypeIncrement(item), 0, itemType.MaxQuality);

            //This could be simplified further but was sure on the requirement for SellIn Date reducing for Sulfuras
            if (!itemType.GetType().Equals(typeof(SulfurasItemType)))
            {
                item.SellIn--;
                //If SellIn < 0 check for further Quality reductions
                if (item.SellIn < 0)
                {
                    //Dont allow Quality to go below 0 or above the itemType Max
                    item.Quality = Math.Clamp(item.Quality + itemType.ItemTypeIncrement(item), 0, itemType.MaxQuality);
                }
            }
        }
    }
}