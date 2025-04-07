// 페이지 로드 시 모든 찜 버튼 초기 이미지 설정
document.addEventListener("DOMContentLoaded", function () {
    const buttonImgs = document.querySelectorAll(".enp_mobon_cart img");
    if (!buttonImgs.length) return;

    if (typeof isWished !== "undefined") {
        window.isWished = isWished;

        buttonImgs.forEach(img => {
            img.src = isWished
                ? "/images/main/red.svg"
                : "/images/main/white.svg";
        });
    }
});

// 찜 버튼 클릭 시 처리 로직
document.addEventListener("click", function (e) {
    const wishButton = e.target.closest(".enp_mobon_cart");
    if (!wishButton) return;
    e.preventDefault();

    const buttonImg = wishButton.querySelector("img");

    const messageDiv = document.querySelector(".Bottom__Wrapper-sc-1nltrn7-0") || (() => {
        const div = document.createElement("div");
        div.className = "Bottom__Wrapper-sc-1nltrn7-0 bpzBFC";
        document.querySelector(".ifNxJR")?.appendChild(div);
        return div;
    })();

    console.log("찜 버튼 클릭됨");

    if (typeof memberId === "undefined" || !memberId) {
        alert("로그인 후 사용 가능합니다.");
        window.location.href = "/login/login";
        return;
    }

    if (window.isProcessing) return;
    window.isProcessing = true;

    const payload = {
        planId: planId,
        memberId: memberId
    };

    const url = window.isWished
        ? `/my-page/deleteHeart`
        : `/my-page/insertHeart`;

    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    })
        .then(res => {
            if (!res.ok) throw new Error("서버 응답 오류");
            return res.text();
        })
        .then(msg => {
            window.isWished = !window.isWished;

            // 버튼 2개 다 상태 반영
            document.querySelectorAll(".enp_mobon_cart img").forEach(img => {
                img.src = window.isWished
                    ? "/images/main/red.svg"
                    : "/images/main/white.svg";
            });

            document.querySelectorAll(".enp_mobon_cart .wish-count").forEach(span => {
                let count = parseInt(span.textContent);
                span.textContent = window.isWished ? count + 1 : count - 1;
            });

            // 메시지 표시
            messageDiv.textContent = window.isWished
                ? "추천 여행을 찜했습니다."
                : "찜을 해제했습니다.";
            messageDiv.style.opacity = "0.9";
            messageDiv.style.zIndex = 9999;
            setTimeout(() => {
                messageDiv.style.opacity = "0";
                messageDiv.style.zIndex = -9999;
            }, 1000);
        })
        .catch(err => {
            console.error("에러 발생:", err);
            alert("서버 요청 중 오류가 발생했습니다.");
        })
        .finally(() => {
            window.isProcessing = false;
        });
});


function commentresize(textarea) {
    textarea.style.height = "auto";
    textarea.style.height = textarea.scrollHeight + "px";
}

// 모이는 장소 :: 카카오맵 처리하기
let address = planDetail.plan.planStartAddress
let geocoder = new kakao.maps.services.Geocoder();
geocoder.addressSearch(address, (result, status) => {
    if (status === kakao.maps.services.Status.OK) {
        let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        let mapContainer = document.getElementById("map"), // 지도를 표시할 div
            mapOption = {
                center: coords,
                level: 3, // 지도의 확대 레벨
            };
        // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        let map = new kakao.maps.Map(mapContainer, mapOption);
        // 결과값으로 받은 위치를 마커로 표시합니다
        let marker = new kakao.maps.Marker({
            map: map,
            position: coords,
        });
        // map.relayout();
        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);

        // 화면 확장 축소
        document.querySelector("#fullMap").addEventListener("click", (e) => {
            if (mapContainer.style.position === "fixed") {
                mapContainer.style.position = "relative";
                mapContainer.style.width = "100%";
                mapContainer.style.height = "30vh";
                mapContainer.style.zIndex = "";
                document.querySelector("#fullMap").style.position = "absolute";
            } else {
                mapContainer.style.position = "fixed";
                mapContainer.style.top = "0";
                mapContainer.style.left = "0";
                mapContainer.style.width = "100%";
                mapContainer.style.height = "100vh";
                mapContainer.style.zIndex = "1000"; // 맵이 다른 요소 위에 오도록 설정
                document.querySelector("#fullMap").style.position = "fixed";
            }
            map.relayout();
            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        });
        // 화면 확장 축소
    }
});
// 모이는 장소 :: 카카오맵 처리하기

// 위로 버튼 누르면 화면 위쪽으로 천천히 이동
document.querySelector(".gQlhwK").addEventListener("click", (e) => {
    window.scrollTo({
        top: 0,
        behavior: "smooth",
    });
});
// 위로 버튼 누르면 화면 위쪽으로 천천히 이동


