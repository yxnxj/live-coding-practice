const assert = require("node:assert/strict");
const { send } = require("node:process");

function commonSortedNumbers(first, second) {
  // 2. 둘 중 하나라도 배열이 아니면 빈 배열 `[]`을 반환한다.
  if(!Array.isArray(first) || !Array.isArray(second)) return [];

  // 3. 둘 중 하나라도 빈 배열이면 빈 배열 `[]`을 반환한다.
  if(first.length <= 0 || second.length <= 0) return []

  let f_idx = 0;
  let s_idx = 0;

  const results = new Array();

  while(f_idx < first.length && s_idx < second.length){
    const f = first.at(f_idx)
    const s = second.at(s_idx)

    if (f > s) {
      s_idx += 1;
    }else if (f < s) {
      f_idx += 1;
    }else {
      s_idx += 1;
      f_idx += 1;

      results.push(f);
    }
  }

  return results;
}

const first = [1, 2, 2, 4, 7];
const second = [2, 2, 3, 7, 9];
const firstSnapshot = [...first];
const secondSnapshot = [...second];

assert.deepStrictEqual(
  commonSortedNumbers(first, second),
  [2, 2, 7]
);

assert.deepStrictEqual(
  commonSortedNumbers(
    [-3, -1, 0, 4],
    [-2, -1, 0, 0, 5]
  ),
  [-1, 0]
);

assert.deepStrictEqual(
  commonSortedNumbers([2, 2, 2], [2, 2]),
  [2, 2]
);

assert.deepStrictEqual(
  commonSortedNumbers([1, 3, 5], [2, 4, 6]),
  []
);

assert.deepStrictEqual(
  commonSortedNumbers([], [1, 2, 3]),
  []
);

assert.deepStrictEqual(commonSortedNumbers(null, [1]), []);
assert.deepStrictEqual(commonSortedNumbers([1], "not-array"), []);

commonSortedNumbers(first, second);
assert.deepStrictEqual(first, firstSnapshot, "first를 변경하면 안 됩니다.");
assert.deepStrictEqual(second, secondSnapshot, "second를 변경하면 안 됩니다.");

console.log("✅ 모든 테스트 통과");
