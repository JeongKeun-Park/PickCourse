const readService = (() => {
//     질문추가
    const writeQuestion = async (question) => {
        await fetch("/proposal/writeQuestion", {
            method: "post",
            body: JSON.stringify(question),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }
//     답변추가
    const writeAnswer = async (answer) => {
        await fetch("/proposal/writeAnswer", {
            method: "post",
            body: JSON.stringify(answer),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        });
    }


// 질문목록조회
    const getList = async (planId, callback) => {
        const response = await fetch(`/proposal/read/${planId}`);
        const questionListData = await response.json();
        if(callback) {
            callback(questionListData);
        }
    }
    // 답변목록 조회
    const getAnswerList = async (planId, questionId, callback) => {
        const response = await fetch(`/proposal/getAnswerLists/${planId}/${questionId}`);
        const answerListData = await response.json();
        if(callback) {
            callback(answerListData);
        }
    }


    return{
        writeQuestion:writeQuestion,
        getList:getList,
        writeAnswer:writeAnswer,
        getAnswerList:getAnswerList
    };
}) ();
