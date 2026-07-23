const assert = require("node:assert/strict");

function distancesToHigherPrice(prices) {
  const input = [...prices];

  const results = [];
  for(let i = 0; i < input.length; i++){
    let offset = input[i]
    results.push(0);
    for (let j = i + 1; j < input.length; j++){
      if (offset < input[j]) {
        results[i] = j - i
        break;
      }
    }

  }

  return results;
}

const prices = [4, 3, 5, 5, 2, 6];
const originalPrices = [...prices];

assert.deepStrictEqual(
  distancesToHigherPrice(prices),
  [2, 1, 3, 2, 1, 0]
);

assert.deepStrictEqual(
  distancesToHigherPrice([1, 2, 3, 4]),
  [1, 1, 1, 0]
);

assert.deepStrictEqual(
  distancesToHigherPrice([4, 3, 2, 1]),
  [0, 0, 0, 0]
);

assert.deepStrictEqual(
  distancesToHigherPrice([3, 3, 3]),
  [0, 0, 0]
);

assert.deepStrictEqual(
  distancesToHigherPrice([2, 2, 3]),
  [2, 1, 0]
);

assert.deepStrictEqual(
  distancesToHigherPrice([5, 4, 3, 6]),
  [3, 2, 1, 0]
);

assert.deepStrictEqual(distancesToHigherPrice([7]), [0]);
assert.deepStrictEqual(distancesToHigherPrice([]), []);
assert.deepStrictEqual(prices, originalPrices);

console.log("✅ 모든 테스트 통과");

