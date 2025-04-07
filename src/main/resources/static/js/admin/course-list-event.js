// 2025.03.08 조승찬

document.addEventListener("DOMContentLoaded", function () {

    // 25.03.11 조승찬 추가 시작  ==  코스 조회를 위한 처리

    // 날짜 처리를 위한 변수
    const today = new Date().toISOString().split("T")[0];
    let firstDate, secondDate, thirdDate;
    let startDate = 0, endDate = 0, deadline = 0;

    // 포함 사항 불포함 사항 준비물 입력시 태그 생성
    let gcqwwhinclude, gcqwwhexclude, gcqwwhprepare, bDBbNifirst, bDBbNisecond, bDBbNithird;


    // 동적요소 처리를 위한 MutationObserver를 설정
    const listObserver = new MutationObserver((mutationsList) => {
        if (listObserverPause) return;  // 동적요소를 찾은 후에는 일시 정지

        console.log(" 리스트 동적 감시 시작 ");
        for (const mutation of mutationsList) {
            if (mutation.type === "childList") {
                // 날짜 입력창 태그 불러오기
                firstDate = document.querySelector(".gcqwwh.startdate");
                secondDate = document.querySelector(".gcqwwh.enddate");
                thirdDate = document.querySelector(".gcqwwh.deadline");

                // 포함 사항 불포함 사항 준비물 입력시 태그 생성
                gcqwwhinclude = document.querySelector(".gcqwwh.include");
                gcqwwhexclude = document.querySelector(".gcqwwh.exclude");
                gcqwwhprepare = document.querySelector(".gcqwwh.prepare");
                bDBbNifirst = document.querySelector(".bDBbNifirst");
                bDBbNisecond = document.querySelector(".bDBbNisecond");
                bDBbNithird = document.querySelector(".bDBbNithird");

                if (firstDate && secondDate && thirdDate &&
                    gcqwwhinclude && gcqwwhexclude && gcqwwhprepare && bDBbNifirst && bDBbNisecond && bDBbNithird) {
                    // listObserver.disconnect(); // 계속 조회시 감지를 위해서 막음
                    listObserverPause = true;
                    console.log("요소들을 찾았습니다.")
                }
            }
        }
    });
    // 감시할 DOM 노드를 지정 (body 전체를 감시)
    listObserver.observe(document.body, { childList: true, subtree: true });

    // 25.03.11 조승찬 추가  끝    ==  코스 조회를 위한 처리

    let courseType = null;
    let courseId = null;
    document.body.addEventListener("click", e => {
        console.log("click event  :: " + e.className + " " + e.target.className + " " + e.target.tagName);

        // 페이지 번호 클릭시 해당 페이지 조회 요청
        const pageLink = e.target.closest(".change-page");
        if (pageLink) {   // 페이지 이동 처리
            console.log("페이지 이동요청 들어옴");
            e.preventDefault(); // 기본 이벤트 막기

            // const pageValue = pageLink.getAttribute("href");
            const pageValue = pageLink.dataset.page;
            const typeValue = document.querySelector('select[name="type"]').value;
            const keyWordValue = document.querySelector('input[name="keyWord"]').value;
            fetchCourseList(pageValue, typeValue, keyWordValue);
        }

        // 코스 등록 시 코스 등록 요청
        if (e.target.className == "selectCourseBtn") {
            if ((courseType == "봉사 코스" && courseIsVolunteer == 'N') || // 봉사 코스로 생성되지 않은 코스는 봉사 코스로 등록이 안됨.
               (courseType != "봉사 코스" && courseIsVolunteer == 'Y')) {  // 봉사 코스로 생성된 코스는 A B C D로 등록이 안됨.
                return;
            }
            e.preventDefault(); // 기본 이벤트 막기
            const pageValue = document.querySelector('input[name="page"]').value;
            const typeValue = document.querySelector('select[name="type"]').value;
            const keyWordValue = document.querySelector('input[name="keyWord"]').value;
            // 전송할 데이타 json 형태로 변경
            const sendData = {
                courseId: courseId,
                courseType: courseType
            };
            updateCourseType(sendData, pageValue, typeValue, keyWordValue);
        }

        // 검색 클릭시 검색 요청
        if (e.target.classList.contains("fa-courseList-search")) {

            console.log("검색 요청 들어옴");
            e.preventDefault(); // 기본 이벤트 막기
            const pageValue = 1;   // 검색 조회시는 1페이지부터 조회
            const typeValue = document.querySelector('select[name="type"]').value;
            const keyWordValue = document.querySelector('input[name="keyWord"]').value;
            fetchCourseList(pageValue, typeValue, keyWordValue);
        }

        // 리스트 중 클릭된 코스의 상세 조회 요청 => course-detail-service.js
        if (e.target.classList.contains("userListDiv") || e.target.closest(".userListDiv")) {
            if (e.target.tagName == "INPUT") {
                return;
            }
            e.preventDefault(); // 기본 이벤트 막기
            const clickedElement = e.target.classList.contains("userListDiv") ? e.target : e.target.closest(".userListDiv");
            const courseIdDiv = clickedElement.querySelector(".courseIdDiv").textContent.trim();

            console.log("상세 조회요청 들어옴" + " " + courseIdDiv);
            if (courseIdDiv) {
                const pageValue = document.querySelector('input[name="page"]').value;
                const typeValue = document.querySelector('select[name="type"]').value;
                const keyWordValue = document.querySelector('input[name="keyWord"]').value;

                fetchCourseDetail(courseIdDiv, pageValue, typeValue, keyWordValue);
            } else {
                console.warn('No courseIdDiv found !!!');
            }
        }

        //  25.03.11 조승찬 추가 시작 :: 태그의 이미지(x), 케밥버튼
        if (e.target.tagName == "IMG") {
            if (e.target.closest(".bDBbNifirst")) {
                e.target.closest(".jXxsiv").remove();

                firstTagCount = document.querySelectorAll(".bDBbNifirst .jXxsiv").length;
                if (firstTagCount === 0 || firstTagCount == null) {
                    bDBbNifirst.innerHTML = ``;
                }
                gcqwwhinclude.placeholder = `포함 사항 (${firstTagCount}/10)`;
            } else if (e.target.closest(".bDBbNisecond")) {
                e.target.closest(".eMLPLA").remove();

                secondTagCount = document.querySelectorAll(".bDBbNisecond .eMLPLA").length;
                if (secondTagCount === 0 || secondTagCount == null) {
                    bDBbNisecond.innerHTML = ``;
                }
                gcqwwhexclude.placeholder = `불포함 사항 (${secondTagCount}/10)`;
            } else if (e.target.closest(".bDBbNithird")) {
                e.target.closest(".eISlhn").remove();

                thirdTagCount = document.querySelectorAll(".bDBbNithird .eISlhn").length;
                if (thirdTagCount === 0) {
                    bDBbNithird.innerHTML = ``;
                }
                gcqwwhprepare.placeholder = `준비물 (${thirdTagCount}/10)`;
                // 케밥 버튼 클릭시 스케쥴 입력 창 통제
            } else if (e.target.closest(".FvtMb")){

                if (document.querySelector(".DetailOfDateContainer")) {
                    const result = confirm("작성하셨던 스케쥴을 모두 삭제하실건가요?");
                    if (result){
                        document.querySelector(".DetailOfDateContainer").remove();
                    }
                    return;
                }

                startDate = firstDate.value;
                endDate   = secondDate.value;
                deadline  = thirdDate.value;
                console.log("날짜를 봅시다 "+startDate+" "+endDate+" "+deadline);
                if (startDate == 0 || endDate == 0 || deadline == 0) {
                    alert(`날짜를 모두 입력하세요`);
                    return;
                }

                const startDateConv = new Date(startDate);
                const endDateConv = new Date(endDate);
                const days =
                    Math.floor((endDateConv - startDateConv) / (1000 * 60 * 60 * 24)) + 1;

                const detailOfDateContainer = document.createElement("div");
                detailOfDateContainer.className = "DetailOfDateContainer";
                detailOfDateContainer.innerHTML = `<p>계획서를 저장하시려면 입력창을 열어놓고 등록하세요.</p>`;
                for (let i = 0; i < days; i++) {
                    detailOfDateContainer.innerHTML += ` <p>${i + 1}일차 계획서</p>
                        <textarea data-index=${i} placeholder="상세 일정을 적어보세요"
                        maxlength="1200"  class="Textarea__StyledTextarea-sc-1b9phu6-1 kmqQeBdetail"></textarea>
                        <p class="Textarea__Count-sc-1b9phu6-2 jvAusQdetail">0/1200</p>`;
                }
                document.querySelector(".NumberOfPerson").parentNode.insertBefore(
                    detailOfDateContainer,
                    document.querySelector(".NumberOfPerson")
                );

            }
        }
        // 25.03.11 조승찬 추가 끝 :: 태그의 이미지(x), 케밥버튼

    });

    document.body.addEventListener("change", e => {
        console.log("change event  :: "+e.className+" "+e.target.className);

        if (e.target.classList.contains("selectCourseOpt")) { // 코스 선택 드롭다운 메뉴에서 선택한 값 유지
            courseType = e.target.value.trim();
            console.log("선택된 값 변경됨:", courseType);
        }

        if (e.target.classList.contains("usersRadio")) { // 체크된 리스트의 코스 아이디 저장
            const radio = e.target;

            // 선택된 라디오 버튼의 부모 요소 탐색
            const userListDiv = radio.closest(".userListDiv");
            if (userListDiv) {
                courseId   = userListDiv.querySelector(".courseIdDiv").textContent.trim();
                courseIsVolunteer = userListDiv.querySelector(".courseIsVolunteerDiv").textContent.trim();
                console.log("선택된 course.id: "+courseId+"  선택된 course.courseType: "+courseType);
            }
        }

        // 25.03.11 조승찬 추가  시작 ::: 시작일, 종료일, 마감일 체크
        if (e.target.classList.contains("startdate")) {
            startDate = firstDate.value;
            if (startDate <= today) {
                alert(`시작일("${startDate}")은 오늘("${today}") 이후만 가능합니다..`);
                firstDate.value = "";
                startDate = 0;
            } else if (endDate != 0 && endDate < startDate) {
                // alert(`시작 날짜("${startDate}")가 종료 날짜("${endDate}") 보다 큽니다.`);
                secondDate.value = "";
                endDate = 0;
            } else if (deadline != 0 && deadline > startDate) {
                // alert(`시작 날짜("${startDate}")가 마감 날짜("${deadline}") 보다 작습니다.`);
                thirdDate.value = "";
                deadline = 0;
            }
        }

        if (e.target.classList.contains("enddate")) {
            endDate = secondDate.value;
            if (endDate <= today) {
                alert(`종료일("${endDate}")은 오늘("${today}") 이후만 가능합니다.`);
                secondDate.value = "";
                endDate = 0;
            } else if (deadline != 0 && deadline > endDate) {
                // alert(`종료 날짜("${endDate}")가 마감 날짜("${deadline}") 보다 작습니다.`);
                firstDate.value = "";
                thirdDate.value = "";
                startDate = 0;
                deadline = 0;
            } else if (startDate != 0 && startDate > endDate) {
                // alert(`종료 날짜("${endDate}")가 시작 날짜("${startDate}") 보다 작습니다.`);
                firstDate.value = "";
                startDate = 0;
            }
        };

        if (e.target.classList.contains("deadline")) {
            deadline = thirdDate.value;
            if (deadline < today) {
                alert(`마감일("${deadline}")은 오늘("${today}") 부터 가능합니다.`);
                thirdDate.value = "";
                deadline = 0;
            } else if (startDate != 0 && startDate <= deadline) {
                alert(
                    `마감일("${deadline}")이 시작일("${startDate}") 보다 작아야 합니다.`
                );
                thirdDate.value = "";
                deadline = 0;
            } else if (endDate != 0 && endDate <= deadline) {
                alert(
                    `마감일("${deadline}")이 종료일("${endDate}") 보다 작아야 합니다.`
                );
                thirdDate.value = "";
                deadline = 0;
            }
        };

        // 25.03.11 조승찬 추가  끝::: 시작일, 종료일, 마감일 체크

    });

    // 25.03.11 조승찬 추가  시작 :: input 이벤트
    document.body.addEventListener("input", e => {
        console.log("input event  :: "+e.className + " " + e.target.className);

        if (e.target.classList.contains("Textarea_textarea__MWJjO")) {
            document.querySelector(".StorySummaryField_text__ZTEzY").textContent = `${e.target.value.length}/1000`;
        }
        if (e.target.classList.contains("kmqQeBdetail")) {
            e.target.nextElementSibling.textContent = `${e.target.value.length}/1200`;
        }
        if (e.target.classList.contains("Input_courseName")){
            document.querySelector(".HelperMessage_helperMessage__ZTRkO").textContent = `${e.target.value.length}/40`;
        }
    });
    // 25.03.11 조승찬 추가 끝:: input 이벤트

    // 25.03.11 조승찬 추가  시작:: keyup 이벤트
    document.body.addEventListener("keyup", e => {

        if (e.key != "Enter") {
            return;
        };
        console.log("keyup event  :: "+e.className + " " + e.target.className);

        // 포함 사항
        if (e.target.classList.contains("include")){
            let firstTagCount = document.querySelectorAll(".bDBbNifirst .jXxsiv").length;
            if ( firstTagCount > 9) {
                alert(`10개 까지만 입력 가능합니다.`);
                return;
            }
            if (firstTagCount === 0) {
                bDBbNifirst.innerHTML = `<header class="Article__Header-sc-1mmkltm-0 gScFGo">
                                        <hgroup>
                                            <h2 class="Article__Title-sc-1mmkltm-1 bZNoYF">포함 사항</h2>
                                        </hgroup>
                                      </header>
                                      <div class="Stuff__StuffContainer-sc-8zlrc8-0 iXEvmI"></div>`;
            }

            let parentDiv = bDBbNifirst.querySelector(".iXEvmI");
            const firstchildDiv = document.createElement("div");
            firstchildDiv.className = "Tag__RoundTag-sxb61j-1 jXxsiv";
            firstchildDiv.innerHTML = `<span class="includeContent">#${gcqwwhinclude.value}</span>
                     <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='nonzero' stroke='%23999' stroke-linecap='square'%3E %3Cpath d='M11.828 6.172l-5.656 5.656M11.828 11.828L6.172 6.172'/%3E %3C/g%3E %3C/svg%3E" alt="delete tags item">`;
            parentDiv.appendChild(firstchildDiv);
            gcqwwhinclude.value = "";

            firstTagCount += 1;
            gcqwwhinclude.placeholder = `포함 사항 (${firstTagCount}/10)`;
        }

        // 불포함 사항
        if (e.target.classList.contains("exclude")){
            let secondTagCount = document.querySelectorAll(".bDBbNisecond .eMLPLA").length;
            if (secondTagCount > 9) {
                alert(`10개 까지만 입력 가능합니다.`);
                return;
            }
            if (secondTagCount === 0) {
                bDBbNisecond.innerHTML = `<header class="Article__Header-sc-1mmkltm-0 gScFGo">
                                        <hgroup>
                                            <h2 class="Article__Title-sc-1mmkltm-1 bZNoYF">불포함 사항</h2>
                                        </hgroup>
                                      </header>
                                      <div class="Stuff__StuffContainer-sc-8zlrc8-0 iXEvmI"></div>`;
            }

            let parentDiv = bDBbNisecond.querySelector(".iXEvmI");
            const secondchildDiv = document.createElement("div");
            secondchildDiv.className = "Tag__RoundTag-sxb61j-1 eMLPLA";
            secondchildDiv.innerHTML = `<span class="excludeContent">#${gcqwwhexclude.value}</span>
                     <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='nonzero' stroke='%23999' stroke-linecap='square'%3E %3Cpath d='M11.828 6.172l-5.656 5.656M11.828 11.828L6.172 6.172'/%3E %3C/g%3E %3C/svg%3E" alt="delete tags item">`;
            parentDiv.appendChild(secondchildDiv);
            gcqwwhexclude.value = "";

            secondTagCount += 1;
            gcqwwhexclude.placeholder = `불포함 사항 (${secondTagCount}/10)`;
        }

        // 준비물
        if (e.target.classList.contains("prepare")){
            let thirdTagCount = document.querySelectorAll(".bDBbNithird .eISlhn").length;
            if (thirdTagCount > 9) {
                alert(`10개 까지만 입력 가능합니다.`);
                return;
            }
            if (thirdTagCount === 0) {
                bDBbNithird.innerHTML = `<header class="Article__Header-sc-1mmkltm-0 gScFGo">
                                        <hgroup>
                                            <h2 class="Article__Title-sc-1mmkltm-1 bZNoYF">준비물</h2>
                                        </hgroup>
                                      </header>
                                      <div class="Stuff__StuffContainer-sc-8zlrc8-0 iXEvmI"></div>`;
            }

            let parentDiv = bDBbNithird.querySelector(".iXEvmI");
            const thirdchildDiv = document.createElement("div");
            thirdchildDiv.className = "Tag__RoundTag-sxb61j-1 eISlhn";
            thirdchildDiv.innerHTML = `<span class="prepareContent">#${gcqwwhprepare.value}</span>
                     <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='nonzero' stroke='%23999' stroke-linecap='square'%3E %3Cpath d='M11.828 6.172l-5.656 5.656M11.828 11.828L6.172 6.172'/%3E %3C/g%3E %3C/svg%3E" alt="delete tags item">`;
            parentDiv.appendChild(thirdchildDiv);
            gcqwwhprepare.value = "";

            thirdTagCount += 1;
            gcqwwhprepare.placeholder = `준비물 (${thirdTagCount}/10)`;
        }

    });

    // 25.03.11 조승찬 추가 끝 :: keyup 이벤트
});
