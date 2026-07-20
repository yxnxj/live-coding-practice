const assert = require("node:assert/strict");

function rotateClockwise(matrix) {
  const n = matrix.length;

  const answer = new Array(n);
  for (let i = 0; i < n; i++) {
    answer[i] = new Array(n);
  }

  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      answer[j][n-i-1] = matrix[i][j]
    }
  }
  return answer;
}

const matrix = [
  [1, 2, 3],
  [4, 5, 6],
  [7, 8, 9]
];
const snapshot = matrix.map(row => [...row]);

const result = rotateClockwise(matrix);

assert.deepStrictEqual(
  result,
  [
    [7, 4, 1],
    [8, 5, 2],
    [9, 6, 3]
  ]
);

assert.deepStrictEqual(
  rotateClockwise([
    [1, 2],
    [3, 4]
  ]),
  [
    [3, 1],
    [4, 2]
  ]
);

console.log("✅ 모든 테스트 통과");
