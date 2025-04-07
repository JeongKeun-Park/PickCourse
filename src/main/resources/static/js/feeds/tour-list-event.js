// 2025.03.21 조승찬 작성

document.body.addEventListener("click", e => {

    // 리얼 후기 작성 클릭
    if (e.target.closest(".real-feed")) {
        const planId = e.target.closest(".jZtIEr").querySelector(".planId").textContent.trim();
        console.log("planId "+planId);
        const form = document.createElement("form");
        form.setAttribute("method", "GET")
        form.setAttribute("action", "/feeds/real-write"); // 파라미터는 input 으로 전달

        let input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("name", "planId");
        input.setAttribute("value", planId);
        form.appendChild(input);

        // 폼 제출
        document.body.appendChild(form); // 폼을 body에 추가
        form.submit(); // 폼 제출

    }

    if (e.target.className == "moreTourList") {
        const page = e.target.value; // 다음 조회할 페이지 설정
        fetchTourListApi(page);
    }

    if (e.target.closest(".MagazineListPage__Magazine-hh1ck3-3.hHOLgL")) {

        // 모집계획 id 가져오기
        const id = e.target.closest(".jZtIEr").querySelector(".planId").textContent.trim();

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