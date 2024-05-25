document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('contactForm').addEventListener('submit', function(event) {
      event.preventDefault(); // 기본 이벤트 제거
  
      // 폼 데이터 가져오기
      const name = document.getElementById('name').value;
      const email = document.getElementById('email').value;
      const subject = document.getElementById('subject').value;
      const message = document.getElementById('message').value;
  
      // 새로운 문의사항 객체 생성
      const newInquiry = {
        name: name,
        email: email,
        subject: subject,
        message: message,
        date: new Date().toISOString()
      };
  
      // 로컬 스토리지에서 기존 문의사항 데이터 가져오기
      const inquiries = JSON.parse(localStorage.getItem('inquiries')) || [];
      inquiries.push(newInquiry);
  
      // 로컬 스토리지에 저장
      localStorage.setItem('inquiries', JSON.stringify(inquiries));
  
      // 작성 완료 후 문의사항 게시판으로 이동
      window.location.href = '/sportLink/pages/Q&A/Q&A.html';
    });
  });