const phoneInput = document.querySelector(".phoneInput");
const sendButton = document.querySelector(".numberSend");

phoneInput.addEventListener("keyup", (e) => {
    const deleteP = phoneInput.nextElementSibling;
    if (deleteP && deleteP.classList.contains("errorMessage")) {
        deleteP.remove();
    }

    if (phoneInput.value.length >= 10 && phoneInput.value.length <= 11) {
        // phoneInput의 길이가 10보다 크거나 같을때
        // 아래의 클래스를 추가하고 삭제함에 따라 인증번호전송 버튼의 스타일이 변경
        phoneInput.classList.add("iRBMai");
        phoneInput.classList.remove("errorBorder");
        sendButton.classList.add("buttonBlack");
        sendButton.classList.remove("gsRKCU");
        deleteP.remove();
    } else {
        phoneInput.classList.add("errorBorder");
        phoneInput.classList.remove("iRBMai");
        sendButton.classList.add("gsRKCU");
        sendButton.classList.remove("buttonBlack");

        const p = document.createElement("p");
        p.classList.add("errorMessage");
        p.innerHTML = "올바른 휴대폰 번호 형식이 아닙니다.";
        e.target.after(p);
    }
});

const checkNumber = document.querySelector(".checkNumber");
const confirmButton = document.querySelector(".confirmButton");

checkNumber.addEventListener("keyup", (e) => {
    const deleteP = checkNumber.nextElementSibling;
    if (deleteP && deleteP.classList.contains("errorMessage")) {
        deleteP.remove();
    }

    if (checkNumber.value.length === 6) {
        checkNumber.classList.add("iRBMai");
        checkNumber.classList.remove("errorBorder");
        confirmButton.classList.add("buttonBlue");
        confirmButton.classList.remove("jquNPr");
    } else {
        checkNumber.classList.add("errorBorder");
        checkNumber.classList.remove("iRBMai");
        confirmButton.classList.add("jquNPr");
        confirmButton.classList.remove("buttonBlue");

        const p = document.createElement("p");
        p.classList.add("errorMessage");
        p.textContent = "올바른 인증번호 형식이 아닙니다.";
        e.target.after(p);
    }
});

// ====================================================================================
document.addEventListener("DOMContentLoaded", function () {
    let isVerified = false;
    const phoneInput = document.querySelector(".phoneInput");
    const sendButton = document.querySelector(".numberSend");
    const signupButton = document.querySelector("#signupSubmit");

    // 비활성화 스타일 설정
    signupButton.style.cursor = "not-allowed";
    signupButton.style.backgroundColor = "#ccc"; // 회색 (비활성화)
    signupButton.style.opacity = "0.5"; // 반투명 처리

    //인증번호 전송 버튼 클릭 이벤트 (중복 방지)
    if (!sendButton.hasAttribute("data-listener")) {
        sendButton.setAttribute("data-listener", "true");
        sendButton.addEventListener("click", function () {
            let memberTell = document.querySelector("input[name='memberTell']").value.trim();

            if (!memberTell || memberTell.length < 10) {
                alert("올바른 휴대폰 번호를 입력하세요.");
                return;
            }

            fetch("/auth/send-code", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: new URLSearchParams({ memberTell: memberTell })
            })
                .then(response => response.json())
                .then(data => {
                    console.log("서버 응답:", data); // 서버 응답 로그 출력
                    if (data.error) {
                        alert(data.error);
                    } else {
                        alert(data.message);
                    }
                })
                .catch(error => console.error("Error:", error));
        });
    }

    //인증하기 버튼 클릭 이벤트
    const confirmButton = document.querySelector(".confirmButton");
    if (!confirmButton.hasAttribute("data-listener")) {
        confirmButton.setAttribute("data-listener", "true");
        confirmButton.addEventListener("click", function () {
            let inputCode = document.querySelector("input[name='verificationCodeInput']").value.trim();

            if (!inputCode || inputCode.length !== 6) {
                alert("올바른 6자리 인증번호를 입력하세요.");
                return;
            }

            fetch("/auth/verify-code", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: new URLSearchParams({ verificationCode: inputCode })
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        alert("인증 성공!");
                        confirmButton.classList.add("buttonBlue");
                        isVerified = true;

                        //가입 버튼 활성화 스타일 변경
                        signupButton.removeAttribute("disabled");
                        signupButton.style.cursor = "pointer";
                        signupButton.style.backgroundColor = "#5a8dee"; // 푸르스름한 색
                        signupButton.style.opacity = "1"; // 불투명하게 변경

                    } else {
                        alert("인증번호가 일치하지 않습니다.");
                    }
                })
                .catch(error => console.error("Error:", error));
        });
    }

    //회원가입 버튼 클릭 시 인증 여부 확인
    document.querySelector("#signupForm").addEventListener("submit", function (event) {
        if (!isVerified) {
            event.preventDefault();
            alert("휴대폰 인증을 완료해야 가입할 수 있습니다.");
        }
    });
});
