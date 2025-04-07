const bannerWrap = document.querySelector(".banner-wrap");
const userWrap = document.querySelector(".userWrap");
const imgWrap = document.querySelector(".img-wrap");
const rankingWrap = document.querySelector(".ranking-wrap");
const volunteerWrap = document.querySelector(".volunteer-wrap");
// mainDTO로 선언한 정보가 들어와있는지 console로 확인
console.log(mainDTO)


let text = ``;

// mainDTO의 courses라는 List객체를 개별화 시키기 위해 forEach를 사용
mainDTO.courses.forEach((course, i) => {
    // FilePath 와 FileName이 존재 한다면 encodedFilePath를 encodeURIComponent 로 지정, 없다면 null
    const encodedFilePath = course.courseFilePath && course.courseFileName
        ? encodeURIComponent(`${course.courseFilePath}/${course.courseFileName}`)
        : null;
    const defaultImage = "/images/proposal/noImage.png";
    text += `
        <div
                class="Banner__ImageSliderWrapper-dlocnb-2 hWqRHN"
        >
            <div
                    class="ImageSlider__SliderWrapper-sc-1obm2ug-0 hHiTXT"
            >
                <div
                        class="slick-slider ImageSlider__StyledSlider-sc-1obm2ug-1 cOsMnB slick-initialized"
                >
                    <div class="slick-list">
                        <div
                                class="slick-track"
                                style="
                                            opacity: 1;
                                            transform: translate3d(
                                                0px,
                                                0px,
                                                0px
                                            );
                                            width: 768px;
                                        "
                        >
                            <div
                                    data-index="0"
                                    class="slick-slide slick-active slick-current"
                                    tabindex="-1"
                                    aria-hidden="false"
                                    style="
                                                outline: none;
                                                width: 768px;
                                            "
                            >
                                <div>
                                    <a
                                            class="withLink__StyledLink-zuberk-0 lQqkn"
                                            href="/proposal/viewlist?courseId=${course.id}"
                                    >
                                        <div
                                                class="Image__Wrapper-v97gyx-0 gDuKGF"
                                        >
                                            <div
                                                    class="Fade__Wrapper-sc-1s0ipfq-0 koasSX"
                                                    style="
                                                                opacity: 1;
                                                                display: block;
                                                            "
                                            >
                                                <div
                                                        class="Ratio"
                                                        style="
                                                                    display: block;
                                                                "
                                                >
                                                    <div
                                                            class="Ratio-ratio"
                                                            style="
                                                                        height: 0px;
                                                                        position: relative;
                                                                        width: 100%;
                                                                        padding-top: 30%;
                                                                    "
                                                    >
                                                        <div
                                                                class="Ratio-content"
                                                                style="
                                                                            height: 80%;
                                                                            left: 0px;
                                                                            position: absolute;
                                                                            top: 0px;
                                                                            width: 100%;
                                                                        "
                                                        >
                                                            <img
                                                                    alt=" 이미지"
                                                                    class="추천코스Image__StyledImage-v97gyx-1 VUNpz"
                                                                    width="768"
                                                                    src="${encodedFilePath? `/files/display?path=${encodedFilePath}` : defaultImage}"
                                                            />
                                                            <div
                                                                    class="commentCource"
                                                            >
                                                                ${course.courseType}
                                                            </div>
                                                        </div>
                                                        <div
                                                                class="heartWrap"
                                                        >
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div
                                        >
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
    `;
})
bannerWrap.innerHTML = text;

text = ``;

