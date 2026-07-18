const assert = require("node:assert/strict");

function maxConsecutiveSum(numbers, length) {
  // TODO: 여기에 풀이를 작성하세요.
  // 2. `numbers`가 배열이 아니거나 빈 배열이면 `null`을 반환한다.
  if (!Array.isArray(numbers) || numbers.length === 0) return null

  // 3. `length`가 정수가 아니거나 `1`보다 작거나 배열 길이보다 크면 `null`을 반환한다.
  if (typeof length !== 'number' || length % 1 !== 0 || length < 1 || length > numbers.length) return null;


  let answer = 0;
  for(let i = 0; i < length; i++){
      answer += numbers[i];
  }

  let sum = answer
  for(let i = 0; i < numbers.length - length; i++){
    sum -= numbers[i]
    sum += numbers[i + length]

    if (answer < sum) {
      answer = sum;
    }
  }


  return answer;
}

assert.strictEqual(
  maxConsecutiveSum([2, 1, 5, 1, 3, 2], 3),
  9
);

assert.strictEqual(
  maxConsecutiveSum([-5, -2, -8, -1], 2),
  -7
);

assert.strictEqual(
  maxConsecutiveSum([4, -1, 2], 3),
  5
);

assert.strictEqual(
  maxConsecutiveSum([3, -4, 8, 1], 1),
  8
);

assert.strictEqual(maxConsecutiveSum([], 1), null);
assert.strictEqual(maxConsecutiveSum(null, 1), null);
assert.strictEqual(maxConsecutiveSum("not-array", 1), null);
assert.strictEqual(maxConsecutiveSum([1, 2, 3], 0), null);
assert.strictEqual(maxConsecutiveSum([1, 2, 3], -1), null);
assert.strictEqual(maxConsecutiveSum([1, 2, 3], 1.5), null);
assert.strictEqual(maxConsecutiveSum([1, 2, 3], 4), null);

const numbers = [2, 1, 5, 1, 3, 2];
const snapshot = [...numbers];

maxConsecutiveSum(numbers, 3);
assert.deepStrictEqual(numbers, snapshot, "원본 배열을 변경하면 안 됩니다.");

console.log("✅ 모든 테스트 통과");
