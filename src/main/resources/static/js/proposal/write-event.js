const fileParentDiv = document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS");
const fileInput = document.querySelector(".InputImageReview__Wrapper-sc-1oapt4s-0.ipbuZD input");
fileInput.addEventListener("change", (e) => {
    const file = e.target.files[0];

    // multipart/form-data 형식으로 데이터를 자동 처리
    const formData = new FormData();
    formData.append("file", file);
    // 서버로 전송하여 path와 썸네일 생성
    inputFileUpload(formData);
});

// 선택파일의 이미지(x)를 눌렀을 때 전체 dev 삭제 :: 동적으로 생성된 요소일 때는 부모 요소에 위임
document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS").addEventListener("click", e => {
    if (e.target.className == "file-cancel") {
        e.target.closest(".uploadFile").remove()
    }
});
// 선택파일의 이미지(x)를 눌렀을 때 전체 dev 삭제 :: 동적으로 생성된 요소일 때는 부모 요소에 위임

// 등록 하기
const button = document.querySelector(".saveButton");

button.addEventListener("click", () => {
    if(document.querySelector(".uploadFile") == null){
        alert("사진을 한장 이상 선택해주세요😊")
        return;
    }

    let max = parseInt(document.querySelector(".gcqwwh.max").value, 10);
    let min = parseInt(document.querySelector(".gcqwwh.min").value, 10);
    if (max < min) {
        // alert(`모집 인원("${max}") 이 최소 출발 인원("${min})"보다 적습니다.`);
        let message = `모집 인원(${max} 명) 이 최소 출발 인원(${min} 명)보다 적습니다.`;
        showAlertModal(message);
        return;
    }
    // 배열로 받은 것들을 단일객체화하여 input태그에 속성 부여 후 form태그에 appendChild로 생성
    includes.forEach((include, i) => {
        const input = document.createElement("input");
        input.type = "hidden";
        input.name=`includeContents[${i}].includeContent`;
        input.value = include;
        document['write-form'].appendChild(input);
    })
    excludes.forEach((exclude, i)=>{
        const input = document.createElement("input");
        input.type = "hidden";
        input.name = `excludeContents[${i}].excludeContent`;
        input.value = exclude;
        document['write-form'].appendChild(input);
    })
    prepares.forEach((prepare, i) => {
        const input = document.createElement("input");
        input.type = "hidden";
        input.name = `prepareContents[${i}].prepareContent`
        input.value = prepare;
        document['write-form'].appendChild(input);
    })
    const textareas = document.querySelectorAll("textarea[data-index]")

    textareas.forEach((textarea) => {
        const index = textarea.getAttribute('data-index'); // data-index 값
        const value = textarea.value; // 입력된 텍스트
        schedules.push(value);
    })

    schedules.forEach((schedule, i) => {
        const input = document.createElement("input");
        input.type = "hidden";
        input.name = `scheduleContents[${i}].scheduleContent`
        input.value = schedule;
        document['write-form'].appendChild(input);
    })

    const inputFileName = document.createElement("input");
    inputFileName.type = "hidden";
    inputFileName.name = `planFileName`;
    inputFileName.value = document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS").querySelector(".uploadFile").dataset.fileName;
    document['write-form'].appendChild(inputFileName);

    const inputFilePath = document.createElement("input");
    inputFilePath.type = "hidden";
    inputFilePath.name = `planFilePath`;
    inputFilePath.value = document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS").querySelector(".uploadFile").dataset.filePath;
    document['write-form'].appendChild(inputFilePath);

    const inputFileSize = document.createElement("input");
    inputFileSize.type = "hidden";
    inputFileSize.name = `planFileSize`;
    inputFileSize.value = document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS").querySelector(".uploadFile").dataset.fileSize;
    document['write-form'].appendChild(inputFileSize);
    // wirte-form태그를 submit하여 post형식의 write controller로 전달.
    document['write-form'].submit();
});


