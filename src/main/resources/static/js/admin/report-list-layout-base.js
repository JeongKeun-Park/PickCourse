// 2025.03.14 조승찬 작성

// 초기 화면 생성 함수
function createReportList() {

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
                                   <h3>신고 목록</h3>
                               </div>
                               <ul class="NoticePage__NoticeListWrapper" >
                                   <div class="mainUserListDiv reportList" >
                                       <div class="reportIdDiv" style="display: none;" > Id </div>
                                       <div class="SourceDiv" > Source </div>
                                       <div class="reportedIdDiv" style="display: none;"> Source Id </div>
                                       <div class="reportedReason">Details of Report </div>
                                       <div class="moreDiv"></div>
                                       <div class="createdDateDiv" > Reporting Date </div>
                                       <div class="memberNicknameDiv" > Reporting Member </div>
                                       <div class="memberIdDiv" style="display: none;">Reporting Member Id</div>
                                   </div>
                                   <div class="reportList-container">
<!--                                       신고 목록 여기 생성 -->
                                   </div>
                               </ul>
                               <footer class="Wrapper__PaginationWrapper-fk4uv4-0 hBVRcD" >
                                   <div class="paging-wrap">
                                       <div class="pagination-container">
<!--                                       페이지 번호 여기 생성-->
                                       </div>
                                   </div>
<!--                                   검색 처리 -->
                                   <!-- 검색 폼 -->
                                   <div>
                                       <form name="searchForm">
                                           <input type="hidden" name="page" class="courseList">
                                           <div class="fields">
                                               <div class="field">
                                                   <div id="searchWrap">
                                                       <div class="activityWrap">
                                                           <label>
                                                               <input type="radio" name="isAct" class="isAct">전체
                                                           </label>
                                                           <br>
                                                           <label>
                                                               <input type="radio" name="isAct" class="isAct">FEED
                                                           </label>
                                                           <br>
                                                           <label>
                                                               <input type="radio" name="isAct" class="isAct">REPLY
                                                           </label>
                                                       </div>
                                                       <label for="keyWordInput">검색어</label>
                                                       <input type="text" name="keyWord">
                                                       <a class="search button primary icon solid fa-reportSearch">검색</a>
                                                   </div>
                                               </div>
                                           </div>
                                       </form>
<!--                                                       검색 기준을 입력 받지 않지만, search.getTypes()가 제대로 기동하도록 유지해야 함.-->
                                       <select name="type" class="custom-select" style="visibility: hidden;"></select>
                                   </div>

                               </footer>

                           </div>
                       </div>
                   </div>
               </section>
           </div>
       </main>
   `;
};
