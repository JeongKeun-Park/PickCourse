const inputAll = document.querySelectorAll("input");

inputAll.forEach((input) => {
    input.addEventListener("keydown", (e) => {
        if(e.key ==='Enter'){
            e.preventDefault()
        }
    })
})
