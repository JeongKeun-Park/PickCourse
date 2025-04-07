document.addEventListener("DOMContentLoaded", function () {
    const memberElement = document.querySelector(".UserInfo__Nickname-bt0psx-3 span");
    const memberId = memberElement ? memberElement.getAttribute("data-id") : null;

    if (!memberId || isNaN(memberId)) {
        return;
    }

    fetch(`/my-page/normalCourseParticipationCount?memberId=${memberId}`)
        .then(response => response.json())
        .then(count => updateBadge(".course_badge", count))
        .catch(() => {});

    fetch(`/my-page/volunteerCourseParticipationCount?memberId=${memberId}`)
        .then(response => response.json())
        .then(count => updateBadge(".volunteer_badge", count))
        .catch(() => {});

    fetch(`/my-page/myFeedCount?memberId=${memberId}`)
        .then(response => response.json())
        .then(count => updateBadge(".feed_badge", count))
        .catch(() => {});

    fetch(`/my-page/myReplyCount?memberId=${memberId}`)
        .then(response => response.json())
        .then(count => updateBadge(".reply_badge", count))
        .catch(() => {});


    function updateBadge(selector, count) {
        let badgeElement = document.querySelector(selector);
        let countElement = badgeElement.querySelector(".second_comment");

        if (count > 0) {
            badgeElement.classList.remove("none_complete_badge");
            countElement.classList.remove("noneCount");
            countElement.textContent = `${count}회`;
        }
    }


});


document.addEventListener("DOMContentLoaded", function () {
    fetch(`/my-page/recentCourse`)
        .then(response => response.json())
        .then(data => {
            if (data && data.courseName) {
                document.querySelector(".courseName").textContent = data.courseName;
                document.querySelector("#recentCourseImg").src = `/files/display?path=${data.courseFilePath}/${data.courseFileName}`;

                let recentCourseLink = document.querySelector("#recentCourseLink");
                recentCourseLink.href = `/proposal/viewlist?courseId=${data.courseId}`;

            }
        })
        .catch(error => console.error("최근 코스 데이터 가져오기 실패:", error));
});

document.addEventListener("DOMContentLoaded", function () {
    const memberElement = document.querySelector(".UserInfo__Nickname-bt0psx-3 span");
    const memberId = memberElement ? memberElement.getAttribute("data-id") : null;

    if (!memberId || isNaN(memberId)) return;

    fetch(`/my-page/myReviewCount?memberId=${memberId}`)
        .then(response => response.json())
        .then(count => {
            const reviewCountElement = document.getElementById("reviewCount");
            if (reviewCountElement) reviewCountElement.textContent = count;
        })
        .catch(() => {});

    fetch(`/my-page/myFeedCount?memberId=${memberId}`)
        .then(response => response.json())
        .then(count => {
            const feedCountElement = document.getElementById("feedCount");
            if (feedCountElement) feedCountElement.textContent = count;
        })
        .catch(() => {});
});


document.addEventListener("DOMContentLoaded", function () {
    const memberElement = document.querySelector(".UserInfo__Nickname-bt0psx-3 span");
    const memberId = memberElement ? memberElement.getAttribute("data-id") : null;

    if (!memberId || isNaN(memberId)) return;

    fetch(`/my-page/recentFeeds?memberId=${memberId}`)
        .then(response => response.json())
        .then(data => {
            const feedContainer = document.querySelector(".feedContainer");
            feedContainer.innerHTML = "";

            data.forEach((feed, index) => {

                const anchor = document.createElement("a");
                anchor.href = "/feeds/feed-list?listType=ALL";

                const wrapDiv = document.createElement("div");
                wrapDiv.className = index === 1 ? "feedWrap rightFeed" : "feedWrap";

                if (feed.filePath && feed.fileName) {
                    const encodedPath = encodeURIComponent(`${feed.filePath}/${feed.fileName}`);
                    const imageUrl = `/files/display?path=${encodedPath}`;

                    const img = document.createElement("img");
                    img.className = "Image__StyledImageLoader-v97gyx-2 bUFcfh";
                    img.src = imageUrl;
                    img.alt = "피드 이미지";

                    wrapDiv.appendChild(img);
                } else {
                    const noImage = document.createElement("div");
                    noImage.textContent = "이미지가 없습니다";
                    noImage.style.textAlign = "center";
                    noImage.style.padding = "30px 0";
                    noImage.style.color = "#999";
                    noImage.style.fontSize = "14px";
                    wrapDiv.appendChild(noImage);
                }

                anchor.appendChild(wrapDiv);
                feedContainer.appendChild(anchor);
            });
        })
        .catch(error => {
            console.error("최근 피드 가져오기 실패:", error);
        });
});
