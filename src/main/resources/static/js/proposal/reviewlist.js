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
document.querySelector(".close_1x3s325").addEventListener("click", () => {
    if (document.querySelector("#modal-root").style.display == "none") {
        document.querySelector("#modal-root").style.display = "";
    } else {
        document.querySelector("#modal-root").style.display = "none";
    }
});
