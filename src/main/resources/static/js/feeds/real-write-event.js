// 2025 조승찬 작성

// textarea에 글자 입력시 입력된 글자 수 보여주기
document.querySelector(".kmqQeB").addEventListener("input", (e) => {
    e.target.closest(".iFxPyq").querySelector(".jvAusQ").textContent = `${
        document.querySelector(".kmqQeB").value.length
    } / 1200 (추천 글자수: 30자 이내)`;
});
// textarea에 글자 입력시 입력된 글자 수 보여주기

// 엔터키를 눌렀을 때 태그 생성
let tagCount = 0;
const tagParentDiv = document.querySelector(".bmexYY-container");
const tagInput = document.querySelector(".SocialFeedTagsContainer__SocialFeedTagsWrapper-sc-2762su-0.bmexYY input");
tagInput.addEventListener("keydown", (e) => {

    if (e.key == "Enter") {
        e.preventDefault(); //  폼 제출 방지 keyup이면 이미 제출되므로 keydown으로 바꾸고

        if (tagCount > 9) {
            //alert(`10개 까지만 입력 가능합니다.`);
            showAlertModal(`10개 까지만 입력 가능합니다.`);
            return;
        }
        const tagChildDiv = document.createElement("div");
        tagChildDiv.className = "SocialFeedTagsContainer__TagsItemContainer-sc-2762su-2 bJjaqH";
        tagChildDiv.innerHTML = `<span>#${tagInput.value}</span>
                                <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='nonzero' stroke='%23999' stroke-linecap='square'%3E %3Cpath d='M11.828 6.172l-5.656 5.656M11.828 11.828L6.172 6.172'/%3E %3C/g%3E %3C/svg%3E" alt="delete tags item">`;
        tagParentDiv.appendChild(tagChildDiv);
        tagInput.value = "";

        tagCount += 1;
        tagInput.placeholder = `태그를 입력 후 엔터를 누르면 태그가 생성돼요! (${tagCount}/10)`;
    }
});
// 엔터키를 눌렀을 때 태그 생성

// 태그의 이미지(x)를 눌렀을 때 div 삭제 :: 동적으로 생성된 요소일 때는 부모 요소에 위임
tagParentDiv.addEventListener("click", (e) => {
    e.preventDefault(); // 폼 제출 방지

    if (e.target.tagName == "IMG") {
        e.target.closest(".SocialFeedTagsContainer__TagsItemContainer-sc-2762su-2").remove();

        tagCount -= 1;
        tagInput.placeholder = `태그를 입력 후 엔터를 누르면 태그가 생성돼요! (${tagCount}/10)`;
    }
});

// 모달 열기 함수
function showAlertModal(message) {
    // 모달 요소
    var modal = document.getElementById("alertModal");
    // 모달 닫기 요소
    var closeBtn = document.getElementsByClassName("close")[0];

    modal.querySelector("p").textContent = message;

    modal.style.display = "block";

    // 닫기 버튼 클릭 시 모달 닫기
    closeBtn.onclick = function () {
        modal.style.display = "none";
    };

    // 모달 외부 클릭 시 모달 닫기
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
}

//25.03.18 조승찬 추가 시작

// 파일 추가시 기존 파일에 추가되는 형식으로 모여서 서버로 전송
const fileParentDiv = document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS");
const fileInput = document.querySelector(".InputImageReview__Wrapper-sc-1oapt4s-0.ipbuZD input");
const allFiles = []; // 파일 추가시 기존 파일 유지되게 하기 위한 배열
fileInput.addEventListener("change", (e) => {

    const files = e.target.files;

    Array.from(files).forEach(newFile => {
        // 중복이 있는지 확인하고 없을 때 추가
        const isDup = allFiles.some(existingFile => {
            return existingFile.name == newFile.name && existingFile.size == newFile.size;
        });

        if (!isDup) { // 중복이 없을 때만 추가
            allFiles.push(newFile);
        }
    });

    const formData = new FormData();
    allFiles.forEach( file => {
       formData.append("files", file);
    });

    // const files = e.target.files;
    //
    // // 서버로 보낼 데이타 형태로 변경
    // const formData = new FormData();
    // Array.from(files).forEach((file) => {
    //     formData.append("files", file);
    // });
    // // FormData 내용 출력
    // console.log("--- FormData 내용 출력 ---");
    // for (let [key, value] of formData.entries()) {
    //     console.log(`${key}:`, value);
    //     if (value instanceof File) {
    //         console.log(`\t파일명: ${value.name}`);
    //         console.log(`\t파일 크기: ${value.size} bytes`);
    //         console.log(`\t파일 타입: ${value.type}`);
    //     }
    // }

    // 서버로 전송하여 path와 썸네일 생성
    inputFileUpload(formData);
});

// 새로 생성된 썸네일 삭제버튼 클릭시
// 신규 추가된 파일이 추가될 수 있도록 기존 배열에서 삭제처리
document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS").addEventListener("click", e => {
    if (e.target.className == "file-cancel"){
        const index = e.target.closest("li").dataset.index; // 클릭된곳의 인덱스 찾아오기
        allFiles.splice(index, 1); // 배열에서 파일 제거
        e.target.closest(".uploadFile").remove()
    }
})

// 등록하기 버튼 클릭시 태그와 파일 정보들을 모아서 input value에 세팅
// 텍스트에어리어에 글자가 없으면 경고
document.querySelector(".jULzvQ").addEventListener("click", (e) => {
    if (document.querySelector(".kmqQeB").value.length == 0){
        alert("피드 내용을 입력하세요 ");
        return;
    }

    e.preventDefault(); // 폼 제출 방지

    // DOM 요소가 동적으로 생성되는 경우, 부모 요소에 이벤트를 위임한다.
    // 서버로 전송할 인풋 데이터 생성
    let text = "";
    document.querySelector(".bmexYY-container").querySelectorAll(".bJjaqH span").forEach((span, index) => {
        text += `
	            <input type="hidden" name="tags[${index}]" value="${span.textContent}">
			 `;
    })

    // DOM 요소가 동적으로 생성되는 경우, 부모 요소에 이벤트를 위임한다.
    // 서버로 전송할 인풋 데이터 생성
    document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS").querySelectorAll(".uploadFile").forEach((li, index) => {
        text += `
	            <input type="hidden" name="files[${index}].fileName" value="${li.dataset.fileName}">
	            <input type="hidden" name="files[${index}].filePath" value="${li.dataset.filePath}">
	            <input type="hidden" name="files[${index}].fileSize" value="${li.dataset.fileSize}">
			 `;
    });
    document['real-input-form'].insertAdjacentHTML("beforeend", text)
//  document['real-input-form'].append(text);    //form은 텍스트 형식을 받는데, text는 html 방식이라서 오류가 남

    document['real-input-form'].submit();  // form  제출
});


// 25.03.18 조승찬 추가 끝.
