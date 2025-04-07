// 2025.03.15 조승찬

document.addEventListener("DOMContentLoaded", function () {

    let noticeIdDiv, adminIdDiv; // 상세 조회, 수정, 삭제시 사용할 공지 아이디
    document.body.addEventListener("click", e => {

        // 페이지 번호 클릭시 해당 페이지 조회 요청
        const pageLink = e.target.closest(".notice-change-page");
        if (pageLink) {   // 페이지 이동 처리
            console.log("페이지 이동요청 들어옴");
            e.preventDefault(); // 기본 이벤트 막기

            const pageValue = pageLink.dataset.page;
            const typeValue = document.querySelector('select[name="type"]').value;
            const keyWordValue = document.querySelector('input[name="keyWord"]').value;
            fetchNoticeList(pageValue, typeValue, keyWordValue);
        }

        // 더보기 클릭시 글자제한 해제
        if (e.target.className == "noticeMore"){
            e.target.previousElementSibling.classList.toggle("expanded");
            e.target.textContent = e.target.previousElementSibling.classList.contains('expanded') ? '접기' : '더보기';
        }

        // 검색 클릭시 검색 요청
        if (e.target.classList.contains("fa-noticeSearch")) {

            e.preventDefault(); // 기본 이벤트 막기
            const pageValue = 1;   // 검색 조회시는 1페이지부터 조회
            const typeValue = document.querySelector('select[name="type"]').value;
            const keyWordValue = document.querySelector('input[name="keyWord"]').value;
            fetchNoticeList(pageValue, typeValue, keyWordValue);  // type 값은 오류방지를 위해 빈문자열로 보냄
        }

        // 각 목록(noticeListDiv) 클릭시 댓글이나 피드 상세보기 제공
        if (e.target.className == "noticeListDiv" || e.target.closest(".noticeListDiv")) {
            if (e.target.tagName == "BUTTON"){
                return;
            }
            e.preventDefault(); // 기본 이벤트 막기

            const clickedElement = e.target.classList.contains("noticeListDiv") ? e.target : e.target.closest(".noticeListDiv");
            noticeIdDiv = clickedElement.querySelector(".noticeIdDiv").textContent.trim();
            adminIdDiv = clickedElement.querySelector(".adminIdDiv").textContent.trim();

            if (noticeIdDiv) {
                fetchNoticeDetail(noticeIdDiv);
            } else {
                console.warn('No noticeIdDiv found !!!');
            }
        }

        // 모달창 x 버튼 클릭시 모달 삭제
        if (e.target.className == "closeNoticeModal") {
            // e.target.classList.remove("clicked");
            document.querySelector(".admin-modal-body").innerHTML = ``;
            document.querySelector(".admin-modal-body").style.display = "none";
        }

        // 공지등록 버튼 클릭시 모달창으로 등록화면 제공
        if (e.target.className == "noticeRegistBtn"){
            createNoticeInput();
        }

        // 모달창 확인 버튼 클릭시 공지 등록
        if (e.target.className == "noticeConfirmBtn"){
            const sendData = {
                noticeTitle   : document.querySelector(".noticeModal-TitleInput").value.trim(),
                noticeContent : document.querySelector(".noticeModal-ContentInput").value.trim()
            }

            insertNoticeDetail(sendData);

            // 모달창 없앰
            document.querySelector(".admin-modal-body").innerHTML = ``;
            document.querySelector(".admin-modal-body").style.display = "none";

        }

        // 모달창의 수정버튼 클릭시
        if (e.target.className == "noticeUpdateBtn") {
            const sendData = {
                id            : noticeIdDiv,
                noticeTitle   : document.querySelector(".noticeModal-TitleInput").value.trim(),
                noticeContent : document.querySelector(".noticeModal-ContentInput").value.trim(),
                adminId       : adminIdDiv
            }
            console.log(" sendData  "+sendData);

            e.preventDefault(); // 기본 이벤트 막기
            const pageValue = document.querySelector('input[name="page"]').value;  // update 후 조회시는 조회하던 페이지부터 조회
            const typeValue = document.querySelector('select[name="type"]').value;
            const keyWordValue = document.querySelector('input[name="keyWord"]').value;

            // 모달창 없앰
            document.querySelector(".admin-modal-body").innerHTML = ``;
            document.querySelector(".admin-modal-body").style.display = "none";

            console.log(" pageValue "+pageValue+" "+typeValue+" "+keyWordValue);
            updateNoticeDetail(sendData, pageValue, typeValue, keyWordValue);  // type 값은 오류방지를 위해 빈문자열로 보냄
        }
        // 모달창의 삭제버튼 클릯
        if (e.target.className == "noticeDeleteBtn") {
            e.preventDefault(); // 기본 이벤트 막기
            const pageValue = document.querySelector('input[name="page"]').value;  // update 후 조회시는 조회하던 페이지부터 조회
            const typeValue = document.querySelector('select[name="type"]').value;
            const keyWordValue = document.querySelector('input[name="keyWord"]').value;

            // 모달창 없앰
            document.querySelector(".admin-modal-body").innerHTML = ``;
            document.querySelector(".admin-modal-body").style.display = "none";

            deleteNoticeList(noticeIdDiv, pageValue, typeValue, keyWordValue);  // type 값은 오류방지를 위해 빈문자열로 보냄
        }

    });

});
