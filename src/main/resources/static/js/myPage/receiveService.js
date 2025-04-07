const messageService = (() => {
    // 목록
    const getReceiveList = async (callback, page = 1) => {
        const response = await fetch(`/my-page/messageListReceives?page=${page}`);
        const receiveListData = await response.json();
        console.log(receiveListData);
        if(callback){
            callback(receiveListData)
        }
    }
    return {getReceiveList: getReceiveList};
})();