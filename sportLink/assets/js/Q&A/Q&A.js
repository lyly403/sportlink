document.addEventListener('DOMContentLoaded', function() {
    // 로컬 스토리지에서 문의사항 데이터 가져오기
    const inquiries = JSON.parse(localStorage.getItem('inquiries')) || [];
  
    // 게시판에 문의사항 데이터 출력
    const boardContent = document.getElementById('boardContent');
    inquiries.forEach((inquiry, index) => {
      const row = `
        <tr>
          <th scope="row">${index + 1}</th>
          <td><a href="/sportLink/pages/Q&A/Q&A_inner.html?index=${index}">${inquiry.subject}</a></td>
          <td>${inquiry.name}</td>
          <td>${inquiry.email}</td>
          <td>${new Date(inquiry.date).toLocaleDateString()}</td>
        </tr>
      `;
      boardContent.innerHTML += row;
    });
  });
  