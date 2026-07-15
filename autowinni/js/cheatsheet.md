# JavaScript 코딩 테스트 직전 복습

오토위니 코딩 테스트 전에 읽을 문서다. 시험 중에는 인터넷과 공식 문서를 참고할 수 없으므로 아래 문법을 검색 없이 작성할 수 있도록 연습한다.

## 1. VS Code 디버깅 설정

### 가장 빠른 방법: JavaScript Debug Terminal

1. `Command + Shift + P`
2. `Debug: Create JavaScript Debug Terminal` 실행
3. 생성된 터미널에서 다음 명령 실행

```bash
node js/problem1.js
```

코드 왼쪽을 클릭해 중단점을 설정하거나 코드에 `debugger;`를 작성한다.

```javascript
for (let i = 0; i < prices.length; i++) {
  const complement = target - prices[i];

  debugger;
}
```

JavaScript Debug Terminal은 `launch.json`을 만들지 않고 해당 터미널에서 실행한 Node.js 프로세스에 VS Code 디버거를 자동으로 연결한다.

### 최소 launch.json

JavaScript Debug Terminal을 사용할 수 없을 때 `.vscode/launch.json`에 작성한다.

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "node",
      "request": "launch",
      "name": "현재 파일",
      "program": "${file}",
      "console": "integratedTerminal"
    }
  ]
}
```

현재 JavaScript 파일을 열고 `F5`를 누른다. Mac에서 F5가 기능키로 동작하면 `Fn + F5` 또는 `Run → Start Debugging`을 사용한다.

## 2. 배열 타입인지 확인

```javascript
Array.isArray(value)
```

사용 예:

```javascript
if (!Array.isArray(vehicles) || vehicles.length === 0) {
  return [];
}
```

다음 코드는 배열 검사에 사용하지 않는다.

```javascript
typeof [] === "object"; // true
```

배열도 `typeof` 결과가 `"object"`이므로 `Array.isArray()`를 사용해야 한다.

## 3. Map 객체 생성

```javascript
const vehicleMap = new Map();
```

초깃값과 함께 생성:

```javascript
const vehicleMap = new Map([
  ["Hyundai", 2],
  ["Kia", 3]
]);
```

기본 사용법:

```javascript
vehicleMap.set("Hyundai", 2);

const count = vehicleMap.get("Hyundai");
const exists = vehicleMap.has("Hyundai");

