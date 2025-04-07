const reviewlistService = (() => {
    const getLists = async (planId, callback, page = 1) => {
        const response = await fetch(`/proposal/reviewlist/${planId}?page=${page}`)
        const feedListData = await response.json();
        if(callback){
            callback(feedListData);
        }
    }
    return{
        getLists:getLists
    }
})();