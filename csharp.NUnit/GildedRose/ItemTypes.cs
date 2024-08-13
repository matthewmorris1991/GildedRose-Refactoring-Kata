using System;
namespace GildedRoseKata;

public abstract class ItemTypeProperties
{
    public abstract int MaxQuality { get; }
    public abstract int ItemTypeIncrement(Item item);
    protected bool IsItemType(Item item, string itemName)
    {
        return item.Name.Equals(itemName, StringComparison.OrdinalIgnoreCase);
    }
}

public static class ItemTypes
{
    //This allows for new item types to be easily added in the future
    public static ItemTypeProperties CheckItemType(string itemName)
    {
        if (itemName.Contains("Sulfuras", StringComparison.OrdinalIgnoreCase))
            return new SulfurasItemType();
        if (itemName.Contains("Aged Brie", StringComparison.OrdinalIgnoreCase))
            return new AgedBrieItemType();
        if (itemName.Contains("Backstage Pass", StringComparison.OrdinalIgnoreCase))
            return new BackstagePassItemType();
        if (itemName.Contains("Conjured", StringComparison.OrdinalIgnoreCase))
            return new ConjuredItemType();
        return new StandardItemType();
    }
}

public class SulfurasItemType : ItemTypeProperties
{
    public override int MaxQuality => 80;
    //Sulfuras does not change in Quality
    public override int ItemTypeIncrement(Item item) => 0;

}

public class AgedBrieItemType : ItemTypeProperties
{
    public override int MaxQuality => 50;
    //Aged Brie increases in Quality with Age
    public override int ItemTypeIncrement(Item item) => 1;

}

public class BackstagePassItemType : ItemTypeProperties
{
    public override int MaxQuality => 50;
    public override int ItemTypeIncrement(Item item)
    {
        //Once the Sellin Date is passed set the quality to 0
        if (item.SellIn < 0) return -50; 

        //Standard Increment
        int increment = 1;
        //As the Sellin Date comes closer the Quality Increases more
        if (item.SellIn < 11) increment += 1;
        if (item.SellIn < 6) increment += 1;

        return increment;
    }
}

public class ConjuredItemType : ItemTypeProperties
{
    public override int MaxQuality => 50;
    //Conjured Items diminish twice as fast
    public override int ItemTypeIncrement(Item item) => -2;

}

public class StandardItemType : ItemTypeProperties
{
    public override int MaxQuality => 50;
    //Standard Item quality diminish
    public override int ItemTypeIncrement(Item item) => -1;

}