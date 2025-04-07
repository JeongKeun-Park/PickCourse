//2025.03.15 조승찬
// 공지 목록 생성하는 함수
function addNoticeList(notices, pagination, search) {
    const noticeListContainer = document.querySelector(".noticeList-container");
    noticeListContainer.innerHTML = ``;

    // 리스트 데이터 매핑
    notices.forEach(notice => {
        const noticeRow = document.createElement("li");
        if (notice.noticeTitle.length > 94) {
            noticeRow.innerHTML =
                `<div class="noticeListDiv">
                    <div class="noticeIdDiv"> ${notice.id} </div>
                    <div class="noticeTitleDiv">${notice.noticeTitle}</div>
                    <button class="noticeMore">더보기</button> 
                    <div class="createdDateDiv">${notice.createdDate}</div>
                    <div class="adminNameDiv" > ${notice.adminName} </div>
                    <div class="adminIdDiv" style="display: none;"> ${notice.adminId} </div>
                </div>`;
        } else {
            noticeRow.innerHTML =
                `<div class="noticeListDiv">
                    <div class="noticeIdDiv"> ${notice.id} </div>
                    <div class="noticeTitleDiv">${notice.noticeTitle}</div>
                    <span class="noMore"></span> 
                    <div class="createdDateDiv">${notice.createdDate}</div>
                    <div class="adminNameDiv" > ${notice.adminName} </div>
                    <div class="adminIdDiv" style="display: none;"> ${notice.adminId} </div>
                </div>`;
        }

        noticeListContainer.appendChild(noticeRow);
    });
};

// 페이지 번호 생성하는 함수
function addNoticePagination(pagination) {
    const paginationContainer = document.querySelector(".pagination-container");
    paginationContainer.innerHTML = ""; // 기존 페이지네이션 초기화

    // console.log(" 페이지 정보 "+pagination.startPage+" "+pagination.endPage+" "+pagination.prev+" "+pagination.next);
    // 이전 페이지 버튼 추가
    if (pagination.prev) {
        const prevLink = document.createElement("a");
        prevLink.classList.add("notice-change-page");
        // prevLink.href = `${pagination.startPage - 1}`;
        prevLink.href = "#";  // 클릭 시 기본 이동 방지
        prevLink.dataset.page = pagination.startPage - 1;
        prevLink.innerHTML = `
            <button class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 iItkLq">
                <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E" 
                     class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns" />
            </button>
        `;
        paginationContainer.appendChild(prevLink);
    }

    // 페이지 번호 버튼 추가
    for (let i = pagination.startPage; i <= pagination.endPage; i++) {
        if (pagination.page !== i) {
            const pageLink = document.createElement("a");
            pageLink.classList.add("notice-change-page");
            // pageLink.href = `${i}`;
            pageLink.href = "#";  // 클릭 시 기본 이동 방지
            pageLink.dataset.page = i;  // 페이지 번호 저장
            pageLink.innerHTML = `<code>${i}</code>`;
            paginationContainer.appendChild(pageLink);
        } else {
            const currentPage = document.createElement("code");
            currentPage.innerText = i;
            paginationContainer.appendChild(currentPage);
        }
    }

    // 다음 페이지 버튼 추가
    if (pagination.next) {
        const nextLink = document.createElement("a");
        nextLink.classList.add("notice-change-page");
        // nextLink.href = `${pagination.endPage + 1}`;
        nextLink.href = "#";  // 클릭 시 기본 이동 방지
        nextLink.dataset.page = pagination.endPage + 1;
        nextLink.innerHTML = `
            <button class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 iItkLq">
                <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E" 
                     class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu" />
            </button>
        `;
        paginationContainer.appendChild(nextLink);
    }
}

// 공지 상세 내역을 담아와서 모달 창으로 보여주기
function noticeModal(notice) {

    document.querySelector(".admin-modal-body").innerHTML = `
        <div class="notice-modal">
            <div class="modal-header">
                <span> 공지 상세 </span>
                <span class="closeNoticeModal">&times;</span>
            </div>
            <div class="notice-container">
                <div class="notice-title-container border-box">
                    <div class="noticeModal-TitleDiv">Title</div>
                    <textarea class="noticeModal-TitleInput">${notice.noticeTitle}</textarea>
                </div>
                <div class="notice-content-container border-box">
                    <div class="noticeModal-ContentDiv">Contents</div>
                    <textarea class="noticeModal-ContentInput">${notice.noticeContent}</textarea>
                </div>
                <div class="notice-button-container">
                    <button class="noticeUpdateBtn">공지 수정</button>
                    <button class="noticeDeleteBtn">공지 삭제</button>
                </div>
            </div>
        </div>`;
    document.querySelector(".admin-modal-body").style.display = "flex";

}

function createNoticeInput() {

    document.querySelector(".admin-modal-body").innerHTML = `
        <div class="notice-modal">
            <div class="modal-header">
                <span> 공지 상세 </span>
                <span class="closeNoticeModal">&times;</span>
            </div>
            <div class="notice-container">
                <div class="notice-title-container border-box">
                    <div class="noticeModal-TitleDiv">Title</div>
                    <textarea class="noticeModal-TitleInput"></textarea>
                </div>
                <div class="notice-content-container border-box">
                    <div class="noticeModal-ContentDiv">Contents</div>
                    <textarea class="noticeModal-ContentInput"></textarea>
                </div>
                <div class="notice-button-container">
                    <button class="noticeConfirmBtn">확  인</button>
                </div>             
            </div>
        </div>`;
    document.querySelector(".admin-modal-body").style.display = "flex";

}