let menuBtn = document.querySelector(".AppLayout_expandNavButton__NTEwM");
let nav = document.querySelector(".AppNavbarLayout_container__NmY5O");

menuBtn.addEventListener("click", function () {
    nav.classList.toggle("active");
});

let div = document.querySelector(".AppLayout_contents__Nzg1Z");
menuBtn.addEventListener("click", function () {
    div.classList.toggle("active");
});

menuBtn.addEventListener("click", function () {
    menuBtn.classList.toggle("active");
});

// 시작일자가 오늘 날짜보다 작은지 확인. 종료일자가 시작일자보다 적은지 확인. 모집 마감일자가 종료일자보다 적은지 확인
const firstDate = document.querySelector(".gcqwwh.startdate");
const secondDate = document.querySelector(".gcqwwh.enddate");
const thirdDate = document.querySelector(".gcqwwh.deadline");
const today = new Date().toISOString().split("T")[0];
let startDate = 0;
let endDate = 0;
let deadline = 0;
firstDate.addEventListener("change", () => {
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
});

secondDate.addEventListener("change", () => {
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
});

thirdDate.addEventListener("change", () => {
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
});

// 케밥버튼을 눌러서 시작일 부터 종료일까지 상세 일정 입력
const kebabmenu = document.querySelector(".FvtMb");
const numberOfPerson = document.querySelector(".NumberOfPerson");
const detailOfDateContainer = document.createElement("div");
detailOfDateContainer.className = "DetailOfDateContainer";

kebabmenu.addEventListener("click", () => {
    if (document.querySelector(".DetailOfDateContainer")) {
        document.querySelector(".DetailOfDateContainer").remove();
        return;
    }

    if (startDate == 0 || endDate == 0 || deadline == 0) {
        alert(`날짜를 모두 입력하세요`);
        return;
    }

    detailOfDateContainer.innerHTML = `<p>계획서를 저장하시려면 입력창을 열어놓고 등록하세요.</p>`;
    const startDateConv = new Date(startDate); // 날짜 객체로 변환해야 계산이 가능함.
    const endDateConv = new Date(endDate);
    const days =
        Math.floor((endDateConv - startDateConv) / (1000 * 60 * 60 * 24)) + 1;
    for (let i = 0; i < days; i++) {
        detailOfDateContainer.innerHTML += ` <p>${i + 1}일차 계획서</p>
            <textarea data-index=${i} placeholder="상세 일정을 적어보세요"
            maxlength="1200"  class="Textarea__StyledTextarea-sc-1b9phu6-1 kmqQeBdetail"></textarea>
            <p class="Textarea__Count-sc-1b9phu6-2 jvAusQdetail">0/1200</p>`;
    }
    numberOfPerson.parentNode.insertBefore(
        detailOfDateContainer,
        numberOfPerson
    );

    // textarea에 글자 입력시 입력된 글자 수 보여주기
    document
        .querySelector(".DetailOfDateContainer")
        .addEventListener("input", (e) => {
            if (e.target.classList.contains("kmqQeBdetail")) {
                e.target.nextElementSibling.textContent = `${e.target.value.length}/1200`;
            }
        });
    // textarea에 글자 입력시 입력된 글자 수 보여주기
});
// 케밥버튼을 눌러서  시작일 부터 종료일까지 상세 일정 입력

// 포함 사항 불포함 사항 준비물 입력시 태그 생성
const gcqwwhinclude = document.querySelector(".gcqwwh.include"); // 포함 사항
const gcqwwhexclude = document.querySelector(".gcqwwh.exclude"); // 불포함 사항
const gcqwwhprepare = document.querySelector(".gcqwwh.prepare"); // 준비물
const bDBbNifirst = document.querySelector(".bDBbNifirst");
const bDBbNisecond = document.querySelector(".bDBbNisecond");
const bDBbNithird = document.querySelector(".bDBbNithird");

