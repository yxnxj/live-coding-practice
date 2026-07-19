const assert = require("node:assert/strict");

function findInsertIndices(numbers, targets) {
  const answer = new Array();

  for(let target of targets){
    let start_idx = 0
    let end_idx = numbers.length

    while(start_idx < end_idx) {
      let mid_idx = Math.floor((end_idx + start_idx)/2)
      let mid = numbers[mid_idx]
      if (target <= mid){
        end_idx = mid_idx
      }else {
        start_idx = mid_idx+1
      }
    }
    answer.push(start_idx)
  }

  return answer;
}

const numbers = [1, 3, 3, 5, 8];
const targets = [3, 4, 0, 10];
const numbersSnapshot = [...numbers];
const targetsSnapshot = [...targets];

assert.deepStrictEqual(
  findInsertIndices(numbers, targets),
  [1, 3, 0, 5]
);

assert.deepStrictEqual(
  findInsertIndices([-5, -2, 0, 0, 7], [-3, 0, 8, -5]),
  [1, 2, 5, 0]
);

assert.deepStrictEqual(
  findInsertIndices([1, 1, 1], [1, 2, 1]),
  [0, 3, 0]
);

assert.deepStrictEqual(findInsertIndices([], [5, -1]), [0, 0]);
assert.deepStrictEqual(findInsertIndices([1, 2, 3], []), []);

findInsertIndices(numbers, targets);
assert.deepStrictEqual(numbers, numbersSnapshot, "numbers를 변경하면 안 됩니다.");
assert.deepStrictEqual(targets, targetsSnapshot, "targets를 변경하면 안 됩니다.");

console.log("✅ 모든 테스트 통과");
