const fileService = (() => {

    const upload = async (formData) => {
        const response = await fetch("/files/upload", {
            method: "post",
            body: formData
        })
    }

    return {upload: upload};
})();