let firstTagCount = 0;
let secondTagCount = 0;
let thirdTagCount = 0;
let parentDiv = ``;
gcqwwhinclude.addEventListener("keyup", (e) => {
    if (e.key == "Enter") {
        if (firstTagCount > 9) {
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

        parentDiv = bDBbNifirst.querySelector(".iXEvmI");
        const firstchildDiv = document.createElement("div");
        firstchildDiv.className = "Tag__RoundTag-sxb61j-1 jXxsiv";
        firstchildDiv.innerHTML = `<span class="includeContent">#${gcqwwhinclude.value}</span>
                     <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='nonzero' stroke='%23999' stroke-linecap='square'%3E %3Cpath d='M11.828 6.172l-5.656 5.656M11.828 11.828L6.172 6.172'/%3E %3C/g%3E %3C/svg%3E" alt="delete tags item">`;
        parentDiv.appendChild(firstchildDiv);
        gcqwwhinclude.value = "";

        firstTagCount += 1;
        gcqwwhinclude.placeholder = `포함 사항 (${firstTagCount}/10)`;
    }
});

gcqwwhexclude.addEventListener("keyup", (e) => {
    if (e.key == "Enter") {
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

        parentDiv = bDBbNisecond.querySelector(".iXEvmI");
        const secondchildDiv = document.createElement("div");
        secondchildDiv.className = "Tag__RoundTag-sxb61j-1 eMLPLA";
        secondchildDiv.innerHTML = `<span class="excludeContent">#${gcqwwhexclude.value}</span>
                     <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='nonzero' stroke='%23999' stroke-linecap='square'%3E %3Cpath d='M11.828 6.172l-5.656 5.656M11.828 11.828L6.172 6.172'/%3E %3C/g%3E %3C/svg%3E" alt="delete tags item">`;
        parentDiv.appendChild(secondchildDiv);
        gcqwwhexclude.value = "";

        secondTagCount += 1;
        gcqwwhexclude.placeholder = `불포함 사항 (${secondTagCount}/10)`;
    }
});

gcqwwhprepare.addEventListener("keyup", (e) => {
    if (e.key == "Enter") {
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

        parentDiv = bDBbNithird.querySelector(".iXEvmI");
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

// 태그의 이미지(x)를 눌렀을 때 div 삭제 :: 동적으로 생성된 요소일 때는 부모 요소에 위임
bDBbNifirst.addEventListener("click", (e) => {
    if (e.target.tagName == "IMG") {
        e.target.closest(".jXxsiv").remove();

        firstTagCount -= 1;
        if (firstTagCount === 0) {
            bDBbNifirst.innerHTML = ``;
        }
        gcqwwhinclude.placeholder = `포함 사항 (${firstTagCount}/10)`;
    }
});

bDBbNisecond.addEventListener("click", (e) => {
    if (e.target.tagName == "IMG") {
        e.target.closest(".eMLPLA").remove();

        secondTagCount -= 1;
        if (secondTagCount === 0) {
            bDBbNisecond.innerHTML = ``;
        }
        gcqwwhexclude.placeholder = `불포함 사항 (${secondTagCount}/10)`;
    }
});

bDBbNithird.addEventListener("click", (e) => {
    if (e.target.tagName == "IMG") {
        e.target.closest(".eISlhn").remove();

        thirdTagCount -= 1;
        if (thirdTagCount === 0) {
            bDBbNithird.innerHTML = ``;
        }
        gcqwwhprepare.placeholder = `준비물 (${thirdTagCount}/10)`;
    }
});
// 태그의 이미지(x)를 눌렀을 때 div 삭제 :: 동적으로 생성된 요소일 때는 부모 요소에 위임

document.addEventListener("DOMContentLoaded", function () {
    const volunteerBox = document.querySelector(".volunteerBox");
    const durationContainer = document.querySelector(
        ".DurationOfTourContainer"
    );
    const durationContainer1 = document.querySelector(
        ".DurationOfTourContainer1"
    );
    const MaxMinPersonnel = document.querySelector(".MaxMinPersonnel");

    if (!volunteerBox || !durationContainer || !durationContainer1) {
        console.error("요소를 찾을 수 없습니다. 클래스명을 확인하세요.");
        return;
    }

    volunteerBox.addEventListener("change", function () {

        if (this.checked) {
            durationContainer.classList.remove("hidden");
            durationContainer1.classList.remove("hidden");
            MaxMinPersonnel.classList.remove("hidden");
        } else {
            durationContainer.classList.add("hidden");
            durationContainer1.classList.add("hidden");
            MaxMinPersonnel.classList.add("hidden");
        }
    });
});

// ✅ 1. 카카오 지도 설정
let mapContainer = document.getElementById("map"),
    mapOption = {
        center: new kakao.maps.LatLng(35.409476, 127.396059),
        level: 9,
    };
let map = new kakao.maps.Map(mapContainer, mapOption);

let tourSpots = [];
let geocoder = new kakao.maps.services.Geocoder();
let clickLine;
let dotOverlays = [];
let textOverlays = [];
let totalDistanceOverlay;

// ✅ 총 거리 입력창 가져오기
const totalDistanceInput = document.querySelector(".max");

// ✅ HTML 요소 가져오기
const searchInput = document.querySelector(".noBtnStyle");
const destinationList = document.getElementById("destinationList");

// ✅ 주소 입력 이벤트 리스너 (중복 실행 방지)
function handleAddressEnter(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        addDestination();
    }
}

// ✅ X 버튼 이벤트 위임 (삭제 기능)
document.addEventListener("click", function (event) {
    if (event.target.classList.contains("closeBtn")) {
        let index = event.target.dataset.index;
        removeDestination(index);
    }
});

// ✅ 지도에 표시할 말풍선(목적지 태그) 생성 함수
function createOverlayContent(spot, index) {
    return `<div class="dotOverlay addedDestination">
                <b>${spot.number}. ${spot.title}</b><br>
                ${spot.address}
                <img src="http://t1.daumcdn.net/localimg/localimages/07/mapjsapi/2x/bt_close.gif" 
                     class="closeBtn" 
                     data-index="${index}" 
                     style="cursor:pointer; vertical-align: middle; margin-left: 5px; margin-bottom: 2px; width: 14px; height: 14px;">
            </div>`;
}

// ✅ 목적지 리스트 UI에 추가하는 함수
function addDestinationToList(spot, index) {
    let listItem = document.createElement("li");
    listItem.style.display = "flex";
    listItem.style.justifyContent = "space-between";
    listItem.style.padding = "5px";
    listItem.style.borderBottom = "1px solid #ddd";

    let spotText = document.createElement("span");
    spotText.innerHTML = `<b>${spot.number}. ${spot.title}</b><br>${spot.address}`;
    spotText.style.cursor = "pointer";

    spotText.addEventListener("click", () => map.setCenter(spot.latlng));

    let deleteBtn = document.createElement("img");
    deleteBtn.src =
        "http://t1.daumcdn.net/localimg/localimages/07/mapjsapi/2x/bt_close.gif";
    deleteBtn.classList.add("closeBtn");
    deleteBtn.dataset.index = index;
    deleteBtn.style.cursor = "pointer";
    deleteBtn.style.marginLeft = "5px";
    deleteBtn.style.marginBottom = "2px";
    deleteBtn.style.width = "14px";
    deleteBtn.style.height = "14px";

    listItem.appendChild(spotText);
    listItem.appendChild(deleteBtn);
    destinationList.appendChild(listItem);
}

// ✅ 목적지 삭제 함수 (마지막 항목도 삭제 가능 & 번호 재정렬)
function removeDestination(index) {
    index = parseInt(index);

    if (index >= 0 && index < tourSpots.length) {
        // 지도에서 오버레이 및 라인 제거
        dotOverlays[index].setMap(null);
        textOverlays[index].setMap(null);

        // 배열에서도 삭제
        tourSpots.splice(index, 1);
        dotOverlays.splice(index, 1);
        textOverlays.splice(index, 1);

        // UI 목록에서도 삭제
        let listItems = document.querySelectorAll("#destinationList li");
        listItems[index].remove();


        // 25.03.12 조승찬 추가 시작 :: 지도에 나오는 태그 순번 수정
        // 기존 모든 textOverlays 제거
        textOverlays.forEach(overlay => overlay.setMap(null));
        // textOverlays 비우기
        textOverlays = [];
        // 25.03.12 조승찬 추가 시작 :: 지도에 나오는 태그 순번 수정
        // 삭제 후 번호 다시 정렬
        tourSpots.forEach((spot, i) => {
            spot.number = i + 1;

            // // 25.03.12 조승찬 추가 시작 :: 지도에 나오는 태그 순번 수정
            let textOverlay = new kakao.maps.CustomOverlay({
                content: createOverlayContent(spot, i),
                position: spot.latlng,
                yAnchor: 1.2,
                zIndex: 2,
            });
            textOverlay.setMap(map);
            textOverlays.push(textOverlay);
            // // 25.03.12 조승찬 추가 시작 :: 지도에 나오는 태그 순번 수정
        });

        // 전체 UI 다시 렌더링 (정확한 번호 정렬 보장)
        refreshDestinationList();

        // 경로 업데이트
        updateRoute();
    }
}

// ✅ 목적지 목록 UI 새로고침 (번호 재정렬)
function refreshDestinationList() {
    destinationList.innerHTML = "";
    tourSpots.forEach((spot, index) => {
        addDestinationToList(spot, index);
    });
}

// ✅ 목적지 추가 함수
function addDestination() {
    if (tourSpots.length >= 10) {
        alert("목적지는 최대 10개까지만 추가할 수 있습니다! 🚫");
        return;
    }

    let inputAddress = searchInput.value.trim();
    if (!inputAddress) {
        alert("주소를 입력하세요!");
        return;
    }

    let tempAddress = inputAddress;
    searchInput.value = "";
    searchInput.placeholder = "제목을 입력하세요.";

    searchInput.removeEventListener("keypress", handleAddressEnter);
    searchInput.addEventListener("keypress", handleTitleEnter);

    setTimeout(() => {
        searchInput.focus();
    }, 10);

    function handleTitleEnter(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            let inputTitle = searchInput.value.trim();
            if (!inputTitle) {
                alert("제목을 입력하세요!");
                return;
            }

            searchInput.value = "";
            searchInput.placeholder = "주소를 입력하세요.";
            searchInput.removeEventListener("keypress", handleTitleEnter);
            searchInput.addEventListener("keypress", handleAddressEnter);

            geocoder.addressSearch(tempAddress, (result, status) => {
                if (status === kakao.maps.services.Status.OK) {
                    let newLatLng = new kakao.maps.LatLng(
                        result[0].y,
                        result[0].x
                    );
                    let spotNumber = tourSpots.length + 1;

                    let newSpot = {
                        number: spotNumber,
                        title: inputTitle,
                        address: tempAddress,
                        latlng: newLatLng,
                    };

                    let index = tourSpots.length;
                    tourSpots.push(newSpot);

                    let dotOverlay = new kakao.maps.CustomOverlay({
                        content: `<span class="dot"></span>`,
                        position: newLatLng,
                        zIndex: 1,
                    });
                    dotOverlay.setMap(map);
                    dotOverlays.push(dotOverlay);

                    let textOverlay = new kakao.maps.CustomOverlay({
                        content: createOverlayContent(newSpot, index),
                        position: newLatLng,
                        yAnchor: 1.2,
                        zIndex: 2,
                    });
                    textOverlay.setMap(map);
                    textOverlays.push(textOverlay);

                    addDestinationToList(newSpot, index);
                    map.setCenter(newLatLng);
                    updateRoute();
                } else {
                    alert("🚫 주소를 찾을 수 없습니다. 다시 입력해주세요.");
                }
            });

            searchInput.value = "";
        }
    }
}

// ✅ 지도 경로 업데이트 (총 거리 정상 표시)
// 25.03.12 조승찬 수정 시작 :: 총거리 보여지도록 수정
function updateRoute() {
    if (clickLine) clickLine.setMap(null);
    if (totalDistanceOverlay) totalDistanceOverlay.setMap(null);

    let linePath = tourSpots.map((spot) => spot.latlng);

    if (tourSpots.length < 2) {
        if (previousDistanceOverlay) {
            previousDistanceOverlay.setMap(null);  // 기존 오버레이 삭제
        }
        totalDistanceInput.value = "0 km";
        return;
    }

    clickLine = new kakao.maps.Polyline({
        map: map,
        path: linePath,
        strokeWeight: 3,
        strokeColor: "#db4040",
        strokeOpacity: 1,
        strokeStyle: "solid",
    });

    let totalDistance = (clickLine.getLength() / 1000).toFixed(1);


    totalDistanceInput.value = `${totalDistance} km`;
    // // ✅ 거리 정보 HTML 생성 및 표시
    let distanceInfoHTML = getTimeHTML(totalDistance);
    showDistance(distanceInfoHTML, linePath[linePath.length - 1], map);
}
// 25.03.12 조승찬 수정 끝 :: 총거리 보여지도록 수정

// 선을 그리는 함수
function drawLine(tourSpots, map) {
    let linePath = [];
    tourSpots.forEach((tourSpot) => {
        linePath.push(tourSpot.latlng);
    });

    clickLine = new kakao.maps.Polyline({
        map: map,
        path: linePath,
        strokeWeight: 3,
        strokeColor: "#db4040",
        strokeOpacity: 1,
        strokeStyle: "solid",
    });

    // 각 지점에 대한 거리 정보 표시
    let distance = (Math.round(clickLine.getLength()) / 1000).toFixed(1);
    let path = clickLine.getPath();
    displayCircleDot(tourSpots[0].latlng, distance, map);

    let content = getTimeHTML(distance); // 거리와 시간을 HTML로 변환
    showDistance(content, path[path.length - 1], map);
}


// 거리와 시간을 표시하는 함수
function getTimeHTML(distance) {
    // 도보 시간 계산
    let walkkTime = (distance / 0.067) | 0;
    let walkHour = "",
        walkMin = "";

    if (walkkTime > 60) {
        walkHour =
            '<span class="number">' +
            Math.floor(walkkTime / 60) +
            "</span>시간 ";
    }
    walkMin = '<span class="number">' + (walkkTime % 60) + "</span>분";

    // 자전거 시간 계산
    let bycicleTime = (distance / 0.227) | 0;
    let bycicleHour = "",
        bycicleMin = "";

    if (bycicleTime > 60) {
        bycicleHour =
            '<span class="number">' +
            Math.floor(bycicleTime / 60) +
            "</span>시간 ";
    }
    bycicleMin = '<span class="number">' + (bycicleTime % 60) + "</span>분";

    let content = '<ul class="dotOverlay distanceInfo">';
    content += "    <li>";
    content +=
        '        <span class="label">총거리</span><span class="number">' +
        distance +
        "</span>Km";
    content += "    </li>";
    content += "    <li>";
    content += '        <span class="label">도보</span>' + walkHour + walkMin;
    content += "    </li>";
    content += "    <li>";
    content +=
        '        <span class="label">자전거</span>' + bycicleHour + bycicleMin;
    content += "    </li>";
    content += "</ul>";

    return content;
}


// 거리 표시 함수
let previousDistanceOverlay = null;
function showDistance(content, position, map) {   // displayCircleDot 에 유사 기능 있음
    // 이전에 표시된 오버레이가 있다면 삭제
    if (previousDistanceOverlay) {
        previousDistanceOverlay.setMap(null);  // 기존 오버레이 삭제
    }

    const distanceOverlay = new kakao.maps.CustomOverlay({
        map: map,
        content: content,
        position: position,
        xAnchor: 0,
        yAnchor: -1,
        zIndex: 3,
    });

    previousDistanceOverlay = distanceOverlay;
}

// 지점에 빨간 동그라미 표시하는 함수
function displayCircleDot(position, distance, map) {   //createMarkers, showDistance 를 합친 기능과 유사
    const circleOverlay = new kakao.maps.CustomOverlay({
        content: '<span class="dot"></span>',
        position: position,
        zIndex: 1,
    });
    circleOverlay.setMap(map);

    // if (distance > 0) {
    //     const distanceOverlay = new kakao.maps.CustomOverlay({
    //         content:
    //             '<div class="dotOverlay">거리 <span class="number">' +
    //             distance +
    //             "</span>Km</div>",
    //         position: position,
    //         yAnchor: 2.2,
    //         zIndex: 2,
    //     });
    //     distanceOverlay.setMap(map);
    // }
}


// 화면 확장 축소
document.querySelector("#fullMap").addEventListener("click", (e) => {
    if (mapContainer.style.position === "fixed") {
        mapContainer.style.position = "relative";
        mapContainer.style.width = "100%";
        mapContainer.style.height = "40vh";
        mapContainer.style.zIndex = ""; // 맵이 다른 요소 위에 오도록 설정한거 해제
        document.querySelector("#fullMap").style.position = "absolute";
        // 지도의 중심을 새로운 좌표로 설정
        map.relayout();
        map.setCenter(initialCenter);
    } else {
        mapContainer.style.position = "fixed";
        mapContainer.style.top = "0";
        mapContainer.style.left = "0";
        mapContainer.style.width = "100%";
        mapContainer.style.height = "100vh";
        mapContainer.style.zIndex = "1000"; // 맵이 다른 요소 위에 오도록 설정
        document.querySelector("#fullMap").style.position = "fixed";
        // 지도의 중심을 새로운 좌표로 설정
        map.relayout();
        map.setCenter(initialCenter);
    }
});
// 화면 확장 축소

// ✅ 중복 리스너 제거 후 다시 등록
searchInput.removeEventListener("keypress", handleAddressEnter);
searchInput.addEventListener("keypress", handleAddressEnter);

//================================================================================
//================================================================================
//================================================================================
// 저장/수정 버튼 요소 가져오기
const saveButton = document.querySelector(
    ".StoryFormPage_saveButtonWrapper__Y2FmO .Button_button__YmRmM"
);

// "더보기 버튼" 요소 가져오기
const moreButton = document.querySelector(".FvtMb");

// 임시저장 버튼 요소 가져오기
const tempSaveButton = document.querySelector(
    ".SaveButtonFooter_btnWrapper__ZTk3Z .Button_button__YmRmM"
);

// "추천 코스 작성" 제목 요소 가져오기
const courseTitle = document.querySelector(".FundingPage_title__YTViN");

// "추천 코스를 소개해 주세요." 문구 요소 가져오기
const courseDescription = document.querySelector(
    ".FundingPage_description__NDA1Z"
);

// "봉사 코스" 체크박스 요소 가져오기
const volunteerCheckbox = document.querySelector("#volunteerBox");

// 필수 입력 필드 가져오기 (주소 제외)
const requiredInputs = document.querySelectorAll(
    "input:not([type='date']):not(.include):not(.exclude):not(.prepare):not(.noBtnStyle), textarea"
);

// "주소를 입력하세요" 필드 및 태그 컨테이너 가져오기
const addressTagContainer = document.getElementById("destinationList");

// 봉사 코스가 체크된 경우 필수 입력될 날짜 필드 가져오기
const dateInputs = document.querySelectorAll(
    ".DurationOfTourContainer input[type='date']"
);

// 모든 입력 필드 가져오기 (비활성화/활성화 시 사용)
const allInputs = document.querySelectorAll("input, textarea, select");

// "저장" 버튼 클릭 이벤트
saveButton.removeEventListener("click", handleSaveClick);
saveButton.addEventListener("click", handleSaveClick);

// "저장" 버튼 클릭 시 실행될 함수
function handleSaveClick() {
    if (saveButton.textContent.trim() === "저장") {
        let missingFields = [];
        // if (saveButton.textContent.trim() === "저장") {
        //     // "더보기 버튼" 비활성화 (봉사 코스 관련)
        //     moreButton.style.pointerEvents = "none";
        //     moreButton.style.opacity = "0.5";
        // } else {
        //     // "수정" 버튼 클릭 시 "더보기 버튼" 다시 활성화
        //     moreButton.style.pointerEvents = "auto";
        //     moreButton.style.opacity = "1";
        // }

        // 필수 입력 체크
        requiredInputs.forEach((input) => {
            if (input.type === "hidden" || input.type === "file") {
                return; // hidden 또는 file input은 필수 입력 체크에서 제외
            }

            if (!document.querySelector("#volunteerBox").checked &&
                (input.classList.contains("max-personnel") ||
                 input.classList.contains("min-personnel") )) {
                return;
            }

            if (input.value.trim() === "") {
                let fieldName =
                    input.placeholder || input.className || "입력 항목";
                missingFields.push(fieldName.replace(/_/g, " "));
            }
        });

        // "주소를 입력하세요" 필드에서 최소한 하나의 태그(목적지)가 추가되었는지 확인
        if (addressTagContainer.children.length === 0) {
            missingFields.push("주소를 입력하세요 (목적지를 추가해주세요)");
        }

        // "봉사 코스"가 체크된 경우, 날짜 필드도 필수 입력 체크
        if (volunteerCheckbox.checked) {
            dateInputs.forEach((input) => {
                if (input.value.trim() === "") {
                    missingFields.push(input.placeholder || "날짜 항목");
                }
            });
        }

        if (missingFields.length > 0) {
            alert(
                "다음 항목을 입력해야 합니다:\n\n- " +
                    missingFields.join("\n- ")
            );
            return;
        }

    }
}


// 2025.03.07 조승찬 추가
// 체크 박스 클릭시
document.querySelector(".volunteerBox").addEventListener("change", e => {
    document.querySelector("#courseIsVolunteer").value = e.target.checked ? 'Y' : 'N';
})

// 제목 textarea에 글자 입력시 입력된 글자 수 보여주기
document
    .querySelector(".Input_input__M2Q3Y.Input_lg__MDE4M")
    .addEventListener("input", (e) => {
        document.querySelector(".HelperMessage_helperMessage__ZTRkO").textContent = `${e.target.value.length}/40`;
    });
// textarea에 글자 입력시 입력된 글자 수 보여주기

// 코스요약 textarea에 글자 입력시 입력된 글자 수 보여주기
document
    .querySelector(".Textarea_textarea__MWJjO")
    .addEventListener("input", (e) => {
        document.querySelector(".StorySummaryField_text__ZTEzY").textContent = `${e.target.value.length}/1000`;
    });
// textarea에 글자 입력시 입력된 글자 수 보여주기

document.addEventListener("DOMContentLoaded", function () { // HTML이 로드된 후 실행되도록 보장

// 저장 버튼 클릭시
    document.querySelector(".Button_children__NzZlO").addEventListener("click", (button) => {

        // 봉사코스일 경우  최대모집인원 최소모집인원 체크
        if (document.querySelector("#volunteerBox").checked){
            const max = parseInt(document.querySelector(".gcqwwh.max-personnel").value, 10);
            const min = parseInt(document.querySelector(".gcqwwh.min-personnel").value, 10);
            if ( max == 0 ){
                alert(" 최대 모집 인원을 입력하세요 ")
                return;
            }
            if ( min == 0 ){
                alert(" 최소 출발 인원을 입력하세요 ")
                return;
            }
            if ( max < min ) {
                console.log("최대최소 인원 " +document.querySelector(".gcqwwh.max-personnel").value+" "+document.querySelector(".gcqwwh.min-personnel").value)
                alert(" 최소 출발 인원이 최대 모집 인원보다 많습니다.")
                return;
            }
        }

        // 포함사항 입력
        document.querySelectorAll(".includeContent").forEach((e,i) => {
            const input = document.createElement("input");
            input.type = "hidden"
            input.name = `includeContents[${i}]`
            input.value = e.textContent;;
            document['addCourse-form'].appendChild(input);
        })
        // 불포함 사항 입력
        document.querySelectorAll(".excludeContent").forEach((e,i) => {
            const input = document.createElement("input");
            input.type = "hidden"
            input.name = `excludeContents[${i}]`
            input.value = e.textContent;;
            document['addCourse-form'].appendChild(input);
        })
        // 준비물 입력
        document.querySelectorAll(".prepareContent").forEach((e,i) => {
            const input = document.createElement("input");
            input.type = "hidden"
            input.name = `prepareContents[${i}]`
            input.value = e.textContent;;
            document['addCourse-form'].appendChild(input);
        })
        // 스케쥴 입력
        document.querySelectorAll(".kmqQeBdetail").forEach( (e,i) => {
            const input = document.createElement("input");
            input.type = "hidden"
            input.name = `scheduleContents[${i}]`
            input.value = `${e.value}`;
            document['addCourse-form'].appendChild(input);
        })
        // 여행 코스 입력
        tourSpots.forEach((e,i) => {
            const inputName = document.createElement("input");
            inputName.type = "hidden"
            inputName.name = `paths[${i}].pathName`
            inputName.value = `${e.title}`;
            document['addCourse-form'].appendChild(inputName);

            const inputAddress = document.createElement("input");
            inputAddress.type = "hidden"
            inputAddress.name = `paths[${i}].pathAddress`
            inputAddress.value = `${e.address}`;
            document['addCourse-form'].appendChild(inputAddress);
        })

        // 파일 정보 생성  25.03.21 조승찬 시작
        if (document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS").querySelector(".uploadFile")){

            const inputFileName = document.createElement("input");
            inputFileName.type = "hidden";
            inputFileName.name = `courseFileName`;
            inputFileName.value = document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS").querySelector(".uploadFile").dataset.fileName;
            document['addCourse-form'].appendChild(inputFileName);

            const inputFilePath = document.createElement("input");
            inputFilePath.type = "hidden";
            inputFilePath.name = `courseFilePath`;
            inputFilePath.value = document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS").querySelector(".uploadFile").dataset.filePath;
            document['addCourse-form'].appendChild(inputFilePath);

            const inputFileSize = document.createElement("input");
            inputFileSize.type = "hidden";
            inputFileSize.name = `courseFileSize`;
            inputFileSize.value = document.querySelector(".ImageList-sc-9v1mt2-0.hGJMVS").querySelector(".uploadFile").dataset.fileSize;
            document['addCourse-form'].appendChild(inputFileSize);
        }
        // 파일 정보 생성  25.03.21 조승찬 끝

        document['addCourse-form'].submit();
    })


    // 25.03.21 조승찬 추가 시작
    // 파일  입력시 컨트롤러로 전송해서 썸네일 정보를 받아서 보여준다
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
    // 25.03.21 조승찬 추가 끝

});
