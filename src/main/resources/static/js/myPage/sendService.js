const messageService = (() => {
    // 목록
    const getSendList = async (callback, page = 1) => {
        const response = await fetch(`/my-page/messageList_Sends?page=${page}`);
        const sendListData = await response.json();
        console.log(sendListData);
        if(callback){
            callback(sendListData)
        }
    }
    return {getSendList: getSendList};
})();