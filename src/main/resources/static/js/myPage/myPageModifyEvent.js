
// input 태그 1개(multiple)
const file = document.getElementById("fileInput");

file.addEventListener("change", (e) =>{
    FileList.prototype.forEach = Array.prototype.forEach;
    e.target.files.forEach(async (file) => {
        const formData = new FormData();
        formData.append("file", file);
        await fileService.upload(formData);
    });
})