if(mainDTO.volunteer.courseType === "봉사 코스"){
    const encodedFilePath = mainDTO.volunteer.courseFilePath && mainDTO.volunteer.courseFileName
        ? encodeURIComponent(`${mainDTO.volunteer.courseFilePath}/${mainDTO.volunteer.courseFileName}`)
        : null;
    const defaultImage = "/images/proposal/noImage.png";
    text = `
        <div
                class="ImageSlider__SliderWrapper-sc-1obm2ug-0 hHiTXT"
        >
            <div
                    class="slick-slider ImageSlider__StyledSlider-sc-1obm2ug-1 cOsMnB slick-initialized"
            >
                <div class="slick-list">
                    <div
                            class="slick-track"
                            style="
                                        opacity: 1;
                                        transform: translate3d(
                                            0px,
                                            0px,
                                            0px
                                        );
                                        width: 768px;
                                    "
                    >
                        <div
                                data-index="0"
                                class="slick-slide slick-active slick-current"
                                tabindex="-1"
                                aria-hidden="false"
                                style="
                                            outline: none;
                                            width: 768px;
                                        "
                        >
                            <div>
                                <a
                                        class="withLink__StyledLink-zuberk-0 lQqkn"
                                        href="/proposal/eco?id=${mainDTO.volunteer.id}"
                                >
                                    <div
                                            class="Image__Wrapper-v97gyx-0 gDuKGF"
                                    >
                                        <div
                                                class="Fade__Wrapper-sc-1s0ipfq-0 koasSX"
                                                style="
                                                            opacity: 1;
                                                            display: block;
                                                        "
                                        >
                                            <div
                                                    class="Ratio"
                                                    style="
                                                                display: block;
                                                            "
                                            >
                                                <div
                                                        class="Ratio-ratio"
                                                        style="
                                                                    height: 0px;
                                                                    position: relative;
                                                                    width: 100%;
                                                                    padding-top: 30%;
                                                                "
                                                >
                                                    <div
                                                            class="Ratio-content"
                                                            style="
                                                                        height: 80%;
                                                                        left: 0px;
                                                                        position: absolute;
                                                                        top: 0px;
                                                                        width: 100%;
                                                                    "
                                                    >
                                                        <img
                                                                alt=" 이미지"
                                                                class="Image__StyledImage-v97gyx-1 VUNpz"
                                                                width="768"
                                                                src="${encodedFilePath ? `files/display?path=${encodedFilePath}` : defaultImage}"
                                                        />
                                                    </div>
                                                    <div
                                                            class="heartWrap"
                                                    >
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div
                                    >
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `;
    volunteerWrap.innerHTML = text;
}

text = ``;

mainDTO.feeds.forEach((feed)=>{
    const encodedMemberPath = feed.member.memberFilePath && feed.member.memberFileName
        ? encodeURIComponent(`${feed.member.memberFilePath}/${feed.member.memberFileName}`)
        : null;

    const defaultProfileImage = "/images/proposal/noImage.png"
    const createdDate = feed.createdDate;
    // util에 있는 함수를 사용하여 현재시간에서 createdDate라고 선언된 날짜가 얼마나 지났는지 formatdDate에 담아준다.
    const formatdDate = timeForToday(createdDate)

    text+=`
        <div
                class="CurrentProfile__Wrapper-sc-1u92qay-0 cpLLyp CurrentIndex__StyledProfile-sc-12ji5h6-0 jVywpa"
                style="margin-right: 178px"
        >
            <div
                    class="CurrentProfile__UserProfileWrapper-sc-1u92qay-1 dIHOdO"
            >
                <div
                        class="CurrentProfile__ProfileImageWrapper-sc-1u92qay-2 iLrRBL"
                >
                    <div
                            class="Image__Wrapper-v97gyx-0 gDuKGF"
                    >
                        <div
                                class="Fade__Wrapper-sc-1s0ipfq-0 koasSX"
                                style="
                                            opacity: 1;
                                            display: block;
                                        "
                        >
                            <div
                                    class="Ratio"
                                    style="
                                                display: block;
                                            "
                            >
                                <div
                                        class="Ratio-ratio"
                                        style="
                                                    height: 0px;
                                                    position: relative;
                                                    width: 100%;
                                                    padding-top: 100%;
                                                "
                                >
                                    <div
                                            class="Ratio-content"
                                            style="
                                                        height: 100%;
                                                        left: 0px;
                                                        position: absolute;
                                                        top: 0px;
                                                        width: 100%;
                                                    "
                                    >
                                        <img
                                                class="Image__StyledImage-v97gyx-1 hPRDQO"
                                                width="36"
                                                height="36"
                                                src="${encodedMemberPath ? `/files/display?path=${encodedMemberPath}` : defaultProfileImage}"
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div
                        class="CurrentProfile__UserTextWrapper-sc-1u92qay-4 cGynaN"
                >
                    <p
                            class="CurrentProfile__Name-sc-1u92qay-3 jNvEmy"
                    >
                        ${feed.member.memberNickname}
                    </p>
                    <div
                            class="CurrentProfile__Time-sc-1u92qay-5 bICbxa"
                    >
                        ${formatdDate}
                    </div>
                </div>
            </div>
        </div>
    `;
})
userWrap.innerHTML=text;

