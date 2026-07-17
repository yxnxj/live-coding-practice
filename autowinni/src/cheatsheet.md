# Java 코딩 테스트 직전 복습

## 여러 조건을 직접 비교하는 Comparator

여러 정렬 조건의 오름차순과 내림차순이 섞여 있을 때는 람다에서 조건을 순서대로 비교하면 동작을 이해하기 쉽다.

```java
results.sort((a, b) -> {
    if (a.totalRevenue != b.totalRevenue) {
        return Long.compare(b.totalRevenue, a.totalRevenue);
    }

    if (a.totalQuantity != b.totalQuantity) {
        return Integer.compare(b.totalQuantity, a.totalQuantity);
    }

    return a.productName.compareTo(b.productName);
});
```

위 Comparator의 정렬 순서:

1. `totalRevenue` 내림차순
2. 매출이 같으면 `totalQuantity` 내림차순
3. 매출과 수량이 같으면 `productName` 오름차순

## Comparator 반환값

```text
음수: a를 b보다 앞에 배치
0:   두 값을 같은 순서로 취급
양수: a를 b보다 뒤에 배치
```

## 숫자 비교

오름차순은 `a, b` 순서로 비교한다.

```java
Integer.compare(a.totalQuantity, b.totalQuantity);
Long.compare(a.totalRevenue, b.totalRevenue);
```

내림차순은 인자 순서를 반대로 둔다.

```java
Integer.compare(b.totalQuantity, a.totalQuantity);
Long.compare(b.totalRevenue, a.totalRevenue);
```

다음처럼 뺄셈으로 비교하지 않는 편이 안전하다.

```java
// 피하기
return (int) (b.totalRevenue - a.totalRevenue);
```

값의 차이가 크면 오버플로가 발생하거나 `long`을 `int`로 변환하면서 값이 손실될 수 있다.

## 문자열 비교

문자열 오름차순:

```java
return a.productName.compareTo(b.productName);
```

문자열 내림차순:

```java
return b.productName.compareTo(a.productName);
```

## Map 핵심 문법

```java
Map<String, Integer> counts = new HashMap<>();
```

| 문법 | 동작 | 반환값 |
| --- | --- | --- |
| `map.put(key, value)` | 값 추가 또는 수정 | 이전 값, 없었으면 `null` |
| `map.get(key)` | 값 조회 | 값, 없으면 `null` |
| `map.getOrDefault(key, defaultValue)` | 값 또는 기본값 조회 | 값 |
| `map.containsKey(key)` | 키 존재 여부 확인 | `boolean` |
| `map.remove(key)` | 해당 키와 값 삭제 | 삭제한 값, 없으면 `null` |
| `map.size()` | 저장된 항목 개수 확인 | `int` |
| `map.isEmpty()` | 비어 있는지 확인 | `boolean` |
| `map.clear()` | 모든 값 삭제 | 반환값 없음 |

빈도 누적:

```java
counts.put(name, counts.getOrDefault(name, 0) + 1);
```

Map 순회:

```java
for (Map.Entry<String, Integer> entry : counts.entrySet()) {
    String key = entry.getKey();
    int value = entry.getValue();
}
```

키, 값, key-value 쌍을 각각 가져올 수 있다.

```java
counts.keySet();   // Set<String>
counts.values();   // Collection<Integer>
counts.entrySet(); // Set<Map.Entry<String, Integer>>
```

Map의 값을 수정 가능한 List로 복사:

```java
List<Integer> results = new ArrayList<>(counts.values());
```

## List 핵심 문법

```java
List<String> names = new ArrayList<>();
```

| 문법 | 동작 | 반환값 |
| --- | --- | --- |
| `list.add(value)` | 맨 뒤에 추가 | `boolean` |
| `list.add(index, value)` | 해당 위치에 추가 | 반환값 없음 |
| `list.get(index)` | 해당 위치의 값 조회 | 값 |
| `list.set(index, value)` | 해당 위치의 값 교체 | 이전 값 |
| `list.remove(index)` | 해당 위치의 값 삭제 | 삭제한 값 |
| `list.contains(value)` | 값 포함 여부 확인 | `boolean` |
| `list.size()` | 원소 개수 확인 | `int` |
| `list.isEmpty()` | 비어 있는지 확인 | `boolean` |
| `list.clear()` | 모든 값 삭제 | 반환값 없음 |

마지막 값 확인과 삭제:

```java
String last = names.get(names.size() - 1);
String removed = names.remove(names.size() - 1);
```

`List<Integer>`에서 `remove()`는 인자 타입에 따라 의미가 달라지므로 주의한다.

```java
numbers.remove(1);                  // 1번 인덱스 삭제
numbers.remove(Integer.valueOf(1)); // 값 1을 찾아 삭제
```

수정 가능한 복사본 생성:

```java
List<String> copied = new ArrayList<>(names);
```

`List.of(...)`로 만든 List에는 `add()`, `set()`, `remove()`를 사용할 수 없다.

```java
List<String> fixed = List.of("A", "B");
List<String> mutable = new ArrayList<>(fixed);
```

## Deque를 Stack으로 사용

Java의 `List`에는 `push()`와 `pop()`이 없다. 스택이 필요하면 `Deque`와 `ArrayDeque`를 사용한다.

```java
Deque<Character> stack = new ArrayDeque<>();

stack.push('A');       // 맨 위에 추가
Character top = stack.peek();    // 맨 위 값 확인
Character removed = stack.pop(); // 맨 위 값 제거
boolean empty = stack.isEmpty();
```

`push()`와 `pop()`을 한 쌍으로 기억한다. 빈 Deque에서 `pop()`을 호출하면 예외가 발생하므로 먼저 `isEmpty()`를 확인한다. `peek()`은 비어 있으면 `null`을 반환한다.

## String을 char 배열로 변환

문자열을 문자 단위로 순회하거나 각 문자를 수정해야 할 때 `toCharArray()`를 사용한다.

```java
String text = "hello";
char[] chars = text.toCharArray();

for (char ch : chars) {
    System.out.println(ch);
}
```

인덱스로 바로 순회하기만 한다면 배열로 변환하지 않고 `charAt()`을 사용할 수도 있다.

```java
for (int i = 0; i < text.length(); i++) {
    char ch = text.charAt(i);
}
```

`char[]`을 다시 `String`으로 변환할 때는 `new String(chars)`를 사용한다.

```java
String converted = new String(chars);
```
