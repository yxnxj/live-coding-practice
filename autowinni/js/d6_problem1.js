const assert = require("node:assert/strict");

function longestUniqueLength(text) {
  const charArray = new Array(...text);

  let map = new Map();
  let results = new Array();

  let answer = 0;

  for(let c of charArray){
    if(map.has(c)){
      answer = answer < results.length ? results.length : answer;
      const newResults = new Array();
      const newMap = new Map();
      for(let existC of results.reverse()){
        if (existC === c)break;
        newResults.push(existC);
        newMap.set(existC, true);
      }
      results = new Array(...newResults.reverse());
      map = new Map(newMap);

      results.push(c);
      map.set(c, true);
    }else {
      results.push(c);
      map.set(c, true);
    }
  }
  return answer < results.length ? results.length : answer;
}

assert.strictEqual(longestUniqueLength("abcabcbb"), 3);
assert.strictEqual(longestUniqueLength("abcaefbg"), 6);
assert.strictEqual(longestUniqueLength(""), 0);
assert.strictEqual(longestUniqueLength("a"), 1);
assert.strictEqual(longestUniqueLength("aaaaa"), 1);
assert.strictEqual(longestUniqueLength("abcdef"), 6);
assert.strictEqual(longestUniqueLength("pwwkew"), 3);
assert.strictEqual(longestUniqueLength("abba"), 2);
assert.strictEqual(longestUniqueLength("dvdf"), 3);

console.log("✅ 모든 테스트 통과");
