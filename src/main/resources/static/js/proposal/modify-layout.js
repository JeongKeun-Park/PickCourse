console.log(plan);
let includes = plan.includeContents;
let excludes = plan.excludeContents;
let prepares = plan.prepareContents;
let firstTagCount = 0;
let secondTagCount = 0;
let thirdTagCount = 0;

const bDBbNifirst = document.querySelector(".bDBbNifirst");
const bDBbNisecond = document.querySelector(".bDBbNisecond");
const bDBbNithird = document.querySelector(".bDBbNithird");
const gcqwwhinclude = document.querySelector(".gcqwwh.include");
const gcqwwhexclude = document.querySelector(".gcqwwh.exclude");
const gcqwwhprepare = document.querySelector(".gcqwwh.prepare");

// 포함사항
includes.forEach((include)=> {
    if (firstTagCount === 0) {
        bDBbNifirst.innerHTML = `<header class="Article__Header-sc-1mmkltm-0 gScFGo">
                                        <hgroup>
                                            <h2 class="Article__Title-sc-1mmkltm-1 bZNoYF">포함 사항</h2>
                                        </hgroup>
                                      </header>
                                      <div class="Stuff__StuffContainer-sc-8zlrc8-0 iXEvmI includes-wrap"></div>`;
    }
    const includesWrap = document.querySelector(".includes-wrap");

    const firstchildDiv = document.createElement("div");

    firstchildDiv.setAttribute("data-index", include.id);
    firstchildDiv.className = "Tag__RoundTag-sxb61j-1 jXxsiv";
    firstchildDiv.innerHTML = `<span>#${include.includeContent}</span>
                     <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='nonzero' stroke='%23999' stroke-linecap='square'%3E %3Cpath d='M11.828 6.172l-5.656 5.656M11.828 11.828L6.172 6.172'/%3E %3C/g%3E %3C/svg%3E" alt="delete tags item">`;
    includesWrap.appendChild(firstchildDiv);
    gcqwwhinclude.value = "";

    firstTagCount += 1;
    gcqwwhinclude.placeholder = `포함 사항 (${firstTagCount}/10)`;
})

//불포함사항
excludes.forEach((exclude)=> {
    if (secondTagCount === 0) {
        bDBbNisecond.innerHTML = `<header class="Article__Header-sc-1mmkltm-0 gScFGo">
                                        <hgroup>
                                            <h2 class="Article__Title-sc-1mmkltm-1 bZNoYF">불포함 사항</h2>
                                        </hgroup>
                                      </header>
                                      <div class="Stuff__StuffContainer-sc-8zlrc8-0 iXEvmI excludes-wrap"></div>`;
    }
    const excludesWrap = document.querySelector(".excludes-wrap")


    const secondchildDiv = document.createElement("div");

    secondchildDiv.setAttribute("data-index", exclude.id);
    secondchildDiv.className = "Tag__RoundTag-sxb61j-1 eMLPLA";
    secondchildDiv.innerHTML = `<span>#${exclude.excludeContent}</span>
             <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='nonzero' stroke='%23999' stroke-linecap='square'%3E %3Cpath d='M11.828 6.172l-5.656 5.656M11.828 11.828L6.172 6.172'/%3E %3C/g%3E %3C/svg%3E" alt="delete tags item">`;
    excludesWrap.appendChild(secondchildDiv);
    gcqwwhexclude.value = "";

    secondTagCount += 1;
    gcqwwhexclude.placeholder = `불포함 사항 (${secondTagCount}/10)`;
})

//준비물
prepares.forEach((prepare)=> {
    if (thirdTagCount === 0) {
        bDBbNithird.innerHTML = `<header class="Article__Header-sc-1mmkltm-0 gScFGo">
                                        <hgroup>
                                            <h2 class="Article__Title-sc-1mmkltm-1 bZNoYF">준비물</h2>
                                        </hgroup>
                                      </header>
                                      <div class="Stuff__StuffContainer-sc-8zlrc8-0 iXEvmI prepares-wrap"></div>`;
    }
    const preparesWrap = document.querySelector(".prepares-wrap");

    const thirdchildDiv = document.createElement("div");

    thirdchildDiv.setAttribute("data-index", prepare.id);
    thirdchildDiv.className = "Tag__RoundTag-sxb61j-1 eISlhn";
    thirdchildDiv.innerHTML = `<span>#${prepare.prepareContent}</span>
                 <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='nonzero' stroke='%23999' stroke-linecap='square'%3E %3Cpath d='M11.828 6.172l-5.656 5.656M11.828 11.828L6.172 6.172'/%3E %3C/g%3E %3C/svg%3E" alt="delete tags item">`;
    preparesWrap.appendChild(thirdchildDiv);
    gcqwwhprepare.value = "";

    thirdTagCount += 1;
    gcqwwhprepare.placeholder = `준비물 (${thirdTagCount}/10)`;
})