vehicleMap.delete("Hyundai");
```

Map 순회:

```javascript
for (const [key, value] of vehicleMap) {
  console.log(key, value);
}
```

Map 값을 배열로 변환:

```javascript
const values = [...vehicleMap.values()];
const entries = [...vehicleMap.entries()];
```

## 4. 빈 문자열인지 확인

공백만 있는 문자열도 빈 문자열로 처리:

```javascript
if (value.trim().length === 0) {
  // 빈 문자열
}
```

타입 검사와 함께 사용:

```javascript
if (typeof value !== "string" || value.trim().length === 0) {
  // 올바르지 않은 문자열
}
```

간단히 다음처럼 작성할 수도 있다.

```javascript
if (typeof value !== "string" || !value.trim()) {
  // 올바르지 않은 문자열
}
```

`trim()`을 타입 검사보다 먼저 호출하면 `null`, `undefined`, 숫자에서 오류가 발생한다.

```javascript
// 잘못된 순서
if (!value.trim() || typeof value !== "string") {
}
```

## 5. 문자열 타입인지 확인

```javascript
typeof value === "string"
```

문자열이 아닌 경우:

```javascript
typeof value !== "string"
```

사용 예:

```javascript
if (typeof vehicle.maker !== "string") {
  continue;
}
```

주의:

```javascript
typeof null;      // "object"
typeof undefined; // "undefined"
typeof 100;       // "number"
typeof "100";     // "string"
```

## 6. Comparator에서 여러 조건 비교

JavaScript의 `sort()`에 비교 함수를 전달한다.

```javascript
array.sort((a, b) => {
  if (a.minPrice !== b.minPrice) {
    return a.minPrice - b.minPrice;
  }

  return a.maker.localeCompare(b.maker);
});
```

위 정렬 조건:

1. `minPrice` 오름차순
2. 가격이 같으면 `maker` 오름차순

숫자 오름차순과 내림차순:

```javascript
numbers.sort((a, b) => a - b); // 오름차순
numbers.sort((a, b) => b - a); // 내림차순
```

문자열 오름차순과 내림차순:

```javascript
names.sort((a, b) => a.localeCompare(b));
names.sort((a, b) => b.localeCompare(a));
```

여러 숫자 조건:

```javascript
cars.sort((a, b) => {
  if (a.price !== b.price) {
    return b.price - a.price; // 가격 내림차순
  }

  if (a.year !== b.year) {
    return b.year - a.year; // 연식 내림차순
  }

  return a.id.localeCompare(b.id); // ID 오름차순
});
```

`sort()`는 원본 배열을 변경한다. 원본을 보존해야 하면 복사 후 정렬한다.

```javascript
const sorted = [...cars].sort(comparator);
```

## 7. 소수점 반올림

정수로 반올림:

```javascript
Math.round(12.5); // 13
Math.round(12.4); // 12
```

평균 계산:

```javascript
const average = Math.round(totalPrice / count);
```

소수점 둘째 자리까지 반올림:

```javascript
const rounded = Math.round(value * 100) / 100;
```

올림과 내림:

```javascript
Math.ceil(12.1);  // 13
Math.floor(12.9); // 12
```

주의: 반올림한 평균값을 다음 평균 계산에 다시 사용하지 않는다. 합계를 계속 누적한 뒤 마지막에 한 번만 나눈다.

```javascript
summary.totalPrice += vehicle.price;
summary.count += 1;

// 모든 집계가 끝난 후
summary.averagePrice = Math.round(
  summary.totalPrice / summary.count
);
```

## 8. Map의 has, get, set

### 존재 여부 확인

```javascript
priceIndex.has(price)
```

### 값 조회

```javascript
priceIndex.get(price)
```

### 값 저장

```javascript
priceIndex.set(price, index)
```

두 수의 합 예시:

```javascript
function findPair(numbers, target) {
  const indexByNumber = new Map();

  for (let i = 0; i < numbers.length; i++) {
    const complement = target - numbers[i];

    if (indexByNumber.has(complement)) {
      return [indexByNumber.get(complement), i];
    }

    indexByNumber.set(numbers[i], i);
  }

  return [];
}
```

빈도 계산:

```javascript
const counts = new Map();

for (const value of values) {
  counts.set(value, (counts.get(value) ?? 0) + 1);
}
```

`get()` 결과가 없으면 `undefined`다. 기본값이 필요하면 `??`를 사용한다.

```javascript
const count = counts.get(value) ?? 0;
```

## 추가로 함께 외울 문법

### 유한한 숫자인지 확인

```javascript
if (!Number.isFinite(value)) {
  // NaN, Infinity, -Infinity 또는 숫자가 아닌 값
}
```

### Set으로 중복 확인

```javascript
const seen = new Set();

if (seen.has(value)) {
  // 이미 존재
}

seen.add(value);
```

중복 제거:

```javascript
const unique = [...new Set(values)];
```

### 테스트 비교

```javascript
const assert = require("node:assert/strict");

assert.deepStrictEqual(actual, expected);
```

`assert.deepStrictEqual()` 호출 전체가 주석 처리되지 않았는지 확인한다.

## 시험 직전 1분 암기 블록

```javascript
Array.isArray(value)
typeof value === "string"
value.trim().length === 0
Number.isFinite(value)

const map = new Map()
map.has(key)
map.get(key)
map.set(key, value)

const set = new Set()
set.has(value)
set.add(value)

numbers.sort((a, b) => a - b)
textA.localeCompare(textB)

Math.round(value)
Math.ceil(value)
Math.floor(value)
```
