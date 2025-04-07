const fileService = (() => {

    const upload = async (formData) => {
        const response = await fetch("/my-page/upload", {
            method: "post",
            body: formData
        });

        if(response.ok) {
            const result = await response.json();

            const newImageUrl = `/files/display?path=${result.filePath}/${result.fileName}`;

            document.querySelectorAll(".uploadImage").forEach(img => {
                if (img.classList.contains("mainProfile")) {
                    img.src = newImageUrl + "&t=" + new Date().getTime();
                }
            });

        } else {
            alert("프로필 업로드에 실패했습니다.");
        }
    }

    return {upload: upload};
})();
