// 25.03.21 조승찬 작성

// 목록 조회 레스트컨트롤러:: 피드 목록에서 더보기 버튼 클릭시
function fetchTourListApi(page) {
    return fetch (`/feeds/tour-list/api?page=${page}`)
        .then(response => response.json())
        .then(data => {
            addTourListAndPage(data.tours, data.pagination);
        })
        .catch(error => {
            console.error("reply list 데이타를 가져오는 중 오류", error);
            throw error;
        });
}