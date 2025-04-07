const receiveMessageWrap = document.getElementById("receiveMessageWrap");
const pageWrap = document.getElementById("page-wrap");

messageService.getReceiveList(messageLayout.showList)

pageWrap.addEventListener("click", (e) => {
    let targetButton = e.target;

    if (targetButton.tagName.toLowerCase() === "img") {
        targetButton = targetButton.closest("button");
    }

    if (targetButton && targetButton.classList.contains("Button-bqxlp0-0")) {
        const page = targetButton.id;

        messageService.getReceiveList(messageLayout.showList, page);
    }
});

//===========================//

//===========================//

document.addEventListener("DOMContentLoaded", () => {
    const receiveMessageWrap = document.getElementById("receiveMessageWrap");

    receiveMessageWrap.addEventListener("click", async (e) => {
        const clickedElement = e.target.closest(".hiddenText");
        if (!clickedElement) return;

        const parentDiv = clickedElement.closest(".userListDiv");
        if (!parentDiv) return;

        const receiveMessageId = parentDiv.dataset.id;
        const isChecked = parentDiv.dataset.checked === "true";

        if (!isChecked) {
            await markMessageAsRead(receiveMessageId);
            parentDiv.dataset.checked = "true";
            parentDiv.classList.add("read"); // 읽음 처리된 메시지 스타일 적용
        }

        document.querySelectorAll(".showList").forEach((list) => {
            if (list !== parentDiv.nextElementSibling) {
                list.style.display = "none";
            }
        });

        let showList = parentDiv.nextElementSibling;

        if (!showList || !showList.classList.contains("showList")) {
            const senderEmail = parentDiv.dataset.senderEmail;

            showList = document.createElement("div");
            showList.classList.add("NoticeEntity__Content-sc-1x9h6uc-5", "kOSXdV", "showList");
            showList.innerHTML = `
                <span>${clickedElement.textContent.trim()}</span>
                <div class="buttonThumbnailContainer">
                    <div id="fileContainer-${receiveMessageId}" class="appearWrap thumbnailDiv" style="display: none;">
                        첨부된 파일 :
                        <a id="fileDownload-${receiveMessageId}" href="#" download>
                            <img id="fileImage-${receiveMessageId}" src="#" class="thumbnailImage">
                        </a>
                    </div>
                    <div class="buttonContainer">
                        <button class="answeraButton appearButton appearWrap" data-sender-email="${senderEmail}">답장</button>
                        <button class="deleteButton appearButton appearWrap" data-id="${receiveMessageId}">삭제</button>
                    </div>
                </div>
            `;

            parentDiv.after(showList);

            try {
                const response = await fetch(`/my-page/files/receive/${receiveMessageId}`);
                const file = await response.json();

                if (file && file.fileName) {
                    const fileContainer = document.getElementById(`fileContainer-${receiveMessageId}`);
                    const fileImage = document.getElementById(`fileImage-${receiveMessageId}`);
                    const fileDownload = document.getElementById(`fileDownload-${receiveMessageId}`);

                    const thumbnailFileName = `t_${file.fileName}`;
                    const encodedFilePath = encodeURIComponent(`${file.filePath}/${thumbnailFileName}`);
                    const encodedOriginalFilePath = encodeURIComponent(`${file.filePath}/${file.fileName}`);

                    fileImage.src = `/files/display?path=${encodedFilePath}`;
                    fileDownload.href = `/files/download?path=${encodedOriginalFilePath}`;

                    fileContainer.style.display = "block";
                } else {
                    document.getElementById(`fileContainer-${receiveMessageId}`).remove();
                }
            } catch (error) {
                document.getElementById(`fileContainer-${receiveMessageId}`).remove();
            }
        } else {
            showList.style.display = showList.style.display === "block" ? "none" : "block";
        }
    });

    receiveMessageWrap.addEventListener("click", (e) => {
        if (e.target.classList.contains("answeraButton")) {
            const senderEmail = e.target.dataset.senderEmail;
            if (!senderEmail) {
                alert("보낼 대상의 이메일을 찾을 수 없습니다.");
                return;
            }
            window.location.href = `/my-page/messageWrite?receiver=${encodeURIComponent(senderEmail)}`;
        }
    });

    receiveMessageWrap.addEventListener("click", (e) => {
        if (e.target.classList.contains("deleteButton")) {
            const receiveMessageId = e.target.dataset.id || e.target.closest(".userListDiv")?.dataset.id;
            if (!receiveMessageId) {
                alert("삭제할 메시지를 찾을 수 없습니다.");
                return;
            }

            fetch(`/my-page/deleteReceiveMessage`, {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: new URLSearchParams({ id: receiveMessageId })
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

async function markMessageAsRead(messageId) {
    try {
        const response = await fetch("/my-page/readMessage", {
            method: "POST",
            body: new URLSearchParams({ id: messageId })
        });

        const result = await response.text(); // Boolean 대신 String을 받음
        console.log(`📌 메시지(${messageId}) 업데이트 결과:`, result);

    } catch (error) {
        console.error("❌ 메시지 읽음 처리 실패:", error);
    }
}
