//2025.03.14 조승찬
// 신고 목록 생성하는 함수
function addReportList(reports) {
    const reportListContainer = document.querySelector(".reportList-container");
    reportListContainer.innerHTML = ``;

    // 리스트 데이터 매핑
    reports.forEach(report => {
        const reportRow = document.createElement("li");
        if (report.reportedReason.length > 94) {
            reportRow.innerHTML =
                `<div class="reportListDiv">
                <div class="reportIdDiv" style="display: none;" > ${report.id} </div>   
                <div class="sourceDiv" > ${report.source} </div>                             
                <div class="reportedIdDiv" style="display: none;"> ${report.reportedId} </div>
                <div class="reportedReasonDiv"> ${report.reportedReason}</div>
                <button class="reportMore">더보기</button> 
                <div class="createdDateDiv" > ${report.createdDate} </div>
                <div class="memberNicknameDiv" > ${report.memberNickname} </div>
                <div class="memberIdDiv" style="display: none;"> ${report.memberId} </div>
            </div>`;
        } else {
            reportRow.innerHTML =
                `<div class="reportListDiv">
                <div class="reportIdDiv" style="display: none;" > ${report.id} </div>   
                <div class="sourceDiv" > ${report.source} </div>                             
                <div class="reportedIdDiv" style="display: none;"> ${report.reportedId} </div>
                <div class="reportedReason"> ${report.reportedReason} </div>
                <span class="noMore"></span> 
                <div class="createdDateDiv" > ${report.createdDate} </div>
                <div class="memberNicknameDiv" > ${report.memberNickname} </div>
                <div class="memberIdDiv" style="display: none;"> ${report.memberId} </div>
            </div>`;
        }

        reportListContainer.appendChild(reportRow);
    });
};

// 페이지 번호 생성하는 함수
function addReportPagination(pagination) {
    const paginationContainer = document.querySelector(".pagination-container");
    paginationContainer.innerHTML = ""; // 기존 페이지네이션 초기화

    // console.log(" 페이지 정보 "+pagination.startPage+" "+pagination.endPage+" "+pagination.prev+" "+pagination.next);
    // 이전 페이지 버튼 추가
    if (pagination.prev) {
        const prevLink = document.createElement("a");
        prevLink.classList.add("report-change-page");
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
            pageLink.classList.add("report-change-page");
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
        nextLink.classList.add("report-change-page");
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

// 검색 폼을 초기화하는 함수
function initReportSearchForm(pagination, search) {
    document.querySelector('input[name="page"]').value = pagination.page;
    document.querySelector('select[name="type"]').value = search.type;
    document.querySelector('input[name="keyWord"]').value = search.keyWord;

    if ((search.isAct == ``) || (search.isAct == null)) {search.isAct = "전체"};
    document.querySelectorAll('input.isAct').forEach(radio => {
        if (radio.parentElement.textContent.trim() == search.isAct) {
            radio.checked = true; // "전체" 라벨인 버튼 체크
        }
    });
}

// 신고 상세 내역을 담아와서 모달 창으로 보여주기
function reportModal(reply) {

    document.querySelector(".admin-modal-body").innerHTML = `
        <div class="report-modal">
            <div class="modal-header">
                <span> 신고 된 내용 상세 </span>
                <span class="closeReportModal">&times;</span>
            </div>
            <div class="title">
                <div class="reportModal-sourceDiv">구    분 ::&nbsp;&nbsp;${reply.source}</div>
                <div class="reportModal-memberNicknameDiv">작성자 ::&nbsp;&nbsp;${reply.memberNickname}</div>
                <div class="reportModal-createdDiv">작성일 ::&nbsp;&nbsp;${reply.createdDate}</div>
                <div class="reportModal-introduce">내    용 ::</div>
            </div>
            <div class="reportModal-contentDiv">${reply.content}</div>
        </div>`;
    document.querySelector(".admin-modal-body").style.display = "flex";

}