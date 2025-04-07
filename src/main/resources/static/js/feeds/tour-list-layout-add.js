// 25.03.21 조승찬 작성

function addTourListAndPage(tours, pagination) {
    tours.forEach( tour => {
        const tourAddLine = document.createElement("div");
        tourAddLine.innerHTML = ``;
        tourAddLine.innerHTML = `
            <div>
                <div class="MagazineListPage__MagazineWrapper-hh1ck3-2 jZtIEr">
                    <a class="MagazineListPage__Magazine-hh1ck3-3 hHOLgL" >
                        <div class="Image__Wrapper-v97gyx-0 gDuKGF">
<!--                            <img class="dynamic-img">-->
<!--                            <div class="Fade__Wrapper-sc-1s0ipfq-0 koasSX" style="opacity: 1; display: block" >-->
<!--                                <div class="Ratio" style="display: block">-->
<!--                                    <div class="Ratio-ratio" style=" height: 0px; position: relative; width: 100%; padding-top: 120%; " >-->
<!--                                        <div class="Ratio-content" style=" height: 100%; left: 0px; position: absolute; top: 0px; width: 100%; " >-->
<!--&lt;!&ndash;                                            <img class="dynamic-img">&ndash;&gt;-->
<!--                                        </div>-->
<!--                                    </div>-->
<!--                                </div>-->
<!--                            </div>-->
                        </div>
                        <div>
                            <div class="MagazineListPage__Title-hh1ck3-4 kBOcSr">
                                <span class="planName" >제목 :: ${tour.planName}</span><br />
                                <span class="planSchedule" >일정 :: ${tour.planStartDate}~${tour.planEndDate}</span><br />
                                <span class="planPrice" >비용 :: ${tour.planPrice}</span><br />
                                <span class="planParticipants" >참여 :: ${tour.participants}/${tour.planMaxPersonnel}</span><br />
                                <span class="planId" style="display: none; ">${tour.id}</span><br /> <!-- 후기 작성에 전달할 planId -->
                            </div>
                            <div class="MagazineListPage__CatchPhrase-hh1ck3-5 dfnTnv memberNickname" >캡틴 :: ${tour.memberNickname}</div>
                        </div>
                    </a>
                    <div class="button-container real-feed">
                        <button>리얼후기</button>
                    </div>
                </div>
           </div>
        `;


        // 파일이 존재 할 때만 이미지 태그 추가
        // **tour.planFilePath와 tour.planFileName이 존재하는 경우에만 이미지 추가**
        if (tour.planFilePath && tour.planFileName) {
            const imageUrl = `/files/display?path=${encodeURIComponent(tour.planFilePath)}/${encodeURIComponent(tour.planFileName)}`;

            // <img> 태그 생성
            const imgElement = document.createElement("img");
            imgElement.src = imageUrl;
            imgElement.alt = "서버에서 불러온 이미지";

            // `.Image__Wrapper-v97gyx-0.gDuKGF` 내부에 이미지 추가
            const imageWrapper = tourAddLine.querySelector(".Image__Wrapper-v97gyx-0.gDuKGF");
            if (imageWrapper) {
                imageWrapper.prepend(imgElement); // 가장 위에 추가
            }

            // `.Ratio-content` 내부에도 추가
            const ratioContent = tourAddLine.querySelector(".Ratio-content");
            if (ratioContent) {
                ratioContent.appendChild(imgElement.cloneNode()); // 클론해서 추가
            }
        }


        document.querySelector(".tourList-container").appendChild(tourAddLine); // 새로 생성된 리스트 추가
        tourAddLine.scrollIntoView({ behavior: "smooth", block: "start" });   // 추가된 행들이 처지지 않게 위치 잡아주기
    })

    document.querySelector("footer").innerHTML = ``; // 기존의 더보기 삭제
    if (pagination.next) {  // 다음 페이지가 존재하면 더보기 추가
        document.querySelector("footer").innerHTML = `
        <button class="moreReplyList" value="${pagination.page + 1}">더보기</button>
    `;
    }

    if (e.target.closest(".jPHhJb")) {
        if (e.target.closest(".jPHhJb").classList.contains("no-pointer")) {  // 리얼후기가 아닌 경우 커서 포인터가 생기지 않음 :: 피드 상세보기 없음
            return;
        }
        // 모집계획 id 가져오기
        const id = e.target.closest(".parent-for-id").querySelector(".server-using-planId").textContent.trim();

        // 폼 요소를 동적으로 생성
        const form = document.createElement("form");
        form.setAttribute("method", "GET");
        form.setAttribute("action", "/proposal/read");  // 정근 모집계획 상세보기 완성 후 수정

        // 파라미터 생성 :: 정근씨 모집계획 상세보기와 연결  수정
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
}


// 신고 클릭시 모달창에서 신고 내용 입력
function reportModalCreate(id) {

    document.querySelector(".tour-report-modal-body").innerHTML = `
        <div class="tour-report-modal">
            <div class="modal-header">
                <span> 신고 사유 작성 </span>
                <span class="closeReplyReportModal">&times;</span>
            </div>
            <div class="tour-report-container">
                <div class="tour-report-content-container border-box">
                    <textarea class="tour-reportModal-ContentInput"></textarea>
                </div>
                <div class="tour-report-button-container">
                    <button class="tour-reportConfirmBtn">확  인</button>
                </div>             
            </div>
            <div class="tourIdDiv" style="display: none;" > ${id} </div>
        </div>`;
    document.querySelector(".tour-report-modal-body").style.display = "flex";

}