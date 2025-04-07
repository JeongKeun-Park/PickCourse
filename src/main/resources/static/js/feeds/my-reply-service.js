// 25.03.17 조승찬 작성

// 목록 조회 레스트컨트롤러:: 피드 목록에서 댓글 버튼 클릭시
function fetchReplyListApi(feedId, page) {
    return fetch (`/feeds/my/reply-list/api?page=${page}`) // 전체조회 대비 feedId 사용안함
        .then(response => response.json())
        .then(data => {
            addReplyListAndPage(data.replys, data.pagination);
        })
        .catch(error => {
            console.error("reply list 데이타를 가져오는 중 오류", error);
            throw error;
        });
}

// 댓글의 삭제 버튼 클릭시  ==> 레스트 방식으로 삭제처리하고 컨트롤러 조회방식으로 리다이렉트
function deleteReplyList(id, feedId) {
    return fetch(`/feeds/my/reply-list/${id}`, {
        method: "DELETE",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify({ id: id })
    })
        .then(response => response.json())
        .then(data => {

            window.location.href = data.redirectUrl;  // json 방식의 메서드로 부터 직접 @GetMapping("/reply-list/{feedId}") 호출하는 방식

        })
        .catch(error => {
            console.error("reply 데이타 삭제를 요청하는 중 오류", error);
            throw error;
        });
}

// 25.03.22 조승찬 시작
// 댓글 달린 피드 내용 불러오기
function fetchFeedDetail(id){
    return fetch(`/admin/report-detail/${id}?source=FEED`)
        .then(response => response.json())
        .then(data => {
            feedModel(data.reply)
        })
        .catch(error => {
            console.error("feed 상세 데이타를 가져오는 중 오류", error);
            throw error;
        });
};
// 25.03.22 조승찬 끝


// 댓글 신고 ==> 사용 안함
function reportReplyList(sendData) {
    return fetch(`/feeds/reply-list/report`, {
        method: "POST",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify(sendData)
    })
        .then(response => {
            if (!response.ok){
                console.error("reply 신고 데이타를 처리하는 중 오류", error);
                throw error;
            }
        })
        .catch(error => {
            console.error("reply 신고를 요청하는 중 오류", error);
            throw error;
        });
}

// 댓글 등록 ==> 사용 안함
function replyReplyList(sendData) {
    return fetch (`/feeds/reply-list`, {
        method: "POST",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify(sendData)
    })
        .then(response => response.json())
        .then(data => {
            window.location.href = data.redirectUrl;  // json 방식의 메서드로 부터 직접 @GetMapping("/reply-list/{feedId}") 호출하는 방식

        })
        .catch(error => {
            console.error("reply 데이타 등록을 요청하는 중 오류", error);
            throw error;
        });
}