text = ``;

mainDTO.feeds.forEach((feed)=>{
    // feed에 files는 List객체이기 때문에 0번방에 filePath와 fileName 이 존재하는지 확인한다
    const encodedFilePath = feed.files[0]?.filePath && feed.files[0]?.fileName
        ? encodeURIComponent(`${feed.files[0].filePath}/${feed.files[0].fileName}`)
        : null;

    const defaultImage = "/images/proposal/noImage.png";
    text +=`
        <div
                class="CardGroup__CardWrapper-l79tlh-2 jCmvf"
        >
            <div
                    class="CardMagazine__Wrapper-sc-1v8lgcg-0 iptsHz"
            >
                <div>
                    <div
                            class="Image__Wrapper-v97gyx-0 gDuKGF"
                    >
                        <div
                                class="Fade__Wrapper-sc-1s0ipfq-0 koasSX"
                                style="
                                                opacity: 1;
                                                display: block;
                                            "
                        >
                            <div
                                    class="Ratio"
                                    style="
                                                    display: block;
                                                "
                            >
                                <div
                                        class="Ratio-ratio"
                                        style="
                                                        height: 0px;
                                                        position: relative;
                                                        width: 100%;
                                                        padding-top: 120%;
                                                    "
                                >
                                    <div
                                            class="Ratio-content"
                                            style="
                                                            height: 100%;
                                                            left: 0px;
                                                            position: absolute;
                                                            top: 0px;
                                                            width: 100%;
                                                        "
                                    >
                                        <img
                                                class="Image__StyledImage-v97gyx-1 VUNpz"
                                                width="256"
                                                src="${encodedFilePath ? `/files/display?path=${encodedFilePath}` : defaultImage}"
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
    `;
})

imgWrap.innerHTML = text;

text = ``;
mainDTO.courses.sort((a,b)=>b.count-a.count);

mainDTO.courses.forEach((course) => {
    const encodedFilePath = course.courseFilePath && course.courseFileName
        ? encodeURIComponent(`${course.courseFilePath}/${course.courseFileName}`)
        : null;
    const defaultImage = "/images/proposal/noImage.png";
    text +=`
        <div
                class="CardProduct__Wrapper-a817d1-0 ijdqTp"
        >
            <a
                    class="withLink__StyledLink-zuberk-0 lQqkn"
                    href="/proposal/viewlist?courseId=${course.id}"
            >
                <div
                        class="CardProduct__InfoWrapper-a817d1-1 hPIfDi"
                >
                    <div
                            class="CardProduct__ImageContainer-a817d1-2 eEpNrE"
                    >
                        <div
                                class="Image__Wrapper-v97gyx-0 gDuKGF CardProduct__StyledImage-a817d1-4 fRUJUy"
                        >
                            <div
                                    class="Fade__Wrapper-sc-1s0ipfq-0 koasSX"
                                    style="
                                                opacity: 1;
                                                display: block;
                                            "
                            >
                                <div
                                        class="Ratio"
                                        style="
                                                    display: block;
                                                "
                                >
                                    <div
                                            class="Ratio-ratio"
                                            style="
                                                        height: 0px;
                                                        position: relative;
                                                        width: 100%;
                                                        padding-top: 100%;
                                                    "
                                    >
                                        <div
                                                class="Ratio-content"
                                                style="
                                                            height: 100%;
                                                            left: 0px;
                                                            position: absolute;
                                                            top: 0px;
                                                            width: 100%;
                                                        "
                                        >
                                            <img
                                                    class="Image__StyledImage-v97gyx-1 VUNpu"
                                                    width="192"
                                                    src="${encodedFilePath?`/files/display?path=${encodedFilePath}`:defaultImage}"
                                            />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div
                            class="CardProduct__DetailContainer-a817d1-6 iDZoyW"
                    >
                        <div
                                class="CardProduct__Title-a817d1-8 kxyGXE"
                        >
                            ${course.courseName}
                        </div>
                        <hr
                                type="Line"
                                size="16"
                                class="Border-sc-1bzcz1p-0 eXQzbT"
                        />
                    </div>
                </div>
            </a
            >
        </div>
    
    `;
})

rankingWrap.innerHTML = text;

