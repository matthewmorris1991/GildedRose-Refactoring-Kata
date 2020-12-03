class Shop {
  constructor(items = []) {
    this.items = items;
    this.MAX_QUALITY = 50;
    this.MIN_QUALITY = 0;
  }
  updateQuality() {
    this.items.forEach(item =>
      this._updateItem(item)
    )
    return this.items;
  }

  _updateItem(item) {
    this._updateItemQuality(item);
    this._checkMaxQuality(item);
    this._checkMinQuality(item);
    this._updateItemSellIn(item);
  }

  _updateItemQuality(item) {
    item.quality += this._getQualityChange(item);
  }

  _getQualityChange(item) {
    if (this._isBackstagePass(item)) {
      return this._getQualityChangeBackstagePass(item);
    } else if (this._isAgedBrie(item)) {
      return this._getQualityChangeAgedBrie(item);
    } else if (this._isSulfuras(item)) {
      return this._getQualityChangeSulfuras(item);
    } else {
      return this._getQualityChangeStandard(item);
    }
  }

  _isBackstagePass(item) {
    return item.name.toLowerCase().match(/backstage pass/);
  }

  _isAgedBrie(item) {
    return item.name.toLowerCase().match(/aged brie/);
  }

  _isSulfuras(item) {
    return item.name.toLowerCase().match(/sulfuras/);
  }

  _getQualityChangeStandard(item) {
    if (item.sellIn <= 0) {
      return -2;
    } else {
      return -1;
    }
  }

  _getQualityChangeBackstagePass(item) {
    if (item.sellIn <= 0) {
      return -item.quality;
    } else if (item.sellIn <= 5) {
      return 3;
    } else if (item.sellIn <= 10) {
      return 2;
    } else {
      return 1;
    }
  }

  _getQualityChangeAgedBrie(item) {
    if (item.sellIn <= 0) {
      return 2;
    } else {
      return 1;
    }
  }

  _getQualityChangeSulfuras(item) {
    return 0;
  }

  _updateItemSellIn(item) {
    if (item.name != 'Sulfuras, Hand of Ragnaros') {
      item.sellIn = item.sellIn - 1;
    }
  }

  _checkMaxQuality(item) {
    if (item.quality > this.MAX_QUALITY) {
      item.quality = this.MAX_QUALITY;
    }
  }

  _checkMinQuality(item) {
    if (item.quality < this.MIN_QUALITY) {
      item.quality = this.MIN_QUALITY;
    }
  }
}
module.exports = {
  Shop
}
