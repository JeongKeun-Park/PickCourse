//2025.03.08  조승찬
// import {createCourseList} from "./course-list-layout-base.js";
// import {addCourseList, addPagination, initSearchForm} from "./course-list-layout-add.js";

function fetchCourseList(page, type, keyWord){
    return fetch(`/admin/course-list?page=${page}&type=${type}&keyWord=${keyWord}`)
        .then(response => response.json())
        .then(data => {
            createCourseList(); // 코스 목록 기초 화면 생성
            addCourseList(data.courses); // 코스목록 추가
            addPagination(data.pagination);  // 페이지 번호 추가
            initSearchForm(data.pagination, data.search); // 다른 화면에 넘겨줄 페이지, 검색조건 초기화
        })
        .catch(error => {
            console.error("course list 데이타를 가져오는 중 오류", error);
            throw error;
        });
};

function updateCourseType(sendData,page,type,keyWord) {
    return fetch("/admin/course-list", {
        method: "PUT",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify(sendData)
    })
        .then (response => {
            if (!response.ok){
                throw new Error("코스타입 데이터 수정에 실패했습니다!");
            }
            console.log("코스타입 수정 데이터 전송 성공!");
            // 전송 성공 후 course list를 다시 가져옴
            fetchCourseList(page, type, keyWord);
        })
        .catch(error => {
            console.error("코스타입 수정 데이터 전송 중 오류:", error);
        });
}

// 메뉴가 클릭되었을 때 화면 로드. admin.js에 있는 함수
document.addEventListener("DOMContentLoaded", function() {
    generateClickedScreens("adminCourseList", function() {
        fetchCourseList(1, '', '')
    });
});
// // 페이지가 로드될 때 코스 목록을 자동으로 로드