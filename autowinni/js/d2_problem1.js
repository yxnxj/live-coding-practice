const assert = require("node:assert/strict");

function mostFrequentChar(text) {
  if (text == null || typeof text != 'string' || text.trim().length <= 0) return ""

  let input = text.trim().toLowerCase()

  const charArray = [...input];
  const m = new Map();
  for(let c of charArray){
    if (c == " ") continue;

    if (m.has(c)){
      let cnt = m.get(c);
      m.set(c, cnt + 1);
      continue;
    }

    m.set(c, 1)
  }


  let answer
  let max_cnt = 0
  for(let k of m.keys()){
    const v = m.get(k);
    if (max_cnt < v) {
      answer = k;
      max_cnt = v;
    }else if(max_cnt == v && answer.localeCompare(k) > 0){
      answer = k;
    }
  }

  return answer
}

assert.strictEqual(
  mostFrequentChar("Banana Apple"),
  "a"
);

assert.strictEqual(
  mostFrequentChar("bbaacc"),
  "a"
);

assert.strictEqual(
  mostFrequentChar("zz YY"),
  "y"
);

assert.strictEqual(
  mostFrequentChar("A a b"),
  "a"
);

assert.strictEqual(
  mostFrequentChar("x"),
  "x"
);

assert.strictEqual(
  mostFrequentChar(""),
  ""
);

assert.strictEqual(
  mostFrequentChar("   "),
  ""
);

assert.strictEqual(
  mostFrequentChar(null),
  ""
);

assert.strictEqual(
  mostFrequentChar(["a", "a"]),
  ""
);

console.log("✅ 모든 테스트 통과");