const textareaWrap = document.querySelector(".textarea-wrap");
const newP = document.createElement("p");
const planContent = plan.planContent;
const contentLength = planContent.length;
newP.innerHTML = `
<p class="Textarea__Count-sc-1b9phu6-2 jvAusQ">${contentLength} / 1200 (추천 글자수: 30자 이내)</p>
`;

textareaWrap.appendChild(newP);

// 모이는 장소 :: 카카오맵 처리하기
// 외부 스크립트 추가. 지도 맵 동적 생성
document.querySelector(".gcqwwh.gather").addEventListener("keyup", (e) => {
    if (e.key == "Enter") {
        if (document.querySelector("#mapContainer")) {
            document.querySelector("#mapContainer").remove();
            document.querySelector(".gcqwwh.gather1").remove();
        }

        let geocoder = new kakao.maps.services.Geocoder();
        geocoder.addressSearch(document.querySelector(".gcqwwh.gather").value, (result, status) => {
            if (status === kakao.maps.services.Status.OK) {
                const mapDiv = document.createElement("div");
                mapDiv.id = "mapContainer";
                mapDiv.innerHTML = `<div id="map"></div>
                <div id="fullMap">
                    <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='24' height='24' fill='none' viewBox='0 0 24 24'%3E %3Cpath stroke='%23333' stroke-linecap='round' stroke-linejoin='round' stroke-width='1.5' d='M17 3h4v4M15 9l6-6M7 21H3v-4M9 15l-6 6M4 11V6c0-1.105.895-2 2-2h5M20 13v5c0 1.105-.895 2-2 2h-6'/%3E %3C/svg%3E" alt="map fullscreen">
                </div>`;
                // const mapDiv = document.createElement("div");
                // mapDiv.id = "map";
                document.querySelector(".GatheringPlace").appendChild(mapDiv);

                // const mapInput = document.createElement("input");
                // mapInput.className = "SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh gather1";
                // mapInput.placeholder = "참가자들이 이해하기 쉽게 설명해주세요";
                // document.querySelector(".GatheringPlace").appendChild(mapInput);

                document.querySelector("#fullMap").addEventListener("click", (e) => {
                    if (mapContainer.style.position === "fixed") {
                        mapContainer.style.position = "relative";
                        mapContainer.style.width = "100%";
                        mapContainer.style.height = "25vh";
                        mapContainer.style.zIndex = ""; // 맵이 다른 요소 위에 오도록 설정한거 해제
                        document.querySelector("#fullMap").style.position = "absolute";
                    } else {
                        mapContainer.style.position = "fixed";
                        mapContainer.style.top = "0";
                        mapContainer.style.left = "0";
                        mapContainer.style.width = "100%";
                        mapContainer.style.height = "100vh";
                        mapContainer.style.zIndex = "1000"; // 맵이 다른 요소 위에 오도록 설정
                        document.querySelector("#fullMap").style.position = "fixed";
                    }
                    map.relayout();
                    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                    map.setCenter(coords);
                });

                let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                let mapContainer = document.getElementById("map"), // 지도를 표시할 div
                    mapOption = {
                        center: coords,
                        level: 3, // 지도의 확대 레벨
                    };
                // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
                let map = new kakao.maps.Map(mapContainer, mapOption);
                // 결과값으로 받은 위치를 마커로 표시합니다
                let marker = new kakao.maps.Marker({
                    map: map,
                    position: coords,
                });
                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                map.setCenter(coords);
            }
        });
    }
});

// 모이는 장소 :: 카카오맵 처리하기

