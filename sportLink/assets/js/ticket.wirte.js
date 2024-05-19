// JavaScript 파일에서 글쓰기 완료 버튼에 대한 이벤트 처리 및 데이터 처리

document.addEventListener('DOMContentLoaded', function () {
    const writeForm = document.getElementById('writeForm');
  
    writeForm.addEventListener('submit', function (event) {
      event.preventDefault(); // 기본 제출 동작 방지
  
      // Form 데이터 가져오기
      const formData = {
        title: document.getElementById('title').value,
        date: document.getElementById('date').value,
        location: document.getElementById('location').value,
        price: document.getElementById('price').value,
        count: document.getElementById('count').value,
        category: document.getElementById('category').value,
        textArea: document.getElementById('text-area').value,
        // Map API 데이터 처리는 필요에 따라 추가
        // 이미지 처리도 필요에 따라 추가
      };
  
      // LocalStorage에 Form 데이터 저장
      localStorage.setItem('formData', JSON.stringify(formData));
  
      // 페이지 이동 등 필요한 추가 로직 수행
      // 예를 들어, 상세 페이지로 이동
      window.location.href = 'ticket_inner.html';
    });
  });
  