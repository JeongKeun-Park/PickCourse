// 25.03.18 조승찬

// 인풋창에 입력된 파일들을 서버로 보내서 경로와 썸네일 만들기
function inputFileUpload(formData){
    return fetch("/files/upload/multi", {
        method: "post",
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            createThumbnail(data.thumbnails);  // json 방식의 메서드로 부터 직접 @GetMapping("/reply-list/{feedId}") 호출하는 방식
        })
        .catch(error => {
            console.error("reply 데이타 등록을 요청하는 중 오류", error);
            throw error;
        });
}