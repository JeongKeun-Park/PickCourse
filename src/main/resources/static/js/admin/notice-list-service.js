//2025.03.15  조승찬

// 상세조회
function fetchNoticeDetail(id){
    return fetch(`/admin/notice-detail/${id}`)
        .then(response => response.json())
        .then(data => {
            noticeModal(data.notice)
        })
        .catch(error => {
            console.error("course list 데이타를 가져오는 중 오류", error);
            throw error;
        });
};

// 등록
function insertNoticeDetail(sendData) {
    return fetch("/admin/notice-detail", {
        method: "POST",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify(sendData)
    })
        .then(response => {
            if (!response.ok){
                throw new Error("공지사항 등록에 실패했습니다!");
                return;
            }
            console.log("공지사항 등록 성공!");

            fetchNoticeList(1, ``, ``)  // 신규 등록시는 첫 화면부터 조회
        })
        .catch(error => {
            console.error("공지사항 데이타 수정을 요청하는 중 오류", error);
            throw error;
        });
}

// 업데이트
function updateNoticeDetail(sendData, page, type, keyWord) {
    return fetch(`/admin/notice-detail` ,{
        method: "PUT",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify(sendData)
    })
        .then (response => {
            if (!response.ok){
                throw new Error("공지사항 데이터 수정에 실패했습니다!");
                return;
            }
            console.log("공지사항 수정 데이터 전송 성공!");

            fetchNoticeList(page, type, keyWord)  // 조회하던 위치로 다시 공지목록 조회요청
        })
        .catch(error => {
            console.error("공지사항 데이타 수정을 요청하는 중 오류", error);
            throw error;
        });
}

// 삭제
function deleteNoticeList(id, page, type, keyWord) {
    return fetch(`/admin/notice-detail/${id}`, {
        method: "DELETE",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify(id)
    })
        .then (response => {
            if (!response.ok){
                throw new Error("공지사항 데이터 삭제에 실패했습니다!");
                return;
            }
            console.log("공지사항 삭제 데이터 전송 성공!");

            fetchNoticeList(page, type, keyWord)  // 조회하던 위치로 다시 공지목록 조회요청
        })
        .catch(error => {
            console.error("공지사항 데이타 삭제를 요청하는 중 오류", error);
            throw error;
        });
}