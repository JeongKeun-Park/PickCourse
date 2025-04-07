// 2025.03.14 조승찬

document.addEventListener("DOMContentLoaded", function () {

    document.body.addEventListener("click", e => {

        // 페이지 번호 클릭시 해당 페이지 조회 요청
        const pageLink = e.target.closest(".report-change-page");
        if (pageLink) {   // 페이지 이동 처리
            console.log("페이지 이동요청 들어옴");
            e.preventDefault(); // 기본 이벤트 막기

            // const pageValue = pageLink.getAttribute("href");
            const pageValue = pageLink.dataset.page;
            const typeValue = document.querySelector('select[name="type"]').value;
            const keyWordValue = document.querySelector('input[name="keyWord"]').value;
            const isAct = document.querySelector('input[name="isAct"]:checked').parentElement.textContent.trim();
            fetchReportList(pageValue, '', keyWordValue, isAct); // type 값은 오류방지를 위해 빈문자열로 보냄
        }

        // 더보기 클릭시 글자제한 해제
        if (e.target.className == "reportMore"){
            console.log("더보기 "+e.target.previousElementSibling)
            e.target.previousElementSibling.classList.toggle("expanded");
            e.target.textContent = e.target.previousElementSibling.classList.contains('expanded') ? '접기' : '더보기';
        }

        // 검색 클릭시 검색 요청
        if (e.target.classList.contains("fa-reportSearch")) {

            e.preventDefault(); // 기본 이벤트 막기
            const pageValue = 1;   // 검색 조회시는 1페이지부터 조회
            const typeValue = document.querySelector('select[name="type"]').value;
            const keyWordValue = document.querySelector('input[name="keyWord"]').value;
            const isAct = document.querySelector('input[name="isAct"]:checked').parentElement.textContent.trim(); // 전체, 피드, 댓글
            console.log("검색 전송시 isAct  "+isAct);
            fetchReportList(pageValue, ``,keyWordValue, isAct);  // type 값은 오류방지를 위해 빈문자열로 보냄
        }

        // 각 목록(reportListDiv) 클릭시 댓글이나 피드 상세보기 제공
        if (e.target.className == "reportListDiv" || e.target.closest(".reportListDiv")) {
            if (e.target.tagName == "BUTTON"){
                return;
            }
            e.preventDefault(); // 기본 이벤트 막기

            const clickedElement = e.target.classList.contains("reportListDiv") ? e.target : e.target.closest(".reportListDiv");
            console.log("clickedElement  "+clickedElement+" clickedElement.querySelector(.sourceDiv)"+clickedElement.querySelector(".sourceDiv"));
            const sourceDiv     = clickedElement.querySelector(".sourceDiv").textContent.trim();
            const reportedIdDiv = clickedElement.querySelector(".reportedIdDiv").textContent.trim();

            if (sourceDiv && reportedIdDiv) {
                fetchReportDetail(sourceDiv, reportedIdDiv);
            } else {
                console.warn('No sourceDiv or No reportedIdDiv found !!!');
            }
        }

        // 모달창 x 버튼 클릭시 모달 삭제
        if (e.target.className == "closeReportModal") {
            // e.target.classList.remove("clicked");
            document.querySelector(".admin-modal-body").innerHTML = ``;
            document.querySelector(".admin-modal-body").style.display = "none";
        }

    });

});
