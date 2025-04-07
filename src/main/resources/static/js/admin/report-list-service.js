//2025.03.08  조승찬
// import {createCourseList} from "./course-list-layout-base.js";
// import {addCourseList, addPagination, initSearchForm} from "./course-list-layout-add.js";

function fetchReportDetail(source, id){
    return fetch(`/admin/report-detail/${id}?source=${source}`)
        .then(response => response.json())
        .then(data => {
            reportModal(data.reply)
        })
        .catch(error => {
            console.error("course list 데이타를 가져오는 중 오류", error);
            throw error;
        });
};
