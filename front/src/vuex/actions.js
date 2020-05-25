import Constant from "./Constant";
import http from "./http-common";

export default {
    [Constant.SET_TEST]: (store, payload) => {
        //사용방법
        // this.$store.dispatch(Constant.SET_TEST, {
        //     tmp: "tmp",
        //   });
        payload
        http
            .get("url")
            .then(response => {

                store.commit(Constant.SET_TEST, {
                    test: response.data.data
                });
            })
            .catch(exp => {
                alert("GET_YOUTUBERS_PER_CATEGORY에 실패하였습니다!\n" + exp);
            });
    },
    [Constant.SET_SEARCHLIST]: (store, payload) => {
        // console.log(payload.searchList)
        store.commit(Constant.SET_SEARCHLIST, {
            searchList: payload.searchList
        });
    }
};