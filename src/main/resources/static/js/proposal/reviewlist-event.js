reviewlistService.getLists(lists.planId,reviewLayout.showList);


const buttonContainer = document.querySelector(".button-container");
const reviewWrap = document.querySelector(".review-wrap")
const lightboxBackdrop = document.querySelector("#lightboxBackdrop > div");


// 추가버튼 이벤트
buttonContainer.addEventListener("click", (e) => {
    if(e.target.tagName === "BUTTON") {
        reviewlistService.getLists(lists.planId, reviewLayout.showList, e.target.getAttribute("data-index"))
    }
});

reviewWrap.addEventListener("click", (e) => {
    if(e.target.classList.contains("VUNpu")){
        // 기본 정보 취득
        // 화면의 갯수
        const imgCount = e.target.closest(".dbtIvu").querySelectorAll(".gDuKGF").length;
        // 클릭된 화면의 인덱스
        var currentIndex = Array.from(e.target.closest(".dbtIvu").querySelectorAll(".gDuKGF")).indexOf(
            e.target
        );
        // 화면들의 src
        const imgArray = Array.from(e.target.closest(".dbtIvu").querySelectorAll(".VUNpu")).map(
            (img) => img.src
        );

        // 해당 img src 설정
        document.querySelector(".figure_10ki57k img").src = imgArray[currentIndex+1];
        document.querySelector("#modal-root").style.display = "";
        // 현재 화면 위치/총 화면 수
        document.querySelector(".footerCount_lkhc9u").textContent = `${currentIndex + 2}/${imgCount}`;

        // 버튼 생성
        createButton(currentIndex, imgCount);

        // 이벤트 위임
        lightboxBackdrop.addEventListener("click", (event) => {
            if (
                event.target.matches(
                    ".arrow_b9bbag-o_O-arrow__direction__left_shhpn5-o_O-arrow__size__medium_9f7hgo > span > svg"
                )
            ) {
                moveLeft();
            } else if (
                event.target.matches(
                    ".arrow_b9bbag-o_O-arrow__direction__right_174p6a9-o_O-arrow__size__medium_9f7hgo > span > svg"
                )
            ) {
                moveRight();
            }

            function moveLeft() {
                if (currentIndex > 0) {
                    currentIndex--;
                    document.querySelector(".figure_10ki57k img").src = imgArray[currentIndex];
                    document.querySelector(".footerCount_lkhc9u").textContent = `${currentIndex + 1}/${imgCount}`;
                    createButton(currentIndex, imgCount);
                }
            }

            function moveRight() {
                if (currentIndex < imgCount - 1) {
                    currentIndex++;
                    document.querySelector(".figure_10ki57k img").src = imgArray[currentIndex];
                    document.querySelector(".footerCount_lkhc9u").textContent = `${currentIndex + 1}/${imgCount}`;
                    createButton(currentIndex, imgCount);
                }
            }
        });
    }
})

function createButton(currentIndex, imgCount) {
    // 존재하면 왼쪽 버튼 삭제
    if (document.querySelector(".arrow_b9bbag-o_O-arrow__direction__left_shhpn5-o_O-arrow__size__medium_9f7hgo")) {
        document
            .querySelector(".arrow_b9bbag-o_O-arrow__direction__left_shhpn5-o_O-arrow__size__medium_9f7hgo")
            .remove();
    }
    // 존재하면 오른쪽 버튼 삭제
    if (document.querySelector(".arrow_b9bbag-o_O-arrow__direction__right_174p6a9-o_O-arrow__size__medium_9f7hgo")) {
        document
            .querySelector(".arrow_b9bbag-o_O-arrow__direction__right_174p6a9-o_O-arrow__size__medium_9f7hgo")
            .remove();
    }

    // 버튼 생성
    if (imgCount > 1) {
        if (0 < currentIndex && currentIndex < imgCount - 1) {
            // 양쪽 버튼 생성
            document.querySelector("#lightboxBackdrop > div").appendChild(leftButton);
            document.querySelector("#lightboxBackdrop > div").appendChild(rightButton);
        } else if (currentIndex == 0) {
            // 오른쪽 버튼만 생성
            document.querySelector("#lightboxBackdrop > div").appendChild(rightButton);
        } else if (currentIndex == imgCount - 1) {
            // 왼쪽 버트만 생성
            document.querySelector("#lightboxBackdrop > div").appendChild(leftButton);
        }
    }
}

// 모달 X 버튼 클릭시 동작
const Xbutton = document.querySelector(".container_9tizg4");
Xbutton.addEventListener("click", (e) => {
    if(e.target.classList.contains("close_1x3s325")){
        if (document.querySelector("#modal-root").style.display == "none") {
            document.querySelector("#modal-root").style.display = "";
        } else {
            document.querySelector("#modal-root").style.display = "none";
        }
    }
})