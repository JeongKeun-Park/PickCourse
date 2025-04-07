const sendMessageWrap = document.getElementById("sendMessageWrap");
const pageWrap = document.getElementById("page-wrap");

messageService.getSendList(messageLayout.showList)

pageWrap.addEventListener("click", (e) => {
    let targetButton = e.target;

    if (targetButton.tagName.toLowerCase() === "img") {
        targetButton = targetButton.closest("button");
    }

    if (targetButton && targetButton.classList.contains("Button-bqxlp0-0")) {
        const page = targetButton.id;

        messageService.getSendList(messageLayout.showList, page);
    }
});

//====================================================================================

document.addEventListener("DOMContentLoaded", () => {
    const sendMessageWrap = document.getElementById("sendMessageWrap");

    sendMessageWrap.addEventListener("click", async (e) => {
        const clickedElement = e.target.closest(".hiddenText");
        if (!clickedElement) return;

        const parentDiv = clickedElement.closest(".userListDiv");
        if (!parentDiv) return;

        document.querySelectorAll(".showList").forEach((list) => {
            if (list !== parentDiv.nextElementSibling) {
                list.style.display = "none";
            }
        });

        let showList = parentDiv.nextElementSibling;

        if (!showList || !showList.classList.contains("showList")) {
            const receiverEmail = parentDiv.dataset.receiverEmail;
            const sendMessageId = parentDiv.dataset.id;

            showList = document.createElement("div");
            showList.classList.add("NoticeEntity__Content-sc-1x9h6uc-5", "kOSXdV", "showList");
            showList.innerHTML = `
                <span>${clickedElement.textContent.trim()}</span>
                <div class="buttonThumbnailContainer">
                    <div id="fileContainer-${sendMessageId}" class="appearWrap thumbnailDiv" style="display: none;">
                        첨부된 파일 :
                        <a id="fileDownload-${sendMessageId}" href="#" download>
                            <img id="fileImage-${sendMessageId}" src="#" class="thumbnailImage">
                        </a>
                    </div>
                    <div class="buttonContainer">
                        <button class="answeraButton appearButton appearWrap" data-receiver-email="${receiverEmail}">답장</button>
                        <button class="deleteButton appearButton appearWrap" data-id="${sendMessageId}">삭제</button>
                    </div>
                </div>
            `;

            parentDiv.after(showList);

            try {
                const response = await fetch(`/my-page/files/send/${sendMessageId}`);
                const file = await response.json();

                if (file && file.fileName) {
                    const fileContainer = document.getElementById(`fileContainer-${sendMessageId}`);
                    const fileImage = document.getElementById(`fileImage-${sendMessageId}`);
                    const fileDownload = document.getElementById(`fileDownload-${sendMessageId}`);

                    const thumbnailFileName = `t_${file.fileName}`;
                    const encodedFilePath = encodeURIComponent(`${file.filePath}/${thumbnailFileName}`);
                    const encodedOriginalFilePath = encodeURIComponent(`${file.filePath}/${file.fileName}`);

                    fileImage.src = `/files/display?path=${encodedFilePath}`;
                    fileDownload.href = `/files/download?path=${encodedOriginalFilePath}`;

                    fileContainer.style.display = "block";
                } else {
                    document.getElementById(`fileContainer-${sendMessageId}`).remove();
                }
            } catch (error) {
                document.getElementById(`fileContainer-${sendMessageId}`).remove();
            }
        } else {
            showList.style.display = showList.style.display === "block" ? "none" : "block";
        }
    });

    sendMessageWrap.addEventListener("click", (e) => {
        if (e.target.classList.contains("answeraButton")) {
            const receiverEmail = e.target.dataset.receiverEmail;
            if (!receiverEmail) {
                alert("보낼 대상의 이메일을 찾을 수 없습니다.");
                return;
            }
            window.location.href = `/my-page/messageWrite?receiver=${encodeURIComponent(receiverEmail)}`;
        }
    });

    sendMessageWrap.addEventListener("click", (e) => {
        if (e.target.classList.contains("deleteButton")) {
            const id = e.target.dataset.id || e.target.closest(".userListDiv")?.dataset.id;
            if (!id) {
                alert("삭제할 메시지를 찾을 수 없습니다.");
                return;
            }

            fetch(`/my-page/deleteSendMessage`, {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: new URLSearchParams({ id: id })
            })
                .then(response => response.json())
                .then(isDeleted => {
                    if (isDeleted) {
                        alert("메시지가 삭제되었습니다.");
                        location.reload();
                    } else {
                        alert("삭제 실패");
                    }
                })
                .catch(error => alert("삭제 중 오류가 발생했습니다."));
        }
    });
});
