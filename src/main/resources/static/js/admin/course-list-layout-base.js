// 2025.03.09 조승찬
// 코스 목록 생성하는 함수
function createCourseList() {
    listObserverPause = false;  // 동적요소 생성 감시 시작

    const baseContainer = document.querySelector(".AppLayout_contents__YmI3N");
    baseContainer.innerHTML = ``;

    baseContainer.innerHTML = `
       <main class="FundingPage_container__NDhmM">
           <div id="FundingPage_Content" class="FundingPage_box__OGY3N" >
               <section>
                   <div class="FundingPage_headBanner__MDMzM" ></div>
               </section>
               <section class="FundingPage_presentation__MzAzN" >
                   <div class="FundingPage_content__NjFjY">
                       <div class="Funding_wrapper__YzViZ">
                           <div class="Fade__Wrapper-sc-1s0ipfq-0 koasSX ulWrap" style="
                                   opacity: 1;
                                   display: block;
                               " >
                               <div class="Center__Wrapper-sc-19p29je-0 hcLOFI" >
                                   <h3>코스 목록</h3>
                               </div>
                               <div class="topsearchandbtn">
                                    <div class="topsearch-wrap">
                                        <select class="selectCourseOpt" >
                                            <option
                                                value="" disabled selected  > 선 택 </option>
<!--                                                25.03.09 항상 5개 코스 유지를 위해 하나가 선택되면 기존 선택은 지워짐 -->
                                            <option value="A 코스"> A 코스   </option>
                                            <option value="B 코스"> B 코스   </option>
                                            <option value="C 코스"> C 코스   </option>
                                            <option value="D 코스"> D 코스   </option>
                                            <option value="봉사 코스"> 봉사 코스 </option>
<!--                                            <option value="N/A"> N/A </option>-->
                                        </select>
                                        <button class="selectCourseBtn">코스 등록</button>
                                    </div>
                               </div>
                               <ul class="NoticePage__NoticeListWrapper" >
                                   <div class="mainUserListDiv" >
                                       <label class="">
                                           <input type="checkbox" class="checkboxall" />
                                       </label>
                                       <div class="courseIdDiv" > #COURSE ID </div>
                                       <div class="adminNameDiv" > Admin Name </div>
                                       <div class="courseNameDiv" > Course Name </div>
                                       <div class="courseAddressDiv" > Course Theme </div>
                                       <div class="courseIsVolunteerDiv" > Volunteer </div>
                                       <div class="courseTypeDiv" > Course Type </div>
                                   </div>
<!--                                   2025.03.08 조승찬 추가 -->
                                   <div class="courseList-container">
<!--                                       코스 목록 여기 생성 -->
                                   </div>
                               </ul>
                               <form action="/admin/add-course" method="get">
                                    <button type="submit" class="addCourseBtn"> 신규 작성 </button>
                               </form>
                               <footer class="Wrapper__PaginationWrapper-fk4uv4-0 hBVRcD" >
<!--                                   2025.03.08 조승찬 -->
                                   <div class="paging-wrap">
                                       <div class="pagination-container">
<!--                                       페이지 번호 여기 생성-->
                                       </div>
                                   </div>
<!--                                   2025.03.08 조승찬 -->

<!--                                   2025.03.08 조승찬 -->
<!--                                   검색 처리 -->
                                   <!-- 검색 폼 -->
                                   <div>
                                       <form name="searchForm">
                                           <input type="hidden" name="page" class="courseList">
                                           <div class="fields">
                                               <div class="field">
                                                   <div id="searchWrap">
                                                       <select name="type" class="custom-select">
                                                           <option value="">검색 기준</option>
                                                           <option value="natp">전 체</option>
                                                           <option value="n">Course Name</option>
                                                           <option value="a">Admin Name</option>
                                                           <option value="t">Course Theme</option>
                                                           <option value="u">Using Course</option>
                                                       </select>
                                                       <input type="text" name="keyWord">
                                                       <a class="search button primary icon solid fa-courseList-search">검색</a>
                                                   </div>
                                               </div>
                                           </div>
                                       </form>
                                   </div>
                                   <!--                                                    2025.03.02 조승찬 -->

                               </footer>

                           </div>
                       </div>
                   </div>
               </section>
           </div>
       </main>
   `;
};
