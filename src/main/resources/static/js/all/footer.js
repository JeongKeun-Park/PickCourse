const footertoggleButton = document.querySelector(".Secondary__Button-l8jybe-011");
const footermodal = document.querySelector(".New__Container-sc-17rcq7-011");
const footerexitBtns = document.querySelector(".ebDrPW11");
const footerexitBtn = document.querySelector(".caCrk11");

footertoggleButton.addEventListener("click", () => {
    if (footermodal.classList.contains("hidden")) {
        footermodal.classList.remove("hidden");
        footermodal.classList.add("visible");
    } else {
        footermodal.classList.remove("visible");
        footermodal.classList.add("hidden");
    }
});

footerexitBtns.addEventListener("click", (e) => {
    footermodal.classList.remove("visible");
    footermodal.classList.add("hidden");
    // if (e.target === exitBtns) {
    //     modal.style.display = "none";
    // }
});

footerexitBtn.addEventListener("click", (e) => {
    footermodal.classList.remove("visible");
    footermodal.classList.add("hidden");
    // if (e.target === exitBtn) {
    //     modal.style.display = "none";
    // }
});
// modal.addEventListener("click", (e) => {
//     modal.classList.remove("visible");
//     modal.classList.add("hidden");
// });
