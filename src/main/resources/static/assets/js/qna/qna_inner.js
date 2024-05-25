document.addEventListener('DOMContentLoaded', function() {
    // URL에서 index 값 추출
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const index = urlParams.get('index');

    // 로컬 스토리지에서 문의사항 데이터 가져오기
    const inquiries = JSON.parse(localStorage.getItem('inquiries')) || [];
    const inquiry = inquiries[index];

    // 상세 페이지에 문의사항 데이터 출력
    document.getElementById('inquirySubject').textContent = inquiry.subject;
    document.getElementById('inquiryName').textContent = `작성자: ${inquiry.name}`;
    document.getElementById('inquiryEmail').textContent = `이메일: ${inquiry.email}`;
    document.getElementById('inquiryDate').textContent = `작성 날짜: ${new Date(inquiry.date).toLocaleDateString()}`;
    document.getElementById('messageContent').textContent = inquiry.message;
});

