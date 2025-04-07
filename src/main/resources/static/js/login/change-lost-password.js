const newPassword = document.querySelector(".newPassword"); // 새 비밀번호 input
const checkPassword = document.querySelector(".checkPassword"); // 비밀번호 재확인 input
const passwordText = document.querySelector(".Form__Description-sc-1quypp7-2");
const button = document.querySelector("form>button");   // form 제출버튼
let errorMsg = document.createElement("p"); // 오류문구
errorMsg.classList.add("changeComment");

// 조건: 10자 이상, 영문 / 숫자 / 특수문자 중 2가지 이상 조합해야함
const passwordRegex = /^(?=(.*[a-zA-Z].*[0-9])|([a-zA-Z].*[\W_])|([0-9].*[\W_]))[A-Za-z\d\W_]{10,}$/;

// 비밀번호를 입력할 때
newPassword.addEventListener("keyup", () => {
    console.log("새 비밀번호 입력됨:", newPassword.value);

    const passwordText = document.querySelector(".Form__Description-sc-1quypp7-2");

    
    // 입력한 비밀번호가 비밀번호의 조건에 맞지 않을 시
    if (!passwordRegex.test(newPassword.value) && newPassword.value !== "") {
        newPassword.classList.add("errorInput");
        newPassword.classList.remove("iRBMai");
        passwordText.classList.add("bViOzS");
        passwordText.classList.remove("cIOZzg");
        return;
    }

    newPassword.classList.add("iRBMai");
    newPassword.classList.remove("errorInput");
    passwordText.classList.add("cIOZzg");
    passwordText.classList.remove("bViOzS");

    checkPassword.dispatchEvent(new Event("keyup"));
});

checkPassword.addEventListener("keyup", () => {

    if (checkPassword.nextElementSibling && checkPassword.nextElementSibling.classList.contains("changeComment")) {
        checkPassword.nextElementSibling.remove();
    }

    if (checkPassword.value === newPassword.value && checkPassword.value !== "") {
        button.classList.add("changeButton");
        button.classList.remove("chSrfn");
        button.removeAttribute("disabled");
        checkPassword.classList.remove("errorInput");
        checkPassword.classList.add("iRBMai");
        return;
    }

    checkPassword.classList.remove("iRBMai");
    checkPassword.classList.add("errorInput");
    button.classList.add("chSrfn");
    button.classList.remove("changeButton");
    button.setAttribute("disabled", "");

    errorMsg.innerText = "먼저 입력하신 비밀번호와 일치하지 않습니다.";
    checkPassword.after(errorMsg);
});
