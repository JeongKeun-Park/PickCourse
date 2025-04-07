document.addEventListener("DOMContentLoaded", function () {
    fetch(`/my-page/my-courses`)
        .then(response => response.text())
        .then(text => {
            try {
                const data = JSON.parse(text);
                const wrapper = document.querySelector(".Banner__Wrapper-dlocnb-0");
                wrapper.innerHTML = "";

                data.slice(0, 5).forEach(course => {  // 최대 5개
                    const imagePath = course.courseFilePath && course.courseFileName
                        ? `/files/display?path=${encodeURIComponent(course.courseFilePath + '/' + course.courseFileName)}`
                        : "/images/default-course.jpg";

                    const courseTypeLabel = course.courseType;
                    const courseId = course.courseId;

                    const template = `
                    <div class="Banner__ImageSliderWrapper-dlocnb-2 hWqRHN">
                        <div class="ImageSlider__SliderWrapper-sc-1obm2ug-0 hHiTXT">
                            <div class="slick-slider ImageSlider__StyledSlider-sc-1obm2ug-1 cOsMnB slick-initialized">
                                <div class="slick-list">
                                    <div class="slick-track firstwBanner" style="opacity: 1; transform: translate3d(0px, 0px, 0px); width: 768px;">
                                        <div data-index="0" class="slick-slide slick-active slick-current" tabindex="-1" aria-hidden="false" style="outline: none; width: 768px;">
                                            <div>
                                                <a class="withLink__StyledLink-zuberk-0 lQqkn" href="/proposal/viewlist?courseId=${courseId}">
                                                    <div class="Image__Wrapper-v97gyx-0 gDuKGF">
                                                        <div class="Fade__Wrapper-sc-1s0ipfq-0 koasSX" style="opacity: 1; display: block;">
                                                            <div class="Ratio" style="display: block;">
                                                                <div class="Ratio-ratio" style="height: 0px; position: relative; width: 100%; padding-top: 30%;">
                                                                    <div class="Ratio-content" style="height: 80%; left: 0px; position: absolute; top: 0px; width: 100%;">
                                                                        <img alt="코스 이미지" class="추천코스Image__StyledImage-v97gyx-1 VUNpz" width="768" src="${imagePath}" />
                                                                        <div class="commentCource">${courseTypeLabel}</div>
                                                                    </div>
                                                                    <div class="heartWrap">
                                                                        <button class="heartButton">
                                                                            <img src="/images/main/red.svg" alt="찜하기" />
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>`;

                    wrapper.insertAdjacentHTML("beforeend", template);
                });

            } catch (e) {
            }
        })
        .catch(() => {
        });
});


