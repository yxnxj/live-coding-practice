const assert = require("node:assert/strict");

// 풀이 함수
function summarizeVehicles(vehicles, minYear) {

    // 6. 입력이 배열이 아니거나 비어 있으면 빈 배열을 반환한다.
    if (!Array.isArray(vehicles) || vehicles.length <= 0) return []

    // 7. 원본 배열과 원본 객체를 변경하지 않는다.
    const results = new Map();

    for(const v of vehicles){
    // 1. 다음 조건을 모두 만족하는 차량만 사용한다.
    //    - `status === "AVAILABLE"`
    if (v.status !== "AVAILABLE") continue

    //    - `year >= minYear`
    if (v.year < minYear) continue

    //    - `price`가 유한한 숫자이며 0보다 큼
    if (typeof v.price != 'number' || v.price <= 0 || v.price >= Infinity) continue

    //    - `maker`가 문자열이며 공백을 제거한 결과가 비어 있지 않음
    if (typeof v.maker != 'string' || v.maker.trim().length <= 0) continue

    // 2. 제조사 이름 앞뒤의 공백은 제거한다.
    const maker = v.maker.trim()

    // 3. 제조사별로 다음 값을 계산한다.
    //    - `count`: 조건을 만족하는 차량 수
    //    - `minPrice`: 최저 가격
    //    - `averagePrice`: 평균 가격을 반올림한 정수
    let summary
    if (results.has(maker)){
        summary = results.get(maker)
        summary.count += 1
        summary.minPrice = Math.min(v.price, summary.minPrice)
        summary.averagePrice = Math.round((summary.averagePrice + v.price) / summary.count)
    } else summary = { maker: maker, count: 1, minPrice: v.price, averagePrice: v.price }

    results.set(maker, summary)

  }
  const summary = [...results.values()].sort((a, b) => {
    // 4. 결과는 `minPrice` 오름차순으로 정렬한다.
    if (a.minPrice !== b.minPrice) {
        return a.minPrice - b.minPrice;
    }

    // 5. `minPrice`가 같으면 `maker` 오름차순으로 정렬한다.
    return a.maker.localeCompare(b.maker)
  });

  return summary
}

function runTests() {

  // 테스트 1: 기존 정상 입력
  const vehicles = [
  { id: "V-100", maker: "Hyundai", year: 2022, price: 12000, status: "AVAILABLE" },
  { id: "V-101", maker: " Kia ", year: 2021, price: 10000, status: "AVAILABLE" },
  { id: "V-102", maker: "Hyundai", year: 2020, price: 8000, status: "AVAILABLE" },
  { id: "V-103", maker: "Hyundai", year: 2023, price: 10000, status: "AVAILABLE" },
  { id: "V-104", maker: "Kia", year: 2024, price: 15000, status: "SOLD" },
  { id: "V-105", maker: "Kia", year: 2022, price: "9000", status: "AVAILABLE" },
  { id: "V-106", maker: "Kia", year: 2023, price: 14000, status: "AVAILABLE" },
  { id: "V-107", maker: " ", year: 2024, price: 5000, status: "AVAILABLE" }
];


  assert.deepStrictEqual(
    summarizeVehicles(vehicles, 2021),
    [
        { maker: "Hyundai", count: 2, minPrice: 10000, averagePrice: 11000 },
        { maker: "Kia", count: 2, minPrice: 10000, averagePrice: 12000 }
    ]
  );

  //   테스트 2: 빈 배열
  assert.deepStrictEqual(
    summarizeVehicles([], 2020),
    []
  );

  // 테스트 3: null
  assert.deepStrictEqual(
    summarizeVehicles(null, 2020),
    []
  );

  // 테스트 4: 잘못된 가격
  assert.deepStrictEqual(
    summarizeVehicles(
      [
        {
          id: "V-1",
          maker: "Kia",
          year: 2024,
          price: -100,
          status: "AVAILABLE"
        }
      ],
      2020
    ),
    []
  );

  console.log("✅ 모든 테스트 통과");
}

runTests();