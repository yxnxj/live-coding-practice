const assert = require("node:assert/strict");

function sortVehicles(vehicles) {
  if (vehicles == null || vehicles.length <= 0) return [];

  const answer = [...vehicles]

  answer.sort((a, b) => {
    // 1. `price` 내림차순
    if (a.price !== b.price) return b.price - a.price;

    // 2. 가격이 같으면 `year` 내림차순
    if (a.year !== b.year) return b.year - a.year;

    // 3. 가격과 연식이 모두 같으면 `id` 오름차순
    return a.id.localeCompare(b.id);
  })

  return answer
}

const vehicles = [
  { id: "V-103", price: 15000, year: 2022 },
  { id: "V-101", price: 15000, year: 2023 },
  { id: "V-200", price: 12000, year: 2025 },
  { id: "V-100", price: 15000, year: 2023 },
  { id: "V-050", price: 18000, year: 2020 }
];

const originalOrder = [...vehicles];
const result = sortVehicles(vehicles);

assert.deepStrictEqual(
  result,
  [
    { id: "V-050", price: 18000, year: 2020 },
    { id: "V-100", price: 15000, year: 2023 },
    { id: "V-101", price: 15000, year: 2023 },
    { id: "V-103", price: 15000, year: 2022 },
    { id: "V-200", price: 12000, year: 2025 }
  ]
);

assert.deepStrictEqual(
  vehicles,
  originalOrder,
  "원본 배열의 순서를 변경하면 안 됩니다."
);

assert.notStrictEqual(
  result,
  vehicles,
  "반환 배열은 원본 배열과 다른 객체여야 합니다."
);

const empty = [];
const emptyResult = sortVehicles(empty);

assert.deepStrictEqual(emptyResult, []);
assert.notStrictEqual(emptyResult, empty);
assert.deepStrictEqual(sortVehicles(null), []);

console.log("✅ 모든 테스트 통과");
