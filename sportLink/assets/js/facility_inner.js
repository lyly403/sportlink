// JavaScript 파일에서 상세 페이지 초기화 및 데이터 표시

document.addEventListener('DOMContentLoaded', function () {
  // LocalStorage에서 Form 데이터 가져오기
  const formData = JSON.parse(localStorage.getItem('formData'));

  if (formData) {
    // 제목
    document.getElementById('detailTitle').textContent = formData.title;
    // 날짜
    document.getElementById('detailDate').textContent = formData.date;
    // 지역
    document.getElementById('detailLocation').textContent = formData.location;
    // 가격
    document.getElementById('detailPrice').textContent = formData.price;
    // 구매 수량
    document.getElementById('detailCount').textContent = formData.count;
    // 종목
    document.getElementById('detailCategory').textContent = formData.category;
    // 텍스트 영역
    document.getElementById('detailTextArea').textContent = formData.textArea;
    // 위치 (지도 API) 처리는 필요에 따라 추가
    // 이미지 처리는 필요에 따라 추가
  }

  // 구매 버튼 클릭 시 처리 (예시)
  const purchaseButton = document.getElementById('purchaseButton');
  purchaseButton.addEventListener('click', function () {
    // 여기에 구매 처리 로직 추가
    alert('구매가 완료되었습니다!');
  });
});
