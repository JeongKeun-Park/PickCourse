const planService = (() => {
    const getList = async (callback, page=1) => {
        const response = await fetch(`/proposal/modifylists?page=${page}`)
        const planListData = await response.json();
        if(callback) {
            callback(planListData);
        }
    }
    return{getList:getList}


})();
