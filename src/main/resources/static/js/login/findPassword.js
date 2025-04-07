const inputChange = document.querySelectorAll("div input");

inputChange.forEach((input) => {
    input.addEventListener("focus", (e) => {
        e.target.style.border = "1px solid blue";
    });

    input.addEventListener("blur", (e) => {
        e.target.style.border = "1px solid rgb(238, 238, 238)";
    });
});

// 이메일 발송버튼 기본으로 못 쓰게 막아놓기(유효성 검사 통과 후에만 submit 가능)
const form = document.querySelector("form"); // 폼 선택

form.addEventListener("submit", (e) => {
    if (!emailSendButton.classList.contains("buttonChange")) {
        e.preventDefault(); // 버튼이 활성화되지 않았으면 제출 방지
        emailSendButton.disabled = true;
    }
});

//  이메일 유효성 검사
const emailInput = document.querySelector("input[name='email']");
const formDescription = document.querySelector(".Form__Description-sc-1quypp7-2");
const emailSendButton = document.querySelector(".Button-bqxlp0-0");

const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

// 스타일을 위한 클래스를 간결하게 변경하기 위해 만듦
function updateStyles(inputElement, descriptionElement, buttonElement, inputClass, descriptionClass, descriptionText, buttonClass) {
    inputElement.classList.remove("iRBMai", "inputError");
    inputElement.classList.add(inputClass);
    descriptionElement.classList.remove("cIOZzg", "errorMessage");
    descriptionElement.classList.add(descriptionClass);
    descriptionElement.textContent = descriptionText;
    buttonElement.classList.remove("ppTTf", "buttonChange");
    buttonElement.classList.add(buttonClass);
}

// input에서 이메일을 입력할 때 형식 검사
emailInput.addEventListener("keyup", (e) => {
    const emailValue = e.target.value.trim();

    if (emailValue && !pattern.test(emailValue)) {
        // 올바르지 않은 이메일 형식
        updateStyles(
            emailInput, formDescription, emailSendButton,
            "inputError", "errorMessage", "올바른 이메일 형식이 아닙니다.", "ppTTf"
        );
        emailSendButton.disabled = true; // 버튼 비활성화
    } else if (pattern.test(emailValue) && emailValue) {
        // 유효한 이메일 형식
        updateStyles(
            emailInput, formDescription, emailSendButton,
            "iRBMai", "cIOZzg", "올바른 이메일 형식입니다.", "buttonChange"
        );
        emailSendButton.disabled = false; // 버튼 활성화
    } else if (!emailValue) {
        // 이메일 입력이 비어있을 때
        updateStyles(
            emailInput, formDescription, emailSendButton,
            "iRBMai","cIOZzg","재설정하려는 비밀번호의 아이디(이메일)를 입력해 주세요.","ppTTf"
        );
        emailSendButton.disabled = true; // 버튼 비활성화
    }
});

// 이메일 발송하는 동안 로딩 gif 출력
// 기본으론 둘 다 숨겨놓음
document.getElementById("loading").style.display = "none"
document.getElementById("overlay").style.display = "none"

// 버튼을 누르고 나서 페이지 이동할때까지 뜸
emailSendButton.addEventListener("click", () => {
    if(emailSendButton.classList.contains("buttonChange")){
        document.getElementById("loading").style.display = "block"
        document.getElementById("overlay").style.display = "block"
    }
});