const reviewCount = document.querySelector(".review-count");

reviewCount.innerHTML = `
        <div>후기 <span class="ProductReviewPage__Count-mmv016-2 lPYvt">${lists.feedLists.length}</span>개</div>
`;

const reviewLayout = (() => {
    const showList = async (feedListData) => {
        const reviewWrap = document.querySelector(".review-wrap");
        const buttonWrap = document.querySelector("#button-wrap");
        const leftButtons = document.querySelector(".left-button-wrap");
        const rightButtons = document.querySelector(".right-button-wrap");
        const pagination = feedListData.pagination;


        let text = ``;
        let imgPath = new Array();

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

        feedListData.feedList.forEach((feed, i)=> {
        const createdDate = feed.createdDate;
        const targetCreatedDate = new Date(createdDate);
        const formatCreatedDate = `${targetCreatedDate.getFullYear()}년 ${targetCreatedDate.getMonth()+1}월 ${targetCreatedDate.getDate()}일`;

        const encodedFilePath = feed.files[0]?.filePath && feed.files[0]?.fileName
                ? encodeURIComponent(`${feed.files[0].filePath}/${feed.files[0].fileName}`)
                : null;
        const encodedMemberPath = feed.memberFilePath && feed.memberFileName
            ? encodeURIComponent(`${feed.memberFilePath}/${feed.memberFileName}`)
            : null;

        const defaultImage = "/images/proposal/noImage.png";
        const defaultProfileImage = "/images/proposal/noImage.png"

        text += `
            <div class="CardReview__Wrapper-f2ssd2-0 gPxoog">
                <div class="CardReview__Header-f2ssd2-1 cCFsGh">
                    <div class="CardReview__ProfileImageWrapper-f2ssd2-17 gARvBK">
                        <div data-index="${i}" class="Image__Wrapper-v97gyx-0 gDuKGF profile-wrap">
                            <img
                                class="Image__StyledImageLoader-v97gyx-2 bUFcfh"
                                width="48"
                                src="https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2,f_auto,q_auto,w_48/ios_image_1307410_20221105221106012_56de93a55028cee4b534c47531b65946996e1c1c47cea3c99ab66d594cf730e3"
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
                                            padding-top: 100%;
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
                                                alt="프로필"
                                                class="Image__StyledImage-v97gyx-1 VUNpu"
                                                width="48"
                                                src="${encodedMemberPath ? `/files/display?path=${encodedMemberPath}` : defaultProfileImage}"
                                            />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="CardReview__MetaData-f2ssd2-2 dkvMyh">
                        <div class="CardReview__Title-f2ssd2-4 RFTCR">${feed.memberNickname}</div>
                        <div class="CardReview__Row-f2ssd2-3 llgSwy">
                            <div class="CardReview__Created-f2ssd2-5 fbQFTO">
                                ${formatCreatedDate} 작성
                            </div>
                        </div>
                    </div>
                </div>
                <div class="CardReview__Body-f2ssd2-9 kIUsm">
                    ${feed.feedContent}
                </div>
                <div class="CardReview__ImageWrapper-f2ssd2-14 dbtIvu img-wrap">
                </div>
            </div>
        </div>
        `;

        imgPath.push(`
                    <div class="Image__Wrapper-v97gyx-0 gDuKGF">
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
                                        padding-top: 100%;
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
                                            alt="후기 이미지"
                                            class="Image__StyledImage-v97gyx-1 VUNpu"
                                            width="100"
                                            src="${encodedFilePath ? `/files/display?path=${encodedFilePath}` : defaultImage}"
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
        `)
        })
        reviewWrap.innerHTML = text;
        const imgWrap = document.querySelectorAll(".img-wrap");
        imgWrap.forEach((img, i) => {
            img.innerHTML = imgPath[i];


        })

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
    return{
        showList:showList
    }
})();




