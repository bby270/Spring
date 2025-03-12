// 게시물 수정 함수
function updateBoard() {
    let input_title = document.querySelector("input[name='title']");
    let input_content = document.querySelector("textarea[name='content']");

    // 제목과 내용이 비어있는지 확인
    if (!input_title.value || !input_content.value) {
        alert('제목과 내용을 모두 입력해주세요.');
        return;
    }

    // 수정된 데이터를 객체로 준비
    const data = {
        "title": input_title.value,
        "content": input_content.value
    };

    const boardId = getBoardIdFromUrl();  // 게시물 ID 추출

    // 게시물 수정 API 호출
    $.ajax({
        url: `/api/board/update/${boardId}`,  // 게시물 수정 API URL
        type: 'PUT',  // PUT 방식으로 수정
        data: JSON.stringify(data),  // 데이터를 JSON 형식으로 보냄
        contentType: 'application/json; charset=utf-8',  // JSON 형식의 데이터
        success: function(response) {
            console.log('서버 응답:', response);  // 서버 응답 확인
            alert('게시물 수정이 완료되었습니다.');

            // 수정 후 게시물 상세 페이지로 이동 (경로 확인 필수)
            const redirectUrl = `/replyQ&A.html?id=${boardId}`;
            console.log('이동할 URL:', redirectUrl); // 디버깅을 위해 URL 출력
            window.location.href = redirectUrl;  // 수정 후 이동
        },
        error: function(xhr, status, error) {
            console.error('서버 오류:', error);  // 오류 상세 확인
            alert('게시물 수정을 실패하였습니다.');
        }
    });
}

// 페이지 로드 시 게시물 ID로 게시물 정보 가져오기
$(document).ready(function() {
    const boardId = getBoardIdFromUrl();
    if (boardId) {
        fetchBoardDetails(boardId);  // 게시물 수정 폼 채우기
    }

    // 수정 폼 제출 시
    $('#saveBtn').click(function(event) {
        event.preventDefault();  // 폼의 기본 제출 동작 막기
        updateBoard();  // 수정 함수 호출
    });
});

// 게시물 데이터를 불러오는 함수
function fetchBoardDetails(boardId) {
    $.ajax({
        url: `/api/board/${boardId}`,  // 게시물 데이터 가져오기
        type: 'GET',
        success: function(data) {
            // 서버에서 받아온 데이터를 제목과 내용 폼에 채워넣기
            $('#title').val(data.title);
            $('#content').val(data.content);
        },
        error: function(error) {
            console.error("게시물을 불러오는 데 실패했습니다:", error);
            alert("게시물을 불러오는 데 실패했습니다.");
        }
    });
}

// URL에서 게시물 ID를 추출하는 함수
function getBoardIdFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('id');
}