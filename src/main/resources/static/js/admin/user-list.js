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
    const userCheckboxes = document.querySelectorAll(".usersCheckbox");

    //전체 선택 체크박스 클릭 시 모든 개별 체크박스 상태 변경
    checkboxAll.addEventListener("change", function () {
        userCheckboxes.forEach((checkbox) => {
            checkbox.checked = checkboxAll.checked;
        });
    });

    //개별 체크박스 변경 시 전체 선택 체크박스 상태 업데이트
    userCheckboxes.forEach((checkbox) => {
        checkbox.addEventListener("change", function () {
            checkboxAll.checked = [...userCheckboxes].every((cb) => cb.checked);
        });
    });

    // 2025.03.03 조승찬 추가
    // 최초 로딩시 라디오 버튼 처리
    // const isActValue =  '[[${search.isAct}]]'; // html문 안에서 설정 됨.
    // 따옴표 제거
    let cleanIsActValue = isActValue.replace(/^"|"$/g, '');
    const radioButtons = document.querySelectorAll('input[name="isAct"]');

    console.log("isActValue 원본:", isActValue, "| ASCII:", isActValue.charCodeAt(0));
    console.log("isActValue 정리 후:", cleanIsActValue, "| ASCII:", cleanIsActValue.charCodeAt(0));
    radioButtons.forEach(radio => {
        console.log("radio.value:", radio.value, "| ASCII:", radio.value.charCodeAt(0));
        if (radio.value == cleanIsActValue) {  // 라디오 버튼이기 때문에 하나만 true 되면 나머지는 false
            radio.checked = true;
            console.log("true    radio.value:", radio.value);
        }
    });

    //회원 정지, 정지 해제, 회원 추방 버튼 클릭시
    function setFormAction(action) {
        const selectedUsers = document.querySelectorAll(".usersCheckbox:checked");
        if (selectedUsers == null || selectedUsers.length == 0){
            alert("체크 박스를 선택 해 주세요!");
            return;
        }
        const selectedIds = Array.from(selectedUsers).map(checkbox => {
            const userDiv = checkbox.closest(".userListDiv");
            const idDiv = userDiv.querySelector(".idDiv");
            return idDiv.textContent.trim();
        });

        document.getElementById("selectedIds").value = selectedIds.join(',');
        document.querySelector("form[name=changeForm]").action = action;
        document.querySelector("form[name=changeForm]").submit();
    }

    document.querySelector(".pauseBtn").addEventListener("click", function (e) {
        e.preventDefault();
        setFormAction('/admin/member-list-pause');
    });

    document.querySelector(".restartBtn").addEventListener("click", function (e) {
        e.preventDefault();
        setFormAction('/admin/member-list-restart');
    });

    document.querySelector(".expelBtn").addEventListener("click", function (e) {
        e.preventDefault();
        setFormAction('/admin/member-list-delete');
    });

    // 2025.03.03 조승찬 추가

});

// 2025.03.05 조승찬 추가
document.addEventListener("DOMContentLoaded", function () {
    // 검색 조건 처리
    document.querySelector("a.search").addEventListener("click", e =>{
        e.preventDefault();
        document.querySelector("form[name=searchForm]").submit();
    })

    // 페이징 처리
    document.querySelector(".pagination-container").addEventListener("click", function (e) {
        const pageLink = e.target.closest(".change-page"); // 가장 가까운 .change-page 요소 찾기
        if (!pageLink) return; // 클릭한 요소가 .change-page가 아니면 무시

        e.preventDefault(); // 기본 이벤트 막기

        const pageValue = pageLink.getAttribute("href"); // href 값 가져오기
        if (pageValue) {
            // document.querySelector("input[name=page]").value = pageValue; // input[name=page] 값 변경
            document.querySelector(".adminList").value = pageValue;
            document.forms["searchForm"].submit(); // 폼 제출
        }
    });

});
// 2025.03.05 조승찬 추가

