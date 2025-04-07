document.addEventListener("DOMContentLoaded", function () {
    const noticeListWrapper = document.querySelector(".NoticePage__NoticeListWrapper-sc-1qw51if-0");
    const pageWrap = document.getElementById("pageWrap");
    let currentPage = 1;

    function fetchNotices(page) {
        fetch(`/main-page/notices?page=${page}`)
            .then(res => res.json())
            .then(data => {
                console.log("pagination >>>", data.pagination);
                renderNotices(data.noticeList);
                renderPagination(data.pagination);
                currentPage = data.pagination.page;
            });
    }

    function renderNotices(list) {
        noticeListWrapper.innerHTML = ""; // 기존 리스트 비우기

        list.forEach(notice => {
            const li = document.createElement("li");

            const wrapper = document.createElement("div");
            wrapper.className = "NoticeEntity__TitleWrapper-sc-1x9h6uc-0 ckmAlF";
            wrapper.setAttribute("data-id", notice.id);

            wrapper.innerHTML = `
                <div class="Badge-op7aqa-0 NoticeEntity__ExtendedBadge-sc-1x9h6uc-1 jEbLXp">공지</div>
                <div class="NoticeEntity__Title-sc-1x9h6uc-2 ipMFPV">${notice.noticeTitle}</div>
                <div class="NoticeEntity__Date-sc-1x9h6uc-4 cOBcxv">${notice.createdDate}</div>
            `;

            const contentDiv = document.createElement("div");
            contentDiv.classList.add("notice-content");
            contentDiv.style.display = "none";
            contentDiv.style.marginTop = "10px";

            li.appendChild(wrapper);
            li.appendChild(contentDiv);
            noticeListWrapper.appendChild(li);
        });

        bindToggle();
    }

    function bindToggle() {
        document.querySelectorAll(".NoticeEntity__TitleWrapper-sc-1x9h6uc-0").forEach(header => {
            header.addEventListener("click", function () {
                const id = this.dataset.id;
                const contentDiv = this.nextElementSibling;

                if (contentDiv.style.display === "none") {
                    fetch(`/main-page/notices/${id}`)
                        .then(res => res.json())
                        .then(data => {
                            contentDiv.innerHTML = `
                                <div class="NoticeContent__Body">${data.noticeContent}</div>
                            `;
                            contentDiv.style.display = "block";
                        });
                } else {
                    contentDiv.style.display = "none";
                }
            });
        });
    }

    function renderPagination(p) {
        if (!pageWrap || !p) return;

        let html = "";

        // << 첫 페이지 버튼
        if(p.page === 1) {
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
        if (p.page !== 1) {
            html += `
        <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 iItkLq page-button" data-action="prevPage" id="${p.page - 1}">
            <img
                                          src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns"
                                             />

        </button>
    `;
        } else {
            html += `
                <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 iItkLq page-button" data-action="prevPage" id="${p.page - 1}" disabled="">
                                        <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns" disabled=""
                                        />
                                    </button>


        </button>
    `;
        }

// 페이지 번호 버튼들 추가
        for (let i = p.startPage; i <= p.endPage; i++) {
            if (p.page === i) {
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
        if (p.page !== p.realEnd) {
            html += `
        <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 garfld page-button" data-action="nextPage" id="${p.page + 1}">
            <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu"
                                            />
        </button>
    `;
        } else {
            html += `
                        <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 garfld page-button" data-action="nextPage" id="${p.page + 1}" disabled="">
            <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu" disabled=""
                                        />

        </button>
                `;
        }

        if(p.page === p.realEnd) {
            html += `
            <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 garfld page-button" data-action="lastPage" id="${p.realEnd}" disabled>
            <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu" disabled=""
                                            />
            </button>

            `;
        } else {
            html += `
        <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 garfld page-button" data-action="lastPage" id="${p.realEnd}" >
            <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu"
                                            />
        </button>


    `;
        }

        pageWrap.innerHTML = html;

        document.querySelectorAll(".page-button").forEach(btn => {
            btn.addEventListener("click", () => {
                const page = parseInt(btn.id);
                if (!isNaN(page)) fetchNotices(page);
            });
        });
    }

    fetchNotices(currentPage);
});
