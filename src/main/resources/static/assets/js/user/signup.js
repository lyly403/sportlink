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

function pwCheck(){
    let pw = document.getElementById('pw');
    confirm = document.getElementById('confirm');
    label = document.getElementById('label');
    if(pw.value == confirm.value){
        label.innerHTML = '일치'
    }else{
        label.innerHTML = '불일치'
    }
    // window.alert('pwCheck 호출')
}

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