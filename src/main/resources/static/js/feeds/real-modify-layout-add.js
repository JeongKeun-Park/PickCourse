// 25.03.19 조승찬

function createThumbnail(thumbnails) {

    // 화면에 보여지는 기존 파일들을 유지하기 위해서 신규 요소들을 append
    thumbnails.forEach((file, index) => {

        // li 요소 생성
        const li = document.createElement("li");
        li.setAttribute("data-index", index);
        li.setAttribute("class", "uploadFile new-file");
        li.setAttribute("data-file-name", file.fileName);
        li.setAttribute("data-file-path", file.filePath);
        li.setAttribute("data-file-size", file.fileSize);

        // 삭제 버튼 이미지 추가
        const cancelImg = document.createElement("img");
        cancelImg.setAttribute("src", "/images/cancel.jpg");
        cancelImg.setAttribute("class", "file-cancel");
        cancelImg.setAttribute("alt", "cancel");
        li.appendChild(cancelImg);

        // 파일 경로 인코딩 및 이미지 추가
        const encodedFilePath = encodeURIComponent(`${file.filePath}/${file.fileName}`);
        const thumbnailImg = document.createElement("img");
        thumbnailImg.setAttribute("src", `/files/display?path=${encodedFilePath}`);
        thumbnailImg.setAttribute("class", "image-files");
        thumbnailImg.setAttribute("alt", "thumbnail");
        li.appendChild(thumbnailImg);

        // li 요소를 ul 컨테이너에 추가
        document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS").appendChild(li);
    });
}