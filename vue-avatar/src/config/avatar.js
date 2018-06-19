import Vue from 'vue'

export default {
  firstUpperCase(value) {
    return value.replace(/^\w/, function (s) {
      return s.toUpperCase();
    });
  },
  moveItem(items, index, isUp) {
    if ((index == 0 && isUp) || (index == items.length - 1 && !isUp)) {
      return []
    }
    var curs = items.splice(index, 1);
    var item = curs[0];
    var origin;
    if (isUp) {
      origin = items[index - 1];
      items.splice(index - 1, 0, ...curs)
    } else {
      origin = items[index];
      items.splice(index + 1, 0, ...curs)
    }
    return [item, origin]
  },
  moveSwap(changeItems, swapItems, propKey, propSeq) {
    var propNewSeq = 'new' + this.firstUpperCase(propSeq);
    var itemSeqNum = function (changeItems, swapItem) {
      for (let item of changeItems) {
        if (item[propKey] == swapItem[propKey]) {
          return item[propNewSeq]
        }
      }
      return swapItem[propSeq]
    };
    var item1 = swapItems[0];
    let item2 = swapItems[1];
    var seq1 = itemSeqNum(changeItems, item1);
    var seq2 = itemSeqNum(changeItems, item2);
    let found1 = false, found2 = false;
    for (let item of changeItems) {
      if (item1[propKey] == item[propKey]) {
        Vue.set(item, propNewSeq, seq2);
        found1 = true
      }
      if (item2[propKey] == item[propKey]) {
        Vue.set(item, propNewSeq, seq1);
        found2 = true
      }
    }

    if (!found1) {
      changeItems.push({...item1, [propNewSeq]: seq2});
    }
    if (!found2) {
      changeItems.push({...item2, [propNewSeq]: seq1});
    }
    return changeItems;
  }
}
