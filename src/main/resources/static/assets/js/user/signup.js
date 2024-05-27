function allCheck(){
    let id = document.getElementById('id');
    let pw = document.getElementById('pw');
    let email = document.getElementById('email');
    confirm = document.getElementById('confirm');
    userName = document.getElementById('userName');

    if(id.value == ""){
        alert('아이디는 필수 항목입니다.');
    }else if(pw.value == ""){
        alert('비밀번호는 필수 항목입니다.');
    }else if(confirm.value == ""){
        alert('비밀번호 확인은 필수 항목입니다.');
    }else if(userName.value == ""){
        alert('이름은 필수 항목입니다.');
    }else if(email.value == ""){
        alert('이메일은 필수 항목입니다.');
    }else{
        var f = document.getElementById('f');
        f.submit();
    }
}
// 전화번호 입력필드 전화번호 형식 변환
function oninputPhone(target) {
    target.value = target.value
        .replace(/[^0-9]/g, '')
        .replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3");
}

// 회원가입 버튼 클릭 시 확인 창 표시
document.getElementById("signup-button").addEventListener("click", function() {
    var confirmed = confirm("회원가입 하시겠습니까?");
    if (!confirmed) {
        event.preventDefault(); // 기본 이벤트(폼 제출)를 막음
        return false; // 이벤트 전파 중지
    }
});

function loginCheck(){
    let id = document.getElementById('id');
    let pw = document.getElementById('pw');

    if(id.value == ""){
        alert('아이디는 필수 항목입니다.');
    }else if(pw.value == ""){
        alert('비밀번호는 필수 항목입니다.');
    }else{
        var f = document.getElementById('f');
        f.submit();
    }
}

function validatePassword() {
    var pw = document.getElementById("pw").value;
    var confirm = document.getElementById("confirm").value;

    if (password != confirm) {
        alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return false;
    }
    return true;
}

window.onload = function() {
    document.getElementById("signupForm").addEventListener("submit", function(event) {
        if (!validatePassword()) {
            event.preventDefault();
        }
    });
};