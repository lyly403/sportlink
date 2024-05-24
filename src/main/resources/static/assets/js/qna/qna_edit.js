document.getElementById('submitButton').addEventListener('click', function() {
    var title = document.getElementById('editTitle').value;
    var content = document.getElementById('editContent').value;

    // 제목이나 내용 중 하나라도 입력되었다면 폼 제출
    if (title.trim() !== '' || content.trim() !== '') {
        document.getElementById('editForm').submit();
    } else {
        alert('제목이나 내용 중 하나는 입력해야 합니다.');
    }
});