
document.addEventListener("DOMContentLoaded", function () {
    const pageWrap = document.getElementById("pageWrap");
    const bannerWrapper = document.querySelector(".Banner__Wrapper-dlocnb-0");

    let currentPage = 1;

    function fetchHeartList(page) {
        fetch(`/my-page/wishList?page=${page}`)
            .then(response => response.json())
            .then(data => {
                if (!data || !data.wishList || data.wishList.length === 0) {
                    bannerWrapper.innerHTML = `<p>찜한 코스가 없습니다.</p>`;
                    pageWrap.innerHTML = "";
                    return;
                }

                currentPage = data.pagination.page;

                renderHearts(data.wishList);
                renderPagination(data.pagination);
                addHeartEvent();
            })
            .catch(error => {
                console.error("찜 목록 로딩 실패", error);
            });
    }

    function renderHearts(wishList) {
        bannerWrapper.innerHTML = "";

        wishList.forEach(wish => {
            const courseFileName = wish.courseFileName;
            const courseFilePath = wish.courseFilePath;
            const fileNameWithPath = `${courseFilePath}/${courseFileName}`;
            const encodedPath = encodeURIComponent(fileNameWithPath);

            const imageUrl = `/files/display?path=${encodedPath}`;

            const item = document.createElement("div");
            item.classList.add("Banner__ImageSliderWrapper-dlocnb-2", "hWqRHN");
            item.innerHTML = `
            <div class="ImageSlider__SliderWrapper-sc-1obm2ug-0 hHiTXT">
                <div class="slick-slider ImageSlider__StyledSlider-sc-1obm2ug-1 cOsMnB slick-initialized">
                    <div class="slick-list">
                        <div class="slick-track">
                            <div class="slick-slide slick-active slick-current" style="width: 768px;">
                                <div>
                                    <a class="withLink__StyledLink-zuberk-0 lQqkn" href="/proposal/read?id=${wish.planId}">
                                        <div class="Image__Wrapper-v97gyx-0 gDuKGF">
                                            <div class="Fade__Wrapper-sc-1s0ipfq-0 koasSX" style="opacity: 1;">
                                                <div class="Ratio" style="display: block;">
                                                    <div class="Ratio-ratio" style="position: relative; width: 100%; padding-top: 30%;">
                                                        <div class="Ratio-content" style="height: 80%; position: absolute; top: 0; left: 0; width: 100%;">
                                                            <img src="${imageUrl}" class="Image__StyledImage-v97gyx-1 VUNpz" alt="추천코스 이미지" width="768">
                                                            <div class="commentCource">${wish.planName}</div>
                                                        </div>
                                                        <div class="heartWrap">
                                                            <button class="heartButton" data-plan-id="${wish.planId}" data-member-id="${wish.memberId}">
                                                                <img src="/images/main/red.svg" alt="찜하기" />
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `;
            bannerWrapper.appendChild(item);
        });
    }

function renderPagination(pagination) {
    const pageWrap = document.getElementById("pageWrap");
    if (!pageWrap || !pagination) return;

    let html = "";

    // << 첫 페이지 버튼
    if(pagination.page === 1) {
        html += `
            <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 iItkLq page-button"  data-action="firstPage" id="${1}" disabled="">
                <img
                                           src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns" disabled=""

                />
            </button>
            `;
    } else {
        html += `
                <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 iItkLq page-button" data-action="firstPage" id="${1}">
                <img
                                           src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns"

                />
                </button>

                `;


    }


// 이전 페이지 버튼 추가
    if (pagination.page !== 1) {
        html += `
        <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 iItkLq page-button" data-action="prevPage" id="${pagination.page - 1}">
            <img
                                          src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns"
                                             />

        </button>
    `;
    } else {
        html += `
                <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 iItkLq page-button" data-action="prevPage" id="${pagination.page - 1}" disabled="">
                                        <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns" disabled=""
                                        />
                                    </button>


        </button>
    `;
    }

// 페이지 번호 버튼들 추가
    for (let i = pagination.startPage; i <= pagination.endPage; i++) {
        if (pagination.page === i) {
            html += `
            <button data-target="div${i}" width="40px" height="40px" color="#3397ff" font-size="18px" font-weight="bold" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 emphasis buttonAll thisButton page-button" id="${i}">
                ${i}
            </button>
        `;
        } else {
            html += `
            <button data-target="div${i}" width="40px" height="40px" color="black" font-size="18px" font-weight="normal" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 emphasis buttonAll anotherButton page-button" id="${i}">
                ${i}
            </button>
        `;
        }
    }

// 다음 페이지 버튼 추가
    if (pagination.page !== pagination.realEnd) {
        html += `
        <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 garfld page-button" data-action="nextPage" id="${pagination.page + 1}">
            <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu"
                                            />
        </button>
    `;
    } else {
        html += `
                        <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 garfld page-button" data-action="nextPage" id="${pagination.page + 1}" disabled="">
            <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu" disabled=""
                                        />

        </button>
                `;
    }

    if(pagination.page === pagination.realEnd) {
        html += `
            <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 garfld page-button" data-action="lastPage" id="${pagination.realEnd}" disabled>
            <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu" disabled=""
                                            />
            </button>

            `;
    } else {
        html += `
        <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 garfld page-button" data-action="lastPage" id="${pagination.realEnd}" >
            <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu"
                                            />
        </button>


    `;
    }

    pageWrap.innerHTML = html;

    document.querySelectorAll(".page-button").forEach(button => {
        button.addEventListener("click", function () {
            const page = parseInt(this.id);
            if (!isNaN(page)) {
                fetchHeartList(page);
            }
        });
    });
}

    function addHeartEvent() {
        document.querySelectorAll(".heartButton").forEach(button => {
            button.addEventListener("click", function (event) {
                event.preventDefault();
                event.stopPropagation();

                const img = this.querySelector("img");
                const planId = this.dataset.planId;
                const memberId = document.getElementById("memberId").value;

                if (img && img.src.includes("red.svg")) {
                    img.src = "/images/main/white.svg";

                    fetch('/my-page/deleteHeart', {

                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify({ memberId, planId })
                    })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error("찜 삭제 실패");
                            }
                            return response.text();
                        })
                        .then(msg => {
                            console.log("삭제 성공:", msg);

                            const courseCard = this.closest(".Banner__ImageSliderWrapper-dlocnb-2");
                            if (courseCard) courseCard.remove();
                        })
                        .catch(err => {
                            console.error("삭제 중 에러:", err);
                        });
                } else {
                    // 추후 찜 추가 기능 넣을 자리
                    img.src = "/images/main/red.svg";
                }
            });
        });
    }

    fetchHeartList(currentPage);
});