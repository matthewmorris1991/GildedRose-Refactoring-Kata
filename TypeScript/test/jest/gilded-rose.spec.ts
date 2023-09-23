import { Item, GildedRose } from "@/gilded-rose";

describe("Aged Brie 테스트", () => {
  let gildedRose = new GildedRose([new Item("Aged Brie", 1, 0)]);

  beforeEach(() => {
    gildedRose = new GildedRose([new Item("Aged Brie", 2, 3)]);
  });

  it("Aged Brie의 quality는 1 증가, sellIn은 1 감소", () => {
    const items = gildedRose.updateQuality();

    expect(items[0].sellIn).toBe(1);
    expect(items[0].quality).toBe(4);
  });

  it("quality는 50을 초과할 수 없음", () => {
    const gildedRose = new GildedRose([new Item("Aged Brie", 2, 50)]);
    const items = gildedRose.updateQuality();

    expect(items[0].sellIn).toBe(1);
    expect(items[0].quality).toBe(50);
  });
});

describe("Surfras 테스트", () => {
  let gildedRose = new GildedRose([
    new Item("Sulfuras, Hand of Ragnaros", 1, 0),
  ]);

  beforeEach(() => {
    gildedRose = new GildedRose([new Item("Sulfuras, Hand of Ragnaros", 2, 3)]);
  });

  it("Surfras의 quality는 변하지 않음, sellIn 변하지 않음", () => {
    const items = gildedRose.updateQuality();

    expect(items[0].sellIn).toBe(2);
    expect(items[0].quality).toBe(3);
  });
});

describe("Backstage passes 테스트", () => {
  let gildedRose = new GildedRose([
    new Item("Backstage passes to a TAFKAL80ETC concert", 1, 0),
  ]);

  beforeEach(() => {
    gildedRose = new GildedRose([
      new Item("Backstage passes to a TAFKAL80ETC concert", 2, 3),
    ]);
  });

  it("Backstage passes의 sellIn값 1감소, quality 1증가", () => {
    const items = gildedRose.updateQuality();

    expect(items[0].sellIn).toBe(1);
    expect(items[0].quality).toBe(6);
  });

  it("Backstage passes의 sellIn값 10일부터, quality 2증가", () => {
    const gildedRose = new GildedRose([
      new Item("Backstage passes to a TAFKAL80ETC concert", 10, 3),
    ]);
    const items = gildedRose.updateQuality();

    expect(items[0].sellIn).toBe(9);
    expect(items[0].quality).toBe(5);
  });

  it("Backstage passes의 sellIn값 5일부터, quality 3증가", () => {
    const gildedRose = new GildedRose([
      new Item("Backstage passes to a TAFKAL80ETC concert", 5, 3),
    ]);
    const items = gildedRose.updateQuality();

    expect(items[0].sellIn).toBe(4);
    expect(items[0].quality).toBe(6);
  });
});
