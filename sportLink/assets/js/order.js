// order.js

// 예시로 localStorage에 저장된 주문 데이터를 가져오는 함수
function getOrderData() {
  return JSON.parse(localStorage.getItem('orderData')) || [];
}

// 예시로 localStorage에 주문 데이터를 저장하는 함수
function saveOrderData(orderData) {
  localStorage.setItem('orderData', JSON.stringify(orderData));
}

// 주문 내역을 화면에 표시하는 함수
function displayOrderHistory() {
  const orderData = getOrderData();

  // 여기서 orderData를 이용하여 주문 내역을 화면에 표시하는 로직을 구현
  // 예시: DOM 조작을 통해 주문 내역을 리스트로 표시
}

// 초기화 함수: 페이지 로드 시 주문 내역 표시
window.onload = function () {
  displayOrderHistory();
};

// 구매 버튼 클릭 이벤트 처리
document.addEventListener('DOMContentLoaded', function () {
  const purchaseButtons = document.querySelectorAll('.purchase-button');

  purchaseButtons.forEach(function (button) {
    button.addEventListener('click', function (event) {
      // 클릭된 버튼에서 데이터 가져오기 (예시: 버튼의 data-속성 활용)
      const formData = {
        // 예시: 버튼의 data-속성에서 필요한 데이터 가져오기
        title: event.target.dataset.title,
        date: event.target.dataset.date,
        location: event.target.dataset.location,
        price: event.target.dataset.price,
        count: event.target.dataset.count,
        category: event.target.dataset.category,
        // 필요한 경우 추가 데이터 가져오기
      };

      // 현재 주문 데이터 가져오기
      let orderData = getOrderData();

      // 새 주문 데이터 추가
      orderData.push(formData);

      // 업데이트된 주문 데이터 저장
      saveOrderData(orderData);

      // 구매 완료 메시지 (예시)
      alert('구매가 완료되었습니다!');

      // 구매 내역 페이지로 이동 (예시: 경로는 필요에 따라 수정)
      window.location.href = 'order_history.html';
    });
  });
});
