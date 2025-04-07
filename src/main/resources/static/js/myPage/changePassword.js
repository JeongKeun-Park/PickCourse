const newPassword = document.querySelector(".newPassword"); // 새 비밀번호 입력창
const checkPassword = document.querySelector(".checkPassword"); // 비밀번호 확인 입력창
const button = document.querySelector("form>button");   // 폼 제출 버튼
let errorMsg = document.createElement("p"); // 에러메시지 p태그
errorMsg.classList.add("changeComment");    // 에러메시지에 클래스 추가

// 10자 이상, 소문자/대문자, 숫자, 특수문자 중 두 가지 이상 포함
const passwordRegex = /^(?=(.*[A-Za-z])?(.*[\d])?(.*[\W_])?.*){2,}[A-Za-z\d\W_]{10,}$/;

// 새 비밀번호창 입력할 때
newPassword.addEventListener("keyup", () => {
    console.log("새 비밀번호 입력됨:", newPassword.value);

    // 비밀번호 입력칸 밑에 있는 설명 텍스트
    const passwordText = document.querySelector(".Form__Description-sc-1quypp7-2");

    // 입력한 비밀번호가 비밀번호의 조건에 맞지 않을 시
    if (!passwordRegex.test(newPassword.value) && newPassword.value !== "") {
        newPassword.classList.add("errorInput");
        newPassword.classList.remove("iRBMai");
        passwordText.classList.add("bViOzS");
        passwordText.classList.remove("cIOZzg");
        button.setAttribute("disabled", "");
        return;
    }

    // 유효한 비밀번호일 경우 스타일 초기화
    newPassword.classList.add("iRBMai");
    newPassword.classList.remove("errorInput");
    passwordText.classList.add("cIOZzg");
    passwordText.classList.remove("bViOzS");

    checkPassword.dispatchEvent(new Event("keyup"));
});

// 비밀번호 확인창에 입력할 때
checkPassword.addEventListener("keyup", () => {

    //  기존에 존재하는 에러메세지 삭제
    if (checkPassword.nextElementSibling && checkPassword.nextElementSibling.classList.contains("changeComment")) {
        checkPassword.nextElementSibling.remove();
    }

    //  공백이 아니고 새 비밀번호 입력창과 비밀번호 확인 입력창의 비번이 같다면
    if (checkPassword.value === newPassword.value && checkPassword.value !== "") {
        button.classList.add("changeButton");
        button.classList.remove("chSrfn");
        button.removeAttribute("disabled");
        checkPassword.classList.remove("errorInput");
        checkPassword.classList.add("iRBMai");
        return;
    }

    // 새 비밀번호와 비밀번호 확인의 값이 다르다면
    checkPassword.classList.remove("iRBMai");
    checkPassword.classList.add("errorInput");
    button.classList.add("chSrfn");
    button.classList.remove("changeButton");
    button.setAttribute("disabled", "");

    errorMsg.innerText = "먼저 입력하신 비밀번호와 일치하지 않습니다.";
    checkPassword.after(errorMsg);
});
