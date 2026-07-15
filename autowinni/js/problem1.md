# JavaScript 문제 1 — 판매 가능한 차량 요약

- 제한시간: 30분
- 구현 파일: `problem1.js`

## 함수 형태

```javascript
function summarizeVehicles(vehicles, minYear) {
  // 구현
}
```

## 요구사항

1. 다음 조건을 모두 만족하는 차량만 사용한다.
   - `status === "AVAILABLE"`
   - `year >= minYear`
   - `price`가 유한한 숫자이며 0보다 큼
   - `maker`가 문자열이며 공백을 제거한 결과가 비어 있지 않음
2. 제조사 이름 앞뒤의 공백은 제거한다.
3. 제조사별로 다음 값을 계산한다.
   - `count`: 조건을 만족하는 차량 수
   - `minPrice`: 최저 가격
   - `averagePrice`: 평균 가격을 반올림한 정수
4. 결과는 `minPrice` 오름차순으로 정렬한다.
5. `minPrice`가 같으면 `maker` 오름차순으로 정렬한다.
6. 입력이 배열이 아니거나 비어 있으면 빈 배열을 반환한다.
7. 원본 배열과 원본 객체를 변경하지 않는다.

## 결과 형태

```javascript
[
  { maker: "Hyundai", count: 2, minPrice: 10000, averagePrice: 11000 }
]
```

## 예제 입력

```javascript
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

summarizeVehicles(vehicles, 2021);
```

## 기대 결과

```javascript
[
  { maker: "Hyundai", count: 2, minPrice: 10000, averagePrice: 11000 },
  { maker: "Kia", count: 2, minPrice: 10000, averagePrice: 12000 }
]
```

## 완료 후 기록

- 시간복잡도:
- 원본 데이터 변경을 방지한 방법:
- 직접 테스트한 엣지케이스:
