document.addEventListener("DOMContentLoaded", () => {

    let offset = 5;
    const limit = 5;

    const moreButton = document.getElementById("load-more-btn");

    if (moreButton) {
        moreButton.addEventListener("click", async () => {
            try {
                const response = await fetch(`/my-page/recruit/load?offset=${offset}&limit=${limit}`);
                const plans = await response.json();

                if (!Array.isArray(plans) || plans.length === 0) {
                    moreButton.style.display = "none";
                    return;
                }

                const container = document.querySelector(".bScXKt"); // 전체 감싸는 div
                plans.forEach(plan => {
                    const planHtml = `
                    <div class="sc-fimazj-0 gKYVxm">
                        <div class="sc-abukv2-0 kcHmyx">
                            <div class="sc-abukv2-1 kSZYgn">${plan.planCreatedDate}</div>
                            <div class="sc-abukv2-2 kThsCL"></div>
                        </div>
                        <div class="sc-gnmni8-0 elGTUw">
                            <table class="sc-gnmni8-1 eSpcfO">
                                <colgroup>
                                    <col width="600" />
                                    <col width="" />
                                </colgroup>
                                <tbody class="sc-gnmni8-2 hryMPB">
                                    <tr class="sc-gnmni8-3 gmGnuU">
                                        <td class="sc-gnmni8-5 hUzAOG">
                                            <div class="sc-1jiyjbz-0 dGiGeF">
                                                <div class="sc-gnmni8-9 kCcQTc">
                                                    <div class="sc-g8964r-0 oRzGt"></div>
                                                    <div class="sc-9cwg9-1 gLgexz">
                                                        <div class="sc-9cwg9-2 cNiGzR">
                                                        <a href="/proposal/read?id=${plan.planId}">
                                                            <div class="sc-9cwg9-3 eEDOvs">
                                                                <img loading="lazy" width="100%" height="100%"
                                                                    src="/files/display?path=${plan.filePath}/${plan.fileName}"
                                                                        alt="코스 이미지" />
                                                            </div>
                                                            </a>
                                                            <div class="sc-9cwg9-5 bmwSdh">
                                                                <div class="sc-9cwg9-6 jBCCpd">
                                                                    <div class="sc-fxyxvg-0 igPkOG"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td class="sc-gnmni8-6 gbTJl">
                                            <div class="sc-gnmni8-7 bCQoer">
                                                <div class="insertComent">코스명: ${plan.planName}</div>
                                                <div class="insertComent checkPerson">
                                                    참여인원: ${plan.participantCount} / ${plan.maxPersonnel}명
                                                </div>
                                                <button class="sc-1k9quwu-0 hEHzLT sc-gnmni8-8 kiiuoA listButton"
                                                        data-plan-id="${plan.planId}">
                                                    신청자 목록
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="listContainer" style="display: none"></div>
                    </div>
                    `;
                    container.insertAdjacentHTML("beforeend", planHtml);
                });

                offset += limit;

            } catch (error) {
                console.error("더보기 오류:", error);
            }
        });
    }

    // 신청자 목록 버튼 이벤트 (이벤트 위임 방식)
    document.body.addEventListener("click", async function (e) {
        if (!e.target.classList.contains("listButton")) return;

        const button = e.target;
        const planId = button.getAttribute("data-plan-id");
        const listContainer = button.closest(".sc-fimazj-0").querySelector(".listContainer");

        if (listContainer.style.display === "block") {
            listContainer.style.display = "none";
            listContainer.innerHTML = "";
            return;
        }

        try {
            const response = await fetch(`/my-page/recruit/${planId}/applicants`);
            const applicants = await response.json();

            listContainer.innerHTML = "";

            if (applicants.length === 0) {
                listContainer.innerHTML = `
                    <div class="listWrap">
                        <div class="noApplicants">아직 신청자가 없습니다.</div>
                    </div>`;
            } else {
                let html = `
                    <div class="listWrap">
                        <ul class="NoticePage__NoticeListWrapper">
                            <div class="mainUserListDiv">
                                <div class="HeaderaccountDiv">아이디</div>
                                <div class="HeadernameDiv">닉네임</div>
                                <div class="HeaderemailDiv">쪽지</div>
                                <div class="lastDiv"></div>
                            </div>`;

                applicants.forEach((a) => {
                    html += `
                        <li>
                            <div class="userListDiv">
                                <div class="accountDiv"><span>${a.memberEmail}</span></div>
                                <div class="accountDiv"><span>${a.memberNickname}</span></div>
                                <div class="messageButtonWrap">
                                    <button class="messageButton" onclick="location.href='/my-page/messageWrite?receiver=${a.memberEmail}'">쪽지 보내기</button>
                                </div>
                            </div>
                        </li>`;
                });

                html += `</ul></div>`;
                listContainer.innerHTML = html;
            }

            listContainer.style.display = "block";

        } catch (error) {
            console.error("신청자 목록 오류:", error);
        }
    });
});
