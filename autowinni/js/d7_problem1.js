const assert = require("node:assert/strict");

function minimumMoves(warehouse) {
  const dx = [-1, 0, 1, 0]
  const dy = [0, 1, 0, -1]

  const visited = new Array()
  let q
  for (let i = 0; i < warehouse.length; i++){
    const v = new Array();
    for(let j = 0; j < warehouse[0].length; j++) {
      if (warehouse[i][j] == "S"){
        q = new Array([i, j, 0])
        v.push(true)
      }

      v.push(false)


    }
    visited.push(v)
  }
  
  let answer = -1;

  for(let i = 0; i < q.length; i++){
    let point = q[i]
    let x = point.at(0)
    let y = point.at(1)
    let cnt = point.at(2) + 1

    for(let i = 0; i < dx.length; i++){
      let nx = x + dx[i];
      let ny = y + dy[i];

      if (nx < 0 || nx > warehouse.length - 1 || ny < 0 || ny > warehouse[0].length - 1) continue;

      if (visited[nx][ny]) continue;

      if (warehouse[nx][ny] == "#") continue;

      if (warehouse[nx][ny] == "E") {
        answer = answer > cnt|| answer == -1 ? cnt: answer 
        continue;
      }
      
      q.push([nx, ny, cnt])
      visited[nx][ny] = true;
    }
  }

  return answer;
}

assert.strictEqual(
  minimumMoves([
    "S..#",
    ".#..",
    "...E"
  ]),
  5
);

assert.strictEqual(
  minimumMoves([
    "S..",
    "##.",
    "..E"
  ]),
  4
);

assert.strictEqual(minimumMoves(["SE"]), 1);

assert.strictEqual(
  minimumMoves([
    "S#.",
    "###",
    "..E"
  ]),
  -1
);

assert.strictEqual(
  minimumMoves([
    "S.",
    ".E"
  ]),
  2
);

assert.strictEqual(
  minimumMoves([
    "S...E",
    ".###.",
    "....."
  ]),
  4
);

assert.strictEqual(
  minimumMoves([
    "S",
    ".",
    ".",
    "E"
  ]),
  3
);

assert.strictEqual(
  minimumMoves([
    "..E",
    ".#.",
    "S.."
  ]),
  4
);

console.log("✅ 모든 테스트 통과");
