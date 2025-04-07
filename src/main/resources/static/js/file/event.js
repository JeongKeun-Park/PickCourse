const fileWrap = document.getElementById("file-wrap");

// input 태그 1개(multiple)
// const file = document.querySelector("input.file");
//
// file.addEventListener("change", (e) =>{
//     FileList.prototype.forEach = Array.prototype.forEach;
//     e.target.files.forEach(async (file) => {
//         const formData = new FormData();
//         formData.append("file", file);
//         await fileService.upload(formData);
//     });
// })

// input태그 여러개
// const files = document.querySelectorAll("input.file");
// files.forEach((file) => {
//     file.addEventListener("change", async (e) => {
//         const file = e.target.files[0];
//         const formData = new FormData();
//         formData.append("file", file);
//         await fileService.upload(formData);
//     });
// });

post.postFiles.forEach((postFile) => {
    const a = document.createElement("a");
    const img = document.createElement("img");

    a.href = `/files/download?path=${postFile.filePath + "/" + postFile.fileName}`;

    img.src = `/files/display?path=${postFile.filePath + "/t_" + postFile.fileName}`;
    img.style.width = "100px";

    a.appendChild(img)
    fileWrap.appendChild(a);
})