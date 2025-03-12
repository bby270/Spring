function fetchBoards() {
    $.ajax({
        url: '/api/board/all',
        type: 'GET',
        success: function(data) {
            console.log(data);
            let question = $('#question');
            question.empty();

            data.forEach(function(board, index) {
                console.log(board);
                let listItem = `
                    <li>
                        <a href="javascript:void(0)">
                            <span class="next">${index + 1}</span>
                            <span class="title-text">${board.title}</span>
                            <span class="author">${board.author}</span>
                        </a>
                        <div class="answer">
                            <ul>
                                <li class="co1" onclick="location.href='replyQ&A.html?id=${board.id}';"> ${board.title}</li>
                                <li class="co2" onclick="location.href='replyQ&A.html?id=${board.id}';">${board.content}</li>
                                <button type="button" onclick="location.href='correctionQ&A.html?id=${board.id}';">수정</button>
                            </ul>
                        </div>
                    </li>
                `;
                question.append(listItem);
            });

            // 슬라이딩 이벤트 추가
            $('figure a').click(function (e) {
                e.preventDefault();
                var answer = $(this).next('.answer');
                var allAnswers = $('.answer');

                // 다른 모든 answer 닫기
                allAnswers.not(answer).stop(true, true).animate({
                    maxHeight: '0',
                    opacity: 0
                }, 500);

                // 클릭한 answer 토글
                if (answer.css('max-height') === '0px' || answer.css('opacity') === '0') {
                    answer.stop(true, true).animate({
                        maxHeight: answer.prop('scrollHeight') + 'px',  // 내용에 맞게 동적으로 높이 설정
                        opacity: 1
                    }, 500);
                } else {
                    answer.stop(true, true).animate({
                        maxHeight: '0',
                        opacity: 0
                    }, 500);
                }
            });
        },
        error: function(error) {
            console.error("게시물을 불러오는 데 실패했습니다:", error);
            alert("게시물을 불러오는 데 실패했습니다.");
        }
    });
}

$(document).ready(function() {
    fetchBoards();
});

