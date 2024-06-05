// 금액 계산
const sumCostFn = () => {
    const selectStockList = document.querySelectorAll(".select-stock"); // 발주 재고 항목

    selectStockList.forEach(stock => {
        const stockCount = stock.querySelector(".test-count");  // 수량 입력

        stockCount.addEventListener("input", e => {
            // 숫자만 입력 가능
            const inpValue = stockCount.value;
            const regExp = /^[0-9]+$/;
            if (!regExp.test(inpValue)) stockCount.value = inpValue.slice(0, -1);

            const price = stock.querySelector(".test-price").innerText.replace(/,/g, '');
            const count = stockCount.value;
            const cost = price * count;

            stock.querySelector(".test-cost").innerText = cost.toLocaleString();
            stockCount.nextElementSibling.value = cost;
            sumOrderCostFn();
        })
    })
}

// 총 합계금액 계산
const sumOrderCostFn = () => {
    const stockOrderCost = document.querySelectorAll(".stock-order-cost");  // 금액 리스트
    let orderCost = 0;  // 합계금액

    stockOrderCost.forEach(cost => {
        orderCost += parseInt(cost.value === '' ? 0 : parseInt(cost.value));
        document.querySelector("#order-cost").innerText = orderCost.toLocaleString();
    })
}

// 발주 재고 임시저장
const checkOrderTempFn = async () => {
    const response = await fetch ("/crispy/ckeck-order-temp");
    const result = await response.text();

    if (result > 0)
        if (!confirm("임시저장된 내용이 이미 존재합니다. 덮어씌울까요?")) return;

    const formData = new FormData(document.querySelector("#form-container"));

    fetch ("/crispy/stock-order-temp", {
        method: "POST",
        body: formData
    })
        .then(response => response.text())
        .then(result => {
            if (result > 0) alert("임시저장이 완료되었습니다.");
            else alert("임시저장에 실패했습니다.")
        })
}

// 임시저장 버튼
document.querySelector("#temp").addEventListener("click", () => {
    checkOrderTempFn();
})

// 임시저장 내용 불러오기
const getOrderTempFn = async () => {
    const response = await fetch ("/crispy/get-order-temp");
    const html = await response.text();
    document.querySelector("#stock-temp-container").outerHTML = html;
}

// 임시저장 내용 불러오기 버튼
document.querySelector("#temp-content").addEventListener("click", async () => {
    if (document.querySelectorAll("#stock-temp-container > div").length > 0)
        if (!confirm("임시저장 내용을 불러오면 현재 재고 목록이 사라집니다. 계속 할까요?")) return;

    await getOrderTempFn();
    sumCostFn();
    sumOrderCostFn();
})

// 초기화
document.addEventListener("DOMContentLoaded", function () {
    sumCostFn();
    sumOrderCostFn();
})