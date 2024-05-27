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

// 전화번호 입력필드 전화번호 형식 변환
function oninputPhone(target) {
    target.value = target.value
        .replace(/[^0-9]/g, '')
        .replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3");
}

// 수정하기 버튼 클릭 시 확인 창 표시
document.getElementById("edit-button").addEventListener("click", function() {
    var confirmed = confirm("회원정보를 수정하시겠습니까?");
    if (!confirmed) {
        event.preventDefault(); // 기본 이벤트(폼 제출)를 막음
        return false; // 이벤트 전파 중지
    }
});