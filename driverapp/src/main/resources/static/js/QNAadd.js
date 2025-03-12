function submit_go() {
console.log("버튼클릭");

// 제목과 내용을 가져오는 코드
let input_title = document.querySelector("input[name='title']");
let input_content = document.querySelector("textarea[name='content']");

if (!input_title.value) {
alert('제목을 입력해주세요.');
input_title.focus();
return;
}

if (!input_content.value) {
alert('내용을 입력해주세요.');
input_content.focus();
return;
}

console.log(input_title.value + " " + input_content.value);

// 여기서 실제 공지사항을 저장하는 AJAX 호출을 할 수 있습니다.
const data = {
"title": input_title.value,
"content": input_content.value,
};

$.ajax({
url: '/api/board/add',  // 공지사항 저장을 위한 URL
type: 'POST',
data: JSON.stringify(data),  // 데이터를 JSON 형식으로 보냄
contentType: 'application/json; charset=utf-8',
success: function(response) {
console.log(response);
alert('등록이 완료되었습니다.');
window.location.href = 'QNA.html'; // 저장 후 공지사항 페이지로 이동
},
error: function(xhr, status, error) {
console.error;
alert('저장 중 오류가 발생했습니다.');
}
});
}

