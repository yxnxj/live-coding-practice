const assert = require("node:assert/strict");

function mergeIntervals(intervals) {
  // 구현
  const input = new Array(...intervals);
  input.sort((a, b) => {
    return a[0]-b[0];
  })

  const results = new Array();

  for(let [i, interval] of input.entries()){

    if (i === 0 ) results.push(interval)
  
    let last = results.at(results.length - 1);

    let compareStart = last[0];
    let compareEnd = last[1];

    let start = interval[0];
    let end = interval[1];

    if (compareEnd >= start) {
      start = compareStart;
      end = compareEnd < end ? end : compareEnd;

      results.pop();
    }


    results.push([start, end])
  }

  return results;
}

const intervals = [
  [8, 10],
  [1, 3],
  [2, 6],
  [15, 18],
  [10, 12]
];
const originalIntervals = JSON.stringify(intervals);

assert.deepStrictEqual(mergeIntervals(intervals), [
  [1, 6],
  [8, 12],
  [15, 18]
]);

assert.deepStrictEqual(
  mergeIntervals([
    [1, 10],
    [2, 3],
    [4, 8]
  ]),
  [[1, 10]]
);

assert.deepStrictEqual(
  mergeIntervals([
    [1, 4],
    [4, 5]
  ]),
  [[1, 5]]
);

assert.deepStrictEqual(
  mergeIntervals([
    [1, 2],
    [3, 4]
  ]),
  [
    [1, 2],
    [3, 4]
  ]
);

assert.deepStrictEqual(
  mergeIntervals([
    [3, 6],
    [1, 2],
    [2, 4]
  ]),
  [[1, 6]]
);

assert.deepStrictEqual(
  mergeIntervals([
    [-5, -1],
    [-3, 2],
    [4, 4]
  ]),
  [
    [-5, 2],
    [4, 4]
  ]
);

assert.deepStrictEqual(
  mergeIntervals([
    [2, 5],
    [2, 3],
    [7, 9]
  ]),
  [
    [2, 5],
    [7, 9]
  ]
);

assert.deepStrictEqual(mergeIntervals([[3, 3]]), [[3, 3]]);
assert.deepStrictEqual(mergeIntervals([]), []);
assert.strictEqual(JSON.stringify(intervals), originalIntervals);

console.log("✅ 모든 테스트 통과");
