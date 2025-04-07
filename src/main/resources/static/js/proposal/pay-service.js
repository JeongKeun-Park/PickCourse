const payService = (() => {
    const kakaoPay = async () => {
        // result-price 클래스의 값을 가져오기
        const resultPriceElement = document.querySelector(".result-price");
        const productCount = document.querySelector(".product-count").value;
        console.log(addPoint);

        if (!resultPriceElement) {
            console.error("result-price 요소를 찾을 수 없습니다.");
            return;
        }

        // textContent 값 가져오기
        const textPrice = resultPriceElement.textContent.trim();
        // 쉼표 제거 및 숫자로 변환
        const numericPrice = parseInt(textPrice.replace(/,/g, ""), 10);
        let items = Array.from({ length: productCount }, (_, index) => ({
            id: plan.id,
            name: plan.planName,
            qty: 1,
            price: index === 0 ? plan.planPrice - discountAmount : plan.planPrice, // 첫 번째 상품에 차이 추가
        }));

        const response = await Bootpay.requestPayment({
            application_id: "678096b03aa7c4faf96e5460",
            price: numericPrice,
            order_name: "테스트결제",
            order_id: "TEST_ORDER_ID",
            pg: "카카오페이",
            method: "카카오페이",
            tax_free: 0,
            user: {
                id: loginMember.id,
                username: loginMember.memberNickname,
                phone: loginMember.memberTell,
                email: loginMember.memberEmail,
            },
            items: items,
            extra: {
                open_type: "iframe",
                card_quota: "0,2,3",
                escrow: false,
            },
        });
    };

    // 결제내역 추가
    const addKakaoPay = async (addKakaoPay) => {
        await fetch("/proposal/addKakaoPay", {
            method: "post",
            body: JSON.stringify(addKakaoPay),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        })
    }

    // 포인트 변경
    const updatePoint = async (point) => {
        await fetch("/proposal/updatePoint", {
            method: "put",
            body: JSON.stringify(point),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        })
    }

    // 참가자 추가
    const insertParticipant = async (participant) => {
        await fetch("/proposal/insertParticipant", {
            method: "post",
            body: JSON.stringify(participant),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        })
        window.location.href = `/proposal/read?id=${plan.id}`;
    }


    return{
        kakaoPay:kakaoPay,
        insertParticipant:insertParticipant,
        addKakaoPay:addKakaoPay,
        updatePoint:updatePoint
    }
})()
