const assert = require("node:assert/strict");

function rangeTotals(values, queries) {
  // TODO: 여기에 풀이를 작성하세요.
  return [];
}

const values = [5, -2, 3, 7, 1];
const queries = [
  [0, 2],
  [1, 3],
  [3, 3],
  [0, 4]
];
const valuesSnapshot = [...values];
const queriesSnapshot = queries.map(query => [...query]);

assert.deepStrictEqual(
  rangeTotals(values, queries),
  [6, 8, 7, 14]
);

assert.deepStrictEqual(
  rangeTotals([10, -5, 2], [[0, 0], [1, 1], [2, 2]]),
  [10, -5, 2]
);

assert.deepStrictEqual(
  rangeTotals([1, 2, 3, 4], [[1, 2], [1, 2]]),
  [5, 5]
);

assert.deepStrictEqual(rangeTotals([1, 2, 3], []), []);
assert.deepStrictEqual(rangeTotals([], []), []);

rangeTotals(values, queries);
assert.deepStrictEqual(values, valuesSnapshot, "values를 변경하면 안 됩니다.");
assert.deepStrictEqual(queries, queriesSnapshot, "queries를 변경하면 안 됩니다.");

console.log("✅ 모든 테스트 통과");
