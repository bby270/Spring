// URL에서 id 파라미터 가져오기
const urlParams = new URLSearchParams(window.location.search);
const questionId = urlParams.get('id');
console.log('게시물 ID:', questionId);

// 게시물 상세 정보 가져오기
function fetchQuestionDetails() {
    $.ajax({
        url: `/api/board/${questionId}`,
        type: 'GET',
        success: function(data) {
            console.log(data);
            $('#question-title').text(data.title);
            $('#question-date').text(data.createdDate);
            $('#question-content').text(data.content);
            $('#question-author').text(data.author);
        },
        error: function(error) {
            console.error("게시물 세부 정보를 불러오는 데 실패했습니다.", error);
            alert("게시물을 불러오는 데 실패했습니다.");
        }
    });
}

// 답글 목록을 불러오는 함수
function loadReplies() {
    $.ajax({
        url: `/api/comment/board/${questionId}`,  // 게시물 전체 조회
        type: 'GET',
        success: function(response) {
            console.log("답글 목록:", response);  // 응답 데이터 콘솔 출력
            let repliesList = $('#reply-list');  // 답글을 표시할 ul 요소
            repliesList.empty();

            // 응답이 배열로 전달되면 각 답글을 li 항목으로 추가
            if (Array.isArray(response) && response.length > 0) {
                response.forEach(function(reply, index) {
                    let replyItem = `
                        <li>
                            <span class="number">${index + 1}</span>
                            <span class="comment">${reply.comment || '내용 없음'}</span>
                            <span class="user">${reply.user || '작성자 '}</span>
                            <button type="button" class="deleteReplyBtn" data-reply-id="${reply.id}">삭제</button>
                        </li>
                    `;
                    repliesList.append(replyItem);  // 답글 항목을 추가
                });
            } else {
                // 답글이 없으면 '답글이 없습니다.' 표시
                repliesList.append('<li>답글이 없습니다.</li>');
            }
        },
        error: function(error) {
            console.error("답글 목록을 불러오는 데 실패했습니다.", error);
        }
    });
}

// 페이지 로드 시 게시물과 답글 목록 불러오기
$(document).ready(function() {
    if (questionId) {
        fetchQuestionDetails(); // 게시물 정보 불러오기
        loadReplies(); // 답글 목록 불러오기
    }

    // 삭제 버튼 클릭 시
    $('#deleteBtn').click(function() {
        if (confirm('정말 게시물을 삭제하시겠습니까?')) {
            $.ajax({
                url: `/api/board/delete/${questionId}`,  // 게시물 삭제 API URL
                type: 'DELETE',
                success: function(response) {
                    console.log('서버 응답:', response);
                    alert('게시물이 삭제되었습니다.');
                    window.location.href = '/QNA.html';  // 삭제 후 목록 페이지로 이동
                },
                error: function(xhr, status, error) {
                    console.error('서버 오류:', error);
                    alert('게시물 삭제에 실패하였습니다.');
                }
            });
        }
    });
 // 답글 삭제 버튼 클릭 시
    $('#reply-list').on('click', '.deleteReplyBtn', function() {
        const replyId = $(this).data('reply-id');  // 클릭된 버튼의 data-reply-id 값 가져오기
        deleteReply(replyId);  // 답글 삭제 함수 호출
    });
});

// 답글 삭제 함수
function deleteReply(replyId) {
    if (confirm('정말 답글을 삭제하시겠습니까?')) {
        $.ajax({
            url: `/api/comment/delete/${replyId}`,  // 답글 삭제 API URL
            type: 'DELETE',
            success: function(response) {
                console.log('서버 응답:', response);
                alert('답글이 삭제되었습니다.');

                // 삭제 후, 해당 답글 항목을 페이지에서 제거
                $(`#reply-${replyId}`).remove();

                // 답글 목록을 다시 불러옴
                loadReplies();  // 답글 목록 갱신
            },
            error: function(xhr, status, error) {
                console.error('서버 오류:', error);
                alert('답글 삭제에 실패하였습니다.');
            }
        });
    }
}
// 답글 등록 함수
function submit_go() {
    console.log("답글 버튼 클릭");

    // 답글 내용을 가져오기
    let input_content = document.querySelector("textarea[name='content']");

    if (!input_content.value) {
        alert('내용을 입력해주세요.');
        input_content.focus();
        return;
    }

    console.log("답글 내용: " + input_content.value);

    // 답글을 서버에 전송하는 AJAX 요청
    const data = {
        "comment": input_content.value,  // 답글 내용
        "boardId": questionId  // 게시물 ID
    };

    $.ajax({
        url: '/api/comment/add',  // 답글 저장을 위한 URL (서버 API 경로를 확인해주세요)
        type: 'POST',
        data: JSON.stringify(data),  // 데이터를 JSON 형식으로 보냄
        contentType: 'application/json; charset=utf-8',
        success: function(response) {
            console.log("답글 등록 성공:", response);
            alert('답글이 등록되었습니다.');
            loadReplies();  // 등록 후, 답글 목록 다시 불러오기
            input_content.value = '';  // 답글 내용 초기화
        },
        error: function(xhr, status, error) {
            console.error("답글 등록 중 오류 발생", error);
            alert('답글 등록 중 오류가 발생했습니다.');
        }
    });
}



