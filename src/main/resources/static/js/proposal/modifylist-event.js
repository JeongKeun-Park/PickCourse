planService.getList(planLayout.showList);

const buttonContainer = document.querySelector(".button-container");
const listWrap = document.querySelector(".list-wrap");

// 추가버튼 이벤트
buttonContainer.addEventListener("click", (e) => {
        if(e.target.tagName === "BUTTON") {
            planService.getList(planLayout.showList, e.target.getAttribute("data-index"))
        }
});

// 수정 삭제 모달창 처리
listWrap.addEventListener("click", (e) => {
    if (e.target.classList.contains("FvtMb")) {
        document.querySelector("#modal-root").style.display = "flex";

        // targetPlanId 전역 저장
        window.targetPlanId = e.target.getAttribute("data-index");

        // 모달 닫기 이벤트 (이벤트 리스너를 중첩해서 등록하지 않도록 수정)
        document.querySelector(".eCzJv").addEventListener("click", closeModal);

        // 모달 내용 업데이트
        updateModal();
    }
});

// 모달 닫기 기능
function closeModal() {
    document.querySelector("#modal-root").style.display = "none";
}

// 모달창 업데이트 (targetPlanId 최신 값 반영)
function updateModal() {
    const modalWrap = document.querySelector(".modal-wrap");

    // 최신 targetPlanId 적용
    let text = `
        <a href="/proposal/modify?id=${window.targetPlanId}" class="Default__Menu-sc-7insrf-1 cUlkXY">
            <div style="padding: 1px 6px">수정하기</div>
        </a>
        <form action="/proposal/delete" method="post" name="delete-form">
            <a href="/proposal/delete?id=${window.targetPlanId}" class="Default__Menu-sc-7insrf-1 cUlkXY">
                <div style="padding: 1px 6px">삭제하기</div>
            </a>
        </form>
    `;

    modalWrap.innerHTML = text;
}


