
function postEcoParticipant(courseId, memberId) {
    return fetch (`/proposal/eco/participant?courseId=${courseId}&memberId=${memberId}`, {
        method: "POST",
        headers: {"Content-type": "application/json"},
    })
        .then(response => response.json())
        .then(data => {
            console.log("참가자 처리 메세지 "+data.message);
            alert(data.message);
        })
        .catch(error => {
            console.error("참여자 정보 보내는 중 오류", error);
            throw error;
        });
}