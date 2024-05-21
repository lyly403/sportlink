// JavaScript 파일에서 상세 페이지 초기화 및 데이터 표시
document.addEventListener('DOMContentLoaded', function () {
  // URL에서 데이터 가져오기
  var urlParams = new URLSearchParams(window.location.search);
  var title = urlParams.get('title');
  var date = urlParams.get('date');
  var location = urlParams.get('location');
  var price = urlParams.get('price');
  var count = urlParams.get('count');
  var category = urlParams.get('category');
  var textArea = urlParams.get('textArea');

  // 데이터가 존재할 경우에만 출력
  if (title && date && location && price && count && category && textArea) {
    document.getElementById('detailTitle').textContent = title;
    document.getElementById('detailDate').textContent = date;
    document.getElementById('detailLocation').textContent = location;
    document.getElementById('detailPrice').textContent = price;
    document.getElementById('detailCount').textContent = count;
    document.getElementById('detailCategory').textContent = category;
    document.getElementById('detailTextArea').textContent = textArea;
  }

  // 구매 버튼 클릭 시 처리
  const purchaseButton = document.getElementById('purchaseButton');
  purchaseButton.addEventListener('click', function () {
    // 구매 내역 페이지로 데이터 전송
    var orderData = {
      title: title,
      date: date,
      location: location,
      price: price,
      count: count,
      category: category
    };

    // 로컬스토리지에 저장 (간단한 예시)
    localStorage.setItem('orderData', JSON.stringify(orderData));

    // '구매완료' 알림창 표시
    alert('구매완료!');

    // 주문 내역 페이지로 이동
    window.location.href = "/sportLink/pages/user/order.html";
  });
});
