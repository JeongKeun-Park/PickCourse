// 25.03.22 조승찬 작성

// 찜 설정하기
function postWish(planId){
    return fetch(`/ranking/wish?planId=${planId}`, {
        method: "POST",
        body: planId
    }).then(response => {
        if (!response.ok){
            console.error(" 찜 등록 처리하는 중 오류", error);
            throw error;
        }
    })
        .catch(error => {
            console.error(" 찜 등록을 요청하는 중 오류", error);
            throw error;
        });
}
// 찜 해제하기
function deleteWish(planId){
    return fetch(`/ranking/wish?planId=${planId}`, {
        method: "PUT",
        body: planId
    }).then(response => {
        if (!response.ok){
            console.error(" 찜 등록 해체 처리하는 중 오류", error);
            throw error;
        }
    })
        .catch(error => {
            console.error(" 찜 등록해제를 요청하는 중 오류", error);
            throw error;
        });
}