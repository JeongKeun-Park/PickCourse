const infoWrap = document.querySelector(".info-wrap");
const titleWrap = document.querySelector(".title-wrap");
const priceWrap = document.querySelector(".price-wrap")
const discountWrapper = document.querySelector(".discountWrapper")
const discountWrap = document.querySelector(".discount-wrap");


const price = plan.planPrice;
const point = loginMember.memberPoint;
const startDate =new Date(plan.planStartDate);

const formatStartDate = `${startDate.getMonth()+1}월 ${startDate.getDate()}일`;
const formatPrice = price.toLocaleString();
const formatPoint = point.toLocaleString();
const insertPoint = price / 100;

titleWrap.innerHTML = `
<div class="ProductInfo__Title-sc-1af61ub-3 htnJlC">
    [${formatStartDate} 출발✨] ${plan.planName}🚎
</div>
`;

infoWrap.innerHTML = `
<p>${formatStartDate}</p>
<p>결제 금액</p>
<p class="oneAmount"><span>${formatPrice}원</span></p>
`;

priceWrap.innerHTML = `
<span class="PurchaseCell__Price-sc-1g4ze9y-5 dOTuYO">${formatPrice}</span><span>원</span>
`;

discountWrapper.innerHTML = `
 <span class="discount"><span>${formatPoint}원</span></span>
<button class="InnerActionButton-io567l-0 cVMMHP">전액</button>
`;

discountWrap.innerHTML = `
<p
    class="PageTitle__PurchasePageTitle-ex62ss-0 TotalPrice__PriceSectionTitle-sc-1e1zxsm-1 jjsTId"
>
    총 결제금액<span class="TotalPrice__TotalPriceText-sc-1e1zxsm-3 dxsibZ plan-price">${formatPrice}</span
    ><span>원</span>
</p>
<hr />
<p class="SubTitle-eeu9i7-0 TotalPrice__PriceSectionSubTitle-sc-1e1zxsm-2 eHKVGS">
    총 피커스 금액<span class="peakcourseAmount result-price">${formatPrice}</span><span>원</span>
</p>
<div class="TotalPrice__SaveEnergyContainer-sc-1e1zxsm-6 kuAjAK">
    <div class="TotalPrice__SaveTextContainer-sc-1e1zxsm-7 bquTis">
        <span>적립 예정 포인트</span
        ><span class="pointspan" style="color: rgb(0, 0, 0)">${insertPoint}</span><span>원</span>
    </div>
    <div class="TotalPrice__SaveEnergyDescrption-sc-1e1zxsm-8 dfwePM">
        결제한 피커스가 사용 완료된 다음 날 자동으로 적립됩니다.<br />
        결제를 취소하거나 환불할 경우 예정된 포인트는 적립되지 않으며,<br />이미 적립된
        포인트는 회수됩니다.
    </div>
</div>
`;
const resultPrice = document.querySelector(".result-price");
