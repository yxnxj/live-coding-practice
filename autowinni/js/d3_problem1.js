const assert = require("node:assert/strict");

function isValidBrackets(text) {
  if (text == null || typeof text != 'string' ) return false;
  if(text.trim().length <= 0) return true

  const brakets = new Map([
    [")", "("],
    ["}", "{"],
    ["]", "["]
  ]);

  const input = new Array(...text)
  const stack = [];

  for(let c of input){
    if (!brakets.has(c)) {
      stack.push(c); // open braket이면 stack push
      continue;
    }

    // c가 close braket이면 stack 마지막이 대응되는 open bracket이어야 한다.
    let popBraket = stack.pop();
    if (popBraket === brakets.get(c)) continue;
    else return false
  }

  return stack.length == 0
}

assert.strictEqual(isValidBrackets("()[]{}"), true);
assert.strictEqual(isValidBrackets("([{}])"), true);
assert.strictEqual(isValidBrackets("([)]"), false);
assert.strictEqual(isValidBrackets("(("), false);
assert.strictEqual(isValidBrackets("]"), false);
assert.strictEqual(isValidBrackets(""), true);
assert.strictEqual(isValidBrackets(null), false);
assert.strictEqual(isValidBrackets(["(", ")"]), false);

console.log("✅ 모든 테스트 통과");
