document.addEventListener("DOMContentLoaded", function () {
    fetch("/my-page/payContents")
        .then((res) => res.json())
        .then((data) => {
            const wrapper = document.getElementById("pay-list-wrapper");
            if (!data || data.length === 0) {
                wrapper.innerHTML = `
                    <div class="Placeholder__Wrapper-jvh9ey-0 kVYyVO">
                        <div class="Placeholder__Wrapper-von7t2-0 gjaxFh">
                            <img src="/images/nonpay.jpg" alt="아직 결제내역이 없어요!" width="56px" height="56px" style="border-radius:100%"/>
                            <div class="Placeholder__Title-von7t2-1 dqGjWH">아직 결제내역이 없어요!</div>
                            <div class="Placeholder__SubTitle-von7t2-2 gSjDbY">지금 바로 플랜을 시작해보세요.</div>
                            <div class="Placeholder__ButtonWrapper-von7t2-3 hCwBUo">
                                <a href="/" class="Primary__Button-sc-5uqlgg-0 jjvlFY">홈으로 이동</a>
                            </div>
                        </div>
                    </div>
                `;
                return;
            }

            // 날짜별로 그룹핑
            const grouped = {};
            data.forEach((item) => {
                const date = item.payDate; // 예: "2025. 01. 22"
                if (!grouped[date]) grouped[date] = [];
                grouped[date].push(item);
            });

            // 날짜별로 그룹 렌더링
            Object.keys(grouped).forEach((date) => {
                const list = grouped[date];
                const groupHTML = `
                    <div class="sc-fimazj-0 gKYVxm">
                        <div class="sc-abukv2-0 kcHmyx">
                            <div class="sc-abukv2-1 kSZYgn">${date}</div>
                            <div class="sc-abukv2-2 kThsCL"></div>
                        </div>
                        <div class="sc-gnmni8-0 elGTUw">
                            <table class="sc-gnmni8-1 eSpcfO">
                                <colgroup>
                                    <col width="600" />
                                    <col width="" />
                                </colgroup>
                                <tbody class="sc-gnmni8-2 hryMPB">
                                    ${list.map((pay) => {
                    const imagePath = `/files/display?path=${encodeURIComponent(pay.courseFilePath + '/' + pay.courseFileName)}`;
                    return `
                                        <tr class="sc-gnmni8-3 gmGnuU">
                                            <td class="sc-gnmni8-5 hUzAOG">
                                                <div class="sc-1jiyjbz-0 dGiGeF">
                                                    <div class="sc-gnmni8-9 kCcQTc">
                                                        <div class="sc-g8964r-0 oRzGt"></div>
                                                        <div class="sc-9cwg9-1 gLgexz">
                                                            <div class="sc-9cwg9-2 cNiGzR">
                                                                <div class="sc-9cwg9-3 eEDOvs">
                                                                    <img loading="lazy" width="100%" height="100%"
                                                                        src="${imagePath}"
                                                                        alt="${pay.courseFileName || '코스 이미지'}" />
                                                                </div>
                                                                <div class="sc-9cwg9-5 bmwSdh">
                                                                    <div class="sc-9cwg9-6 jBCCpd"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td class="sc-gnmni8-6 gbTJl">
                                                <div class="sc-gnmni8-7 bCQoer">
                                                    <div class="insertComent">코스명: ${pay.planName}</div>
                                                    <div class="insertComent">결제 금액: ${pay.payPrice.toLocaleString()} 원</div>
                                                    <div class="insertComent">참여형태: ${pay.courseType}</div>
                                                    <div class="insertComent">참여인원: ${pay.participantNumber}명</div>
                                                    <button class="sc-1k9quwu-0 hEHzLT sc-gnmni8-8 kiiuoA buttonCancel"
                                                            data-plan-id="${pay.planId}">
                                                        결제 취소 / 환불
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                        `;
                }).join('')}
                                </tbody>
                            </table>
                        </div>
                    </div>
                `;
                wrapper.insertAdjacentHTML("beforeend", groupHTML);
            });
        })
        .catch((err) => {
            console.error("결제 내역 불러오기 실패:", err);
        });
});


document.addEventListener("click", function (e) {
    if (e.target.classList.contains("buttonCancel")) {
        alert("결제 취소 및 환불은 1:1 문의를 통해 진행하실 수 있습니다.\n하단에 고객센터 > 카카오톡 채널 픽커스에 문의를 남겨주세요.");
    }
});