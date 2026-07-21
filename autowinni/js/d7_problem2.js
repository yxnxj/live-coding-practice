const assert = require("node:assert/strict");

function bestTrade(prices) {

  let buy = prices[0]

  let buyIndex  = 0
  let sellIndex = 1
  let profit = -1

  let tempIdx = 0
  for(let i = 1; i< prices.length; i++){
    let next = prices[i]

    if (buy === next) continue;
    else if (buy < next) {
      if (profit < next - buy){
        sellIndex = i
        buyIndex = tempIdx
        profit = next - buy
      }
    }else{ // next > buy
      buy = next
      tempIdx = i
    }
    
  }

  if (profit === -1) return null
  return {buyIndex: buyIndex, sellIndex: sellIndex, profit: profit};
}

assert.deepStrictEqual(
  bestTrade([8, 3, 6, 1, 7, 4]),
  { buyIndex: 3, sellIndex: 4, profit: 6 }
);

assert.deepStrictEqual(
  bestTrade([5, 1, 4, 1, 4]),
  { buyIndex: 1, sellIndex: 2, profit: 3 }
);

assert.deepStrictEqual(
  bestTrade([2, 2, 5]),
  { buyIndex: 0, sellIndex: 2, profit: 3 }
);

assert.deepStrictEqual(
  bestTrade([1, 2, 3, 4]),
  { buyIndex: 0, sellIndex: 3, profit: 3 }
);

assert.deepStrictEqual(
  bestTrade([3, 8]),
  { buyIndex: 0, sellIndex: 1, profit: 5 }
);

assert.deepStrictEqual(
  bestTrade([1, 5, 0, 3]),
  { buyIndex: 0, sellIndex: 1, profit: 4 }
);

assert.strictEqual(bestTrade([9, 7, 5, 3]), null);
assert.strictEqual(bestTrade([4, 4, 4]), null);
assert.strictEqual(bestTrade([1]), null);
assert.strictEqual(bestTrade([]), null);

console.log("✅ 모든 테스트 통과");
