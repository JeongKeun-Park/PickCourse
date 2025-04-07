// 25.03.16 조승찬 작성

function addReplyListAndPage(replys, pagination) {
    replys.forEach( reply => {
        const replyAddLine = document.createElement("div");
        replyAddLine.className = "CurrentIndex__Wrapper-lgen36-0 dSDVFj";
        replyAddLine.innerHTML = ``;
        replyAddLine.innerHTML = `
            <div class="CurrentIndex__Profile-lgen36-1 eqCOjz">
                <div class="Image__Wrapper-v97gyx-0 gDuKGF">
<!--                    <img class="Image__StyledImageLoader-v97gyx-2 bUFcfh" width="36" height="36" src="https://res.cloudinary.com/frientrip/image/upload/c_fill,f_auto,g_center,q_auto/ios_image_391885_20230825111921027_79e3016429b8c2ed57ec60c14e00ba3680af64a25fa7349505f5db3a078839a2"/>-->
                    <div class="Fade__Wrapper-sc-1s0ipfq-0 koasSX" style="opacity: 1; display: block" >
                        <div class="Ratio" style="display: block" >
                            <div class="Ratio-ratio" style=" height: 0px; position: relative; width: 100%; padding-top: 100%; " >
                                <div class="Ratio-content" style=" height: 100%; left: 0px; position: absolute; top: 0px; width: 100%; " >
<!--                                    <img class="Image__StyledImage-v97gyx-1 hPRDQO" width="36" height="36" src="https://res.cloudinary.com/frientrip/image/upload/c_fill,f_auto,g_center,q_auto/ios_image_391885_20230825111921027_79e3016429b8c2ed57ec60c14e00ba3680af64a25fa7349505f5db3a078839a2"/> -->
<!--                                    <img class="Image__StyledImage-v97gyx-1 hPRDQO" width="36" height="36" src="/images/feeds/reply.png"> -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="CurrentIndex__Container-lgen36-2 lcexfU">
                <div class="replyIdDiv" style="display: none;" >${reply.id}</div>
                <div class="CurrentIndex__Name-lgen36-3 bRPQMl" >${reply.memberNickname}</div>
                <p class="CurrentIndex__Contents-lgen36-4 cDlmAG" >${reply.replyContent}</p>
                <section class="ActionMenu__Section-s8lvsh-0 gzpaSl">
                    <p class="ActionMenu__Time-s8lvsh-1 gwAsrs"  >${reply.createdDate}</p>
                    <button class="ActionMenu__TextButton-s8lvsh-2 kosXvh" >${reply.replyAction}</button >
<!--                    <button class="ActionMenu__Like-s8lvsh-3 cOmkks" >-->
<!--                        <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='10' height='9' viewBox='0 0 10 9'%3E %3Cpath fill='none' stroke='%23C6CAD0' d='M7.456.5c-.543 0-1.052.228-1.436.637L5 2.224 3.98 1.136C3.597.728 3.087.5 2.544.5c-.543 0-1.052.228-1.435.637C.701 1.57.5 2.143.5 2.713c0 .565.198 1.132.599 1.565l4.144 3.898 3.648-3.887c.408-.435.609-1.006.609-1.576 0-.57-.201-1.142-.609-1.576C8.508.728 8 .5 7.456.5z'/%3E %3C/svg%3E" class="ActionMenu__HeartImage-s8lvsh-4 deXuYZ" />-->
<!--                        <p class="ActionMenu__LikeText-s8lvsh-5 fgqaUZ" > 좋아요 </p>-->
<!--                    </button>-->
                </section>
            </div>
        `;

        // 파일이 존재 할 때만 이미지 태그 추가
        // **tour.planFilePath와 tour.planFileName이 존재하는 경우에만 이미지 추가**
        if (reply.memberFilePath && reply.memberFileName) {
            const imageUrl = `/files/display?path=${encodeURIComponent(reply.memberFilePath)}/${encodeURIComponent(reply.memberFileName)}`;

            // <img> 태그 생성
            const imgElement = document.createElement("img");
            imgElement.className = "Image__StyledImage-v97gyx-1 hPRDQO";
            imgElement.width = 36;
            imgElement.height = 36;
            imgElement.src = imageUrl;
            imgElement.alt = "서버에서 불러온 이미지";

            // `.Image__Wrapper-v97gyx-0.gDuKGF` 내부에 이미지 추가
            const imageWrapper = replyAddLine.querySelector(".Image__Wrapper-v97gyx-0.gDuKGF");
            if (imageWrapper) {
                imageWrapper.prepend(imgElement); // 가장 위에 추가
            }

            // `.Ratio-content` 내부에도 추가
            const ratioContent = replyAddLine.querySelector(".Ratio-content");
            if (ratioContent) {
                ratioContent.appendChild(imgElement.cloneNode()); // 클론해서 추가
            }
        }

        document.querySelector(".replyList-container").appendChild(replyAddLine); // 새로 생성된 리스트 추가
        replyAddLine.scrollIntoView({ behavior: "smooth", block: "center" }); // 추가된 행들이 처지지 않게 위치 잡아주기
    })

    document.querySelector("footer").innerHTML = ``; // 기존의 더보기 삭제
    if (pagination.next) {  // 다음 페이지가 존재하면 더보기 추가
        document.querySelector("footer").innerHTML = `
        <button class="moreReplyList" value="${pagination.page + 1}">더보기</button>
    `;
    }
}


// 신고 클릭시 모달창에서 신고 내용 입력
function reportModalCreate(id) {

    document.querySelector(".reply-report-modal-body").innerHTML = `
        <div class="reply-report-modal">
            <div class="modal-header">
                <span> 신고 사유 작성 </span>
                <span class="closeReplyReportModal">&times;</span>
            </div>
            <div class="reply-report-container">
                <div class="reply-report-content-container border-box">
                    <textarea class="reply-reportModal-ContentInput"></textarea>
                </div>
                <div class="reply-report-button-container">
                    <button class="reply-reportConfirmBtn">확  인</button>
                </div>             
            </div>
            <div class="replyIdDiv" style="display: none;" > ${id} </div>
        </div>`;
    document.querySelector(".reply-report-modal-body").style.display = "flex";

}