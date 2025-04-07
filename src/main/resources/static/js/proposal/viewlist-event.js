const button = document.querySelector(".pay-button")

button.addEventListener("click", () =>{
    if(member != null) {
    document['viewlist-form'].submit();
    } else {
        alert("로그인 후 작성해주세요")
        return "/login/login"
    }
})

// 후기의 버튼 클릭시 이미지 3개식 이동 732px, 디스플레이 768px

let leftReviewEnd = 0; // 화면 왼쪽 끝
let rightReviewEnd = 976;
const totalLength = slickTrack.children;
let maxReviewRightEnd =totalLength.length * 244;

slickInitialized.addEventListener("click", (e) => {
    if(e.target.classList.contains("slick-next")){
        rightReviewEnd += 732;
        leftReviewEnd += 732;
        if (rightReviewEnd > maxReviewRightEnd) {
            // maxRightEnd 보다 오른쪽으로 못 가도록 막음
            leftReviewEnd -= rightReviewEnd - maxReviewRightEnd;
            rightReviewEnd = maxReviewRightEnd;

            document.querySelector(".slick-next").classList.add("slick-disabled");
        }
        document.querySelector(".slick-track").style.transform = `translate3d(${768 - rightReviewEnd}px, 0, 0)`;
        document.querySelector(".slick-prev").classList.remove("slick-disabled");
    }
    if(e.target.classList.contains("slick-prev")){
        leftReviewEnd -= 732;
        rightReviewEnd -= 732;
        if (leftReviewEnd < 0) {
            // 0 보다 오른쪽으로 못 가도록 막음
            rightReviewEnd -= leftReviewEnd;
            leftReviewEnd = 0;

            document.querySelector(".slick-prev").classList.add("slick-disabled");
        }
        document.querySelector(".slick-track").style.transform = `translate3d(${leftReviewEnd}px, 0, 0)`;
        document.querySelector(".slick-next").classList.remove("slick-disabled");
    }
})


// 후기의 버튼 클릭시 이미지 3개식 이동 732px, 디스플레이 768px