// 2025.03.15 조승찬 작성

// 초기 화면 생성 함수
function createNoticeList() {

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
                                   <h3>공지 목록</h3>
                               </div>
                               <button class="noticeRegistBtn">
                                   공지 등록
                               </button>
                               <ul class="NoticePage__NoticeListWrapper" >
                                   <div class="mainNoticeListDiv noticeList" >
                                       <div class="noticeIdDiv"> Id </div>
                                       <div class="noticeTitleDiv">Title</div>
                                       <div class="moreDiv"></div>
                                       <div class="createdDateDiv">Created Date</div>
                                       <div class="adminNameDiv" > Writer </div>
                                   </div>
                                   <div class="noticeList-container">
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
                                                       <select name="type" class="custom-select">
                                                           <option value="">검색 기준</option>
                                                           <option value="tc">전 체</option>
                                                           <option value="t">Title</option>
                                                           <option value="c">Content</option>
                                                       </select>
                                                       <input type="text" name="keyWord">
                                                       <a class="search button primary icon solid fa-noticeSearch">검색</a>
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
