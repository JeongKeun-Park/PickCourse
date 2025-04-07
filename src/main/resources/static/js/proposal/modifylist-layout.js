const planLayout = (() => {

    const showList = async (planListData) => {
        console.log(planListData)
        const listWrap = document.querySelector(".list-wrap");
        const buttonWrap = document.querySelector("#button-wrap");
        const leftButtons = document.querySelector(".left-button-wrap");
        const rightButtons = document.querySelector(".right-button-wrap");
        const modalWrap = document.querySelector(".modal-wrap");
        const pagination = planListData.pagination;
        let text = ``;

        // 왼쪽 화살표 버튼 생성
        leftButtons.innerHTML = `
        <button
            data-index=1
            width="40px"
            height="40px"
            font-size="18px"
            disabled=""
            class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-none start-page"
            >
                <img
                        src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                        class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns start-img"
                /></button
            ><button
                data-index=${pagination.page-1}
                width="40px"
                height="40px"
                font-size="18px"
                disabled=""
                class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-none left-button"
        >
            <img
                    src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                    class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns left-img"
            />
        </button>
        `;
        const startPage =document.querySelector(".start-page")
        const leftButton = document.querySelector(".left-button")
        if(pagination.page !== 1) {
            startPage.classList.remove("status-none");
            startPage.classList.add("status-on");
            startPage.removeAttribute("disabled")
            leftButton.classList.remove("status-none")
            leftButton.classList.add("status-on")
            leftButton.removeAttribute("disabled")
            document.querySelector(".start-img").src ="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".left-img").src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        }else {
            startPage.classList.remove("status-on")
            startPage.classList.add("status-none")
            startPage.setAttribute("disabled", true)
            leftButton.classList.remove("status-on")
            leftButton.classList.add("status-none")
            leftButton.setAttribute("disabled", true)
            document.querySelector(".start-img").src ="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".left-img").src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        }

        rightButtons.innerHTML = `
        <button
                    data-index=${pagination.page + 1}
                    width="40px"
                    height="40px"
                    font-size="18px"
                    class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-on right-button"
            >
                <img
                        src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                        class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu right-img"
                /></button
            ><button
                data-index=${pagination.realEnd}
                width="40px"
                height="40px"
                font-size="18px"
                class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 status-on  last-page"
        >
            <img
                    src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E"
                    class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu last-img"
            />
        </button>
        `;
        const lastPage = document.querySelector(".last-page");
        const rightButton = document.querySelector(".right-button");

        if(pagination.page !== pagination.realEnd) {
            lastPage.classList.remove("status-none");
            lastPage.classList.add("status-on");
            lastPage.removeAttribute("disabled")
            rightButton.classList.remove("status-none")
            rightButton.classList.add("status-on")
            rightButton.removeAttribute("disabled")
            document.querySelector(".last-img").src ="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".right-img").src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        }else {
            lastPage.classList.remove("status-on")
            lastPage.classList.add("status-none")
            lastPage.setAttribute("disabled", true)
            rightButton.classList.remove("status-on")
            rightButton.classList.add("status-none")
            rightButton.setAttribute("disabled", true)
            document.querySelector(".last-img").src ="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
            document.querySelector(".right-img").src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E";
        }

        // 가져온 목록 for문으로 분리
        planListData.planList.forEach((plan) => {
            console.log(plan.planFilePath)
            // 가져온 날짜 정보 담아주기
            const startDate = new Date(plan.planStartDate);
            const endDate = new Date(plan.planEndDate);
            const encodedFilePath = plan.planFilePath && plan.planFileName
                ? encodeURIComponent(`${plan.planFilePath}/${plan.planFileName}`)
                : null;

            const defaultImage = "/images/proposal/noImage.png";

            // ?월 ?일로 날짜 변환
            const formatStartDate = `${startDate.getMonth() + 1}월 ${startDate.getDate()}일`;
            const formatEndDate = `${endDate.getMonth() + 1}월 ${endDate.getDate()}일`;

            const formatPrice = plan.planPrice.toLocaleString();

            text += `
                 <div>
                                            <div class="MagazineListPage__MagazineWrapper-hh1ck3-2 jZtIEr">
                                                <a
                                                    class="MagazineListPage__Magazine-hh1ck3-3 hHOLgL"
                                                    href="/proposal/read?id=${plan.id}"
                                                    ><div class="Image__Wrapper-v97gyx-0 gDuKGF">
                                                        <img
                                                            class="Image__StyledImageLoader-v97gyx-2 bUFcfh"
                                                            width="160"
                                                            src="https://res.cloudinary.com/frientrip/image/upload/c_fill,f_auto,g_center,h_240,q_auto,w_200/thm_%EC%9D%B4%EB%B2%88%EC%A3%BC%EB%A7%90%EB%AD%90%ED%95%98%EC%A7%80%20(13)_d1a9b6efdfcb34b9d97243819cefc83f4f656f479bdc1686ba582b3c4368848c"
                                                        />
                                                        <div
                                                            class="Fade__Wrapper-sc-1s0ipfq-0 koasSX"
                                                            style="opacity: 1; display: block"
                                                        >
                                                            <div class="Ratio" style="display: block">
                                                                <div
                                                                    class="Ratio-ratio"
                                                                    style="
                                                                        height: 0px;
                                                                        position: relative;
                                                                        width: 100%;
                                                                        padding-top: 120%;
                                                                    "
                                                                >
                                                                    <div
                                                                        class="Ratio-content"
                                                                        style="
                                                                            height: 100%;
                                                                            left: 0px;
                                                                            position: absolute;
                                                                            top: 0px;
                                                                            width: 100%;
                                                                        "
                                                                    >
                                                                        <img
                                                                            class="Image__StyledImage-v97gyx-1 VUNpu"
                                                                            width="160"
                                                                            src="${encodedFilePath ? `/files/display?path=${encodedFilePath}` : defaultImage}"
                                                                        />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <div class="MagazineListPage__Title-hh1ck3-4 kBOcSr">
                                                            <span>${plan.planName}</span><br />
                                                            <span>일정 : ${formatStartDate} ~ ${formatEndDate}</span><br />
                                                            <span>비용 : ${formatPrice}원</span><br />
                                                            <span>참가 : ${plan.participants.length}/${plan.planMaxPersonnel}(참여/목표)</span><br />
                                                        </div>
                                                        <div class="MagazineListPage__CatchPhrase-hh1ck3-5 dfnTnv">
                                                            캡틴 : ${plan.memberNickname}
                                                        </div>
                                                    </div>
                                                </a>

                                                <div class="button-container">
                                                    <button
                                                    data-index="${plan.id}"
                                                        type="button"
                                                        class="CurrentProfile__MoreButton-sc-1u92qay-6 FvtMb"
                                                    >
                                                        <img
                                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='4' height='17' viewBox='0 0 4 17'%3E %3Cpath fill='%23999' fill-rule='evenodd' d='M1.57 14a1.5 1.5 0 110 3 1.5 1.5 0 010-3zm0-7a1.5 1.5 0 110 3 1.5 1.5 0 010-3zm0-7a1.5 1.5 0 110 3 1.5 1.5 0 010-3z'/%3E %3C/svg%3E"
                                                            alt="더보기"
                                                        />
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
            `;
        })
        listWrap.innerHTML = text;

        text=``;

        for(let i = pagination.startPage; i<= pagination.endPage; i++){
            if(pagination.page === i){
                text += `
                <button
                    data-index=${i}
                    width="40px"
                    height="40px"
                    color="#3397ff"
                    font-size="18px"
                    font-weight="bold"
                    class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 emphasis buttonAll thisButton">${i}
                </button>
                `;
                continue;
            }
            text += `
            <button
                    data-index=${i}
                    width="40px"
                    height="40px"
                    color="black"
                    font-size="18px"
                    font-weight="normal"
                    class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 emphasis buttonAll anotherButton">${i}
            </button>
            `;
        }
        buttonWrap.innerHTML = text;
    }

    return {showList: showList}
})()