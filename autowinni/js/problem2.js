const assert = require("node:assert/strict");

function findVehiclePair(prices, target) {

    const idxes = new Map();

    for(let i = 0; i < prices.length; i++){
        subs = target - prices[i];

        if(idxes.has(subs)) {
            return [idxes.get(subs), i];
        }

        idxes.set(prices[i], i)
    }

    return []
}

assert.deepStrictEqual(
    findVehiclePair([12000, 9000, 15000, 7000], 16000),
    [1, 3]
)

assert.deepStrictEqual(
    findVehiclePair([8000, 8000], 16000),
    [0, 1]
)

assert.deepStrictEqual(
    findVehiclePair([5000, 6000, 7000], 20000),
    []
)


console.log("test all passed")