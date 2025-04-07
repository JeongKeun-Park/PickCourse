// 25.03.21 조승찬

function createThumbnail(file) {
    let text = ``;
        // 파일 삭제를 위한 index, 서버로 전달할 정보 세팅
        // file-name 가 dataset에서는 카멜표기법(fileName)으로 변경됨
        text += `<li  class="uploadFile"     
					data-file-name="${file.fileName}" data-file-path="${file.filePath}" data-file-size="${file.fileSize}" >`;
        text += `<img src="/images/cancel.jpg" class="file-cancel" alt="calcel"">`;
        const encodedFilePath = encodeURIComponent(`${file.filePath}/${file.fileName}`);   // 이미지 파일이 아닌경우 별도의 이미지 파일 제공
        text += `<img src="/files/display?path=${encodedFilePath}" class="image-files" alt="thumbnail">&nbsp;&nbsp;`;                             // 이미지 파일 여부 확인 할 별도 필드도 마련되어야 함
        text += `</li>`;
    document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS").innerHTML = text;
    document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS").scrollIntoView({ behavior: "smooth", block: "start" });   // 추가된 행들이 처지지 않게 위치 잡아주기

}