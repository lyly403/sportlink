window.addEventListener("DOMContentLoaded", function() {
    // 로컬스토리지에서 주문 정보 가져오기
    var orderData = JSON.parse(localStorage.getItem('orderData'));
  
    // 주문 내역 게시판에 정보 추가
    if (orderData) {
      var orderHistoryContent = document.getElementById("orderHistoryContent");
      var orderItem = document.createElement("div");
      orderItem.classList.add("order-item");
      orderItem.innerHTML = `
          <div class="order-details">
              <p><strong>상품 제목:</strong> ${orderData.title}</p>
              <p><strong>구매 날짜:</strong> ${orderData.date}</p>
              <p><strong>구매 지역:</strong> ${orderData.location}</p>
              <p><strong>상품 가격:</strong> ${orderData.price}</p>
              <p><strong>구매 수량:</strong> ${orderData.count}</p>
              <p><strong>상품 종목:</strong> ${orderData.category}</p>
          </div>
      `;
      orderHistoryContent.appendChild(orderItem);
  
      // 데이터가 출력된 후 로컬스토리지에서 삭제 (필요에 따라)
      localStorage.removeItem('orderData');
    }
  });
  