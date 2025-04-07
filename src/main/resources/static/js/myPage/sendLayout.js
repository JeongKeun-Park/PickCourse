const messageLayout = (() => {
    const showList = async (sendListData) => {
        console.log(sendListData);
        const sendMessages = sendListData.sendMessages;
        const pagination = sendListData.pagination;
        const noSendMessage = document.getElementById("no-sendMessage");
        const sendMessageWrap = document.getElementById("sendMessageWrap");
        const pageWrap = document.getElementById("page-wrap");

        if (sendMessages.length === 0) {
            sendMessageWrap.style.display = "none";
            pageWrap.style.display = "none"
            noSendMessage.style.display = "block";
        } else {
            noSendMessage.style.display = "none"
            sendMessageWrap.style.display = "block";
            pageWrap.style.display = "flex"

            let text = ``;

            sendMessages.forEach(({id , receiverEmail, receiverNickname, content, sendDate}) => {
                text += `
                    <div class="userListDiv" data-id="${id}" data-receiver-email="${receiverEmail}">
                        <div class="postList">
                            <a href="">
                              <span>${receiverNickname}</span>
                            </a>
                        </div>
                            <div class="contentHidden">
                                  <span style="cursor: pointer" class="hiddenText" >${content}</span>
                            </div>
                            <div class="comentMessage">
                                  <span>${sendDate}</span>
                            </div>
                    </div>
                `
            });

            sendMessageWrap.innerHTML = text;

            text = ``;

            if(pagination.page === 1) {
                text += `
            <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 iItkLq"  data-action="firstPage" id="${1}" disabled="">
                <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns" disabled=""

                />
            </button>
            `;
            } else {
                text += `
                <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 iItkLq" data-action="firstPage" id="${1}">
                <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns"

                />
                </button>
                
                `;


            }


// 이전 페이지 버튼 추가
            if (pagination.page !== 1) {
                text += `
        <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 iItkLq" data-action="prevPage" id="${pagination.page - 1}">
            <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns" 
                                             />

        </button>
    `;
            } else {
                text += `
                <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 iItkLq" data-action="prevPage" id="${pagination.page - 1}" disabled="">
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
                    text += `
            <button data-target="div${i}" width="40px" height="40px" color="#3397ff" font-size="18px" font-weight="bold" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 emphasis buttonAll thisButton" id="${i}">
                ${i}
            </button>
        `;
                } else {
                    text += `
            <button data-target="div${i}" width="40px" height="40px" color="black" font-size="18px" font-weight="normal" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 emphasis buttonAll anotherButton" id="${i}">
                ${i}
            </button>
        `;
                }
            }

// 다음 페이지 버튼 추가
            if (pagination.page !== pagination.realEnd) {
                text += `
        <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 garfld" data-action="nextPage" id="${pagination.page + 1}">
            <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath d='M18 0H0v18h18z'/%3E %3Cpath stroke='%23000' stroke-width='1.5' d='M7 5l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu"
                                            />
        </button>
    `;
            } else {
                text += `
                        <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 garfld" data-action="nextPage" id="${pagination.page + 1}" disabled="">
            <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu" disabled=""
                                        />

        </button>
                `;
            }

        if(pagination.page === pagination.realEnd) {
            text += `
            <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 garfld" data-action="lastPage" id="${pagination.realEnd}" disabled>
            <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='%23FFF' fill-opacity='0' fill-rule='nonzero' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu" disabled=""
                                            />
            </button>
            
            `;
        } else {
            text += `
        <button width="40px" height="40px" font-size="18px" class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 garfld" data-action="lastPage" id="${pagination.realEnd}" >
            <img
                                            src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Crect width='40' height='40' fill='%23FFF' fill-opacity='0' fill-rule='nonzero' rx='20'/%3E %3Cg stroke='%23000' stroke-width='1.5'%3E %3Cpath d='M16 16l4 4-4 4M21 16l4 4-4 4'/%3E %3C/g%3E %3C/g%3E %3C/svg%3E"
                                            class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu"
                                            />
        </button>
        
            
    `;
        }
            pageWrap.innerHTML = text;
        }
    }
    return {showList: showList};
})();


