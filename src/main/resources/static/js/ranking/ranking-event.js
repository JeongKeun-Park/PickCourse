// 25.03.22 조승찬 작성

// 가격에 콤마 넣기
document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".plan-price").forEach( e => {
        const planPrice = parseInt(e.textContent.trim(),10);
        e.textContent = planPrice.toLocaleString();
    });
});
//  찜 설정 해지
document.querySelectorAll(".dXNbSn").forEach((button) => {
    button.addEventListener("click", function (event) {
        event.preventDefault();
        event.stopPropagation();

        const img = this.querySelector("img");
        if (img) {
            if(!document.querySelector("#main-header").querySelector(".jfHerU")) {  // 로그인 했을 때만 처리 가능
                const result = confirm("로그인 하시겠습니까 ?");
                if (result){
                    window.location.href="/login/login";
                }
                return;
            }

            // 찜 설정에 필요한 여행 계획(모집) 아이디 가져오기
            const planId = parseInt(this.closest(".lQqkn").querySelector(".planId-container").textContent.trim(), 10);

            if (img.src.includes("icon1.svg")) { // 빨간색일 때 클릭하면 찜 해제, 하얀색일 때 클릭하면 찜 설정
                img.src = "/images/ranking/icon2.svg";
                // 화면내 같은 planId가 있으면 같이 반영
                document.querySelectorAll(".planId-container").forEach( e => {
                    if (parseInt(e.textContent.trim()) == planId) { // 부모계층으로 찾아가서 반영
                        e.closest(".lQqkn").querySelector(".dXNbSn img").src = "/images/ranking/icon2.svg"
                    }
                });

                deleteWish(planId);
            } else {
                img.src = "/images/ranking/icon1.svg";
                // 화면내 같은 planId가 있으면 같이 반영
                document.querySelectorAll(".planId-container").forEach( e => {
                    if (parseInt(e.textContent.trim()) == planId) { // 부모계층으로 찾아가서 반영
                        e.closest(".lQqkn").querySelector(".dXNbSn img").src = "/images/ranking/icon1.svg"
                    }
                });

                postWish(planId);
            }

        }
    });
});


document.body.addEventListener("click", e =>{

    // 이미지 클릭시 여행계획 상세 페이지로 이동
    if (e.target.classList.contains("VUNpu")) {

        // 모집계획 id 가져오기
        const id = parseInt(e.target.closest(".lQqkn").querySelector(".planId-container").textContent.trim(), 10);

        // 폼 요소를 동적으로 생성
        const form = document.createElement("form");
        form.setAttribute("method", "GET");
        form.setAttribute("action", "/proposal/read");  // 정근 모집계획 상세보기 완성 후 수정

        // 파라미터 생성
        let input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("name", "id");
        input.setAttribute("value", id);
        form.appendChild(input);

        // 폼 제출
        document.body.appendChild(form); // 폼을 body에 추가
        form.submit(); // 폼 제출
        document.body.removeChild(form); // 제출 후 폼 삭제

    }

})


