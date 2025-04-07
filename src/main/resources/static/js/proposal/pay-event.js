const payButton = document.querySelector(".pay-button");
// 전액 버튼 누르면 반영되게
discountWrapper.addEventListener("click", (e)=> {
    if(e.target.classList.contains("cVMMHP")){
        discountAmount = parseInt(document.querySelector(".discount").textContent.replace(/,/g, ""), 10);
        paymentAmount = parseInt(document.querySelector(".dxsibZ").textContent.replace(/,/g, ""), 10);

        document.querySelector(".Coupon_Input").value = NumberWithCommas(discountAmount);
        document.querySelector(".peakcourseAmount").textContent = NumberWithCommas(paymentAmount - discountAmount);
        document.querySelector(".pointspan").textContent = NumberWithCommas((paymentAmount - discountAmount) / 100);
    }
})
// 전액 버튼 누르면 반영되게

payButton.addEventListener("click", async (e) => {
    let method = "카카오페이";
    let resultPoint = loginMember.memberPoint - discountAmount + addPoint;
    console.log(resultPoint)

    await payService.kakaoPay();

    await payService.addKakaoPay({
        payPrice: resultPrice.textContent.replace(",", ""),
        payMethod: method,
        memberId: loginMember.id,
        planId: plan.id
    })

    await payService.updatePoint({
        memberPoint: resultPoint,
        id: loginMember.id
    })

    await payService.insertParticipant({
        memberId: loginMember.id,
        planId: plan.id
    })
})

