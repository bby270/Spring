function move() {

    const user = sessionStorage.getItem('user');
    const userObj = JSON.parse(user);

    if (!user) {
        alert("로그인 후 이용 가능합니다.");
        window.location.href = "login.html";
        return;
    }
    else if (userObj.authority === 'ADMIN') {
        window.location.href = '/adminpage.html';

    }
    else if (userObj.authority === 'INSTRUCTOR') {
        window.location.href = '/adminpage.html';

    }
    else {
    window.location.href = '/usermypage.html'
    }

}