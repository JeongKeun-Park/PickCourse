// let menuBtn = document.querySelector(".AppLayout_expandNavButton__NTEwM");
// let nav = document.querySelector(".AppNavbarLayout_container__NmY5O");
//
// menuBtn.addEventListener("click", function () {
//     nav.classList.toggle("active");
// });
//
// let div = document.querySelector(".AppLayout_contents__YmI3N");
// menuBtn.addEventListener("click", function () {
//     div.classList.toggle("active");
// });
//
// menuBtn.addEventListener("click", function () {
//     menuBtn.classList.toggle("active");
// });

document.addEventListener("DOMContentLoaded", function () {
    const checkboxAll = document.querySelector(".checkboxall");
    const userListContainer = document.querySelector(
        ".NoticePage__NoticeListWrapper"
    );

    //"전체 선택" 체크박스 클릭 시 모든 개별 체크박스 상태 변경
    checkboxAll.addEventListener("change", function () {
        const userCheckboxes = document.querySelectorAll(".usersCheckbox"); // 최신 체크박스 목록 가져오기
        userCheckboxes.forEach((checkbox) => {
            checkbox.checked = checkboxAll.checked;
        });
    });

    //체크박스 상태 변경 감지 (이벤트 위임)
    userListContainer.addEventListener("change", function (event) {
        if (event.target.classList.contains("usersCheckbox")) {
            updateCheckboxAllState();
        }
    });

    function updateCheckboxAllState() {
        const userCheckboxes = document.querySelectorAll(".usersCheckbox");
        checkboxAll.checked =
            userCheckboxes.length > 0 &&
            [...userCheckboxes].every((cb) => cb.checked);
    }

    const manageBtn = document.querySelector(".manageBtn");
    const deleteBtn = document.querySelector(".deleteBtn");
    const inputRow = document.querySelector(".inputRow");
    const saveBtn = document.querySelector(".saveBtn1");
    const cancelBtn = document.querySelector(".cancelBtn");

    //"관리자 등록" 버튼 클릭 시 입력창 표시
    manageBtn.addEventListener("click", function () {
        inputRow.classList.remove("hidden");
    });

    // "취소" 버튼 클릭 시 입력창 숨김
    cancelBtn.addEventListener("click", function () {
        inputRow.classList.add("hidden");
    });

    //"저장" 버튼 클릭 시 입력값을 리스트에 추가
    saveBtn.addEventListener("click", function () {
        const account = document.querySelector(".inputAccount").value.trim();
        const name = document.querySelector(".inputName").value.trim();
        const phone = document.querySelector(".inputPhone").value.trim();
        const email = document.querySelector(".inputEmail").value.trim();

        if (!account || !name || !phone || !email) {
            alert("모든 정보를 입력해주세요!");
            return;
        }

        document.forms["inputForm"].submit();
    });

    // 2025.03.02 조승찬 추가
    // "관리자 추방" 버튼 클릭 시 체크된 항목 삭제
    deleteBtn.addEventListener("click", function (e) {
        e.preventDefault(); // 기본 폼 제출 막기

        const selectedUsers = document.querySelectorAll(
            ".usersCheckbox:checked"
        );

        if (selectedUsers.length === 0) {
            alert("삭제할 사용자를 선택해주세요!");
            return;
        }

        const selectedIds = Array.from(selectedUsers).map(checkbox => {
            // 체크박스의 부모 요소에서 idDiv 값을 찾습니다.
            const userDiv = checkbox.closest('.userListDiv');
            const idDiv = userDiv.querySelector('.idDiv');
            return idDiv.textContent.trim();
        });

        document.getElementById("selectedIds").value = selectedIds.join(',');
        document.querySelector("form[name=deleteForm]").submit();

    });
    // 2025.03.02 조승찬 추가
    // "관리자 추방" 버튼 클릭 시 체크된 항목 삭제
});

// 2025.03.02 조승찬 추가
// 검색 조건
document.querySelector("a.search").addEventListener("click", e =>{
    e.preventDefault();
    document.querySelector("form[name=searchForm]").submit();
})

// 페이징 처리
document.addEventListener("DOMContentLoaded", function () { // HTML이 로드된 후 실행되도록 보장
    document.querySelector(".pagination-container").addEventListener("click", function (e) {
        const pageLink = e.target.closest(".change-page"); // 가장 가까운 .change-page 요소 찾기
        if (!pageLink) return; // 클릭한 요소가 .change-page가 아니면 무시

        e.preventDefault(); // 기본 이벤트 막기

        const pageValue = pageLink.getAttribute("href"); // href 값 가져오기
        if (pageValue) {
            e.preventDefault(); // 기본 동작 방지
            document.querySelector(".adminList").value = pageValue;
            document.forms["searchForm"].submit(); // 폼 제출
        }
    });
});
// 2025.03.02 조승찬 추가