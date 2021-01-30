defmodule GildedRoseTest do
  use ExUnit.Case

  import GildedRose

  @brie     "Aged Brie"
  @conjured "Conjured"
  @normal   "Normal Item"
  @sulfuras "Sulfuras, Hand of Ragnaros"
  @passes   "Backstage passes to a TAFKAL80ETC concert"

  def create_items(sell_in, quality) do
    [
      %Item{name: @sulfuras, sell_in: sell_in, quality: 80},
      %Item{name: @passes, sell_in: sell_in, quality: quality},
      %Item{name: @brie, sell_in: sell_in, quality: quality},
      %Item{name: @normal, sell_in: sell_in, quality: quality}
    ]
  end

  def elapse_days(items, 0), do: items
  
  def elapse_days(items, days) do
    elapse_days(update_quality(items), days - 1)
  end

  test "all items quality _ 11 days or more left" do
    days_left = 12
    initial_quality = 1
    result = create_items(days_left, initial_quality) |> elapse_days(1)

    assert result === [
      %Item{name: @sulfuras, sell_in: 12, quality: 80},
      %Item{name: @passes, sell_in: 11, quality: 2},
      %Item{name: @brie, sell_in: 11, quality: 2},
      %Item{name: @normal, sell_in: 11, quality: 0},
    ]
  end

  test "normal item quality _ any day greater than 0" do
    days_left = 10
    initial_quality = 10
    [_, _, _, normal] = create_items(days_left, initial_quality) |> elapse_days(1)

    assert normal === %Item{name: @normal, sell_in: 9, quality: 9}
  end

  test "normal item quality _ last day" do
    days_left = 1
    initial_quality = 5
    [_, _, _, normal] = create_items(days_left, initial_quality) |> elapse_days(1)

    assert normal === %Item{name: @normal, sell_in: 0, quality: 4}
  end
  

  test "normal item quality _ no days left" do
    days_left = 0
    initial_quality = 4
    [_, _, _, normal] = create_items(days_left, initial_quality) |> elapse_days(1)

    assert normal === %Item{name: @normal, sell_in: -1, quality: 2}
  end

  test "normal item quality _ no days left _ no more quality" do
    days_left = 0
    initial_quality = 4
    [_, _, _, normal] = create_items(days_left, initial_quality) |> elapse_days(4)

    assert normal === %Item{name: @normal, sell_in: -4, quality: 0}
  end

  test "ticket quality _ 10 days or less" do
    days_left = 10
    initial_quality = 0
    [_, ticket, _, _] = create_items(days_left, initial_quality) |> elapse_days(1)

    assert ticket === %Item{name: @passes, sell_in: 9, quality: 2}
  end

  test "ticket quality _ 5 days or less" do
    days_left = 5
    initial_quality = 0
    [_, ticket, _, _] = create_items(days_left, initial_quality) |> elapse_days(1)

    assert ticket === %Item{name: @passes, sell_in: 4, quality: 3}
  end

  test "ticket quality _ after concert" do
    days_left = 0
    initial_quality = 0
    [_, ticket, _, _] = create_items(days_left, initial_quality) |> elapse_days(1)

    assert ticket === %Item{name: @passes, sell_in: -1, quality: 0}
  end

  test "brie quality _ greater than 0 days left " do
    days_left = 1
    initial_quality = 0
    [_, _, brie, _]  = create_items(days_left, initial_quality) |> elapse_days(1)

    assert brie === %Item{name: @brie, sell_in: 0, quality: 1}
  end


  test "brie quality _ past sell in day" do
    days_left = 0
    initial_quality = 0
    [_, _, brie, _]  = create_items(days_left, initial_quality) |> elapse_days(1)

    assert brie === %Item{name: @brie, sell_in: -1, quality: 2}
  end

  test "sulfuras quality _ any days left" do
    days_left = 20
    initial_quality = 40
    [sulfuras, _, _, _]  = create_items(days_left, initial_quality) |> elapse_days(40)

    assert sulfuras === %Item{name: @sulfuras, sell_in: 20, quality: 80}
  end

  test "sulfuras quality _ lengendary quality" do
    days_left = 20
    initial_quality = 100
    [sulfuras, _, _, _]  = create_items(days_left, initial_quality) |> elapse_days(1)

    assert sulfuras === %Item{name: @sulfuras, sell_in: 20, quality: 80}
  end

  test "all items quality _ reaching max quality" do
    days_left = 5
    initial_quality = 49
    result = create_items(days_left, initial_quality) |> elapse_days(5)

    assert result === [
      %Item{name: @sulfuras, sell_in: 5, quality: 80},
      %Item{name: @passes, sell_in: 0, quality: 50},
      %Item{name: @brie, sell_in: 0, quality: 50},
      %Item{name: @normal, sell_in: 0, quality: 44}
    ]
  end


end
