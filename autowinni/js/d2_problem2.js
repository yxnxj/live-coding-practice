const assert = require("node:assert/strict");

function uniqueInOrder(numbers) {

  const m = new Map();
  let answer = new Array()

  // 4. 입력이 배열이 아니면 빈 배열 `[]`을 반환한다.
  // 5. 입력이 빈 배열이면 빈 배열 `[]`을 반환한다.
  if (numbers == null || !Array.isArray(numbers) || numbers.length <= 0) return answer

  for (let n of numbers){
    if (m.has(n)) continue;

    answer.push(n)
    m.set(n, true)
  }

  return answer
}

const input = [4, 2, 4, 1, 2, 3];
const inputBeforeCall = [...input];
const result = uniqueInOrder(input);

assert.deepStrictEqual(
  result,
  [4, 2, 1, 3]
);

assert.deepStrictEqual(
  input,
  inputBeforeCall,
  "원본 배열을 변경하면 안 됩니다."
);

assert.notStrictEqual(
  result,
  input,
  "반환값은 원본과 다른 새 배열이어야 합니다."
);

assert.deepStrictEqual(
  uniqueInOrder([-1, -1, 0, -1, 2, 0]),
  [-1, 0, 2]
);

assert.deepStrictEqual(
  uniqueInOrder([7, 7, 7]),
  [7]
);

assert.deepStrictEqual(
  uniqueInOrder([5]),
  [5]
);

assert.deepStrictEqual(
  uniqueInOrder([1, 2, 3]),
  [1, 2, 3]
);

assert.deepStrictEqual(
  uniqueInOrder([]),
  []
);

assert.deepStrictEqual(
  uniqueInOrder(null),
  []
);

assert.deepStrictEqual(
  uniqueInOrder("1,2,1"),
  []
);

console.log("✅ 모든 테스트 통과");
