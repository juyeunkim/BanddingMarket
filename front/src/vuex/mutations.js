import Constant from "./Constant";
import store from "./store";

export default {
    [Constant.SET_TEST]: (state, payload) => {
        store.state.test = payload.test;
    },
    [Constant.SET_SEARCHLIST]: (state, payload) => {
        store.state.searchList = store.state.searchList.concat(payload.searchList);
        console.log(store.state.searchList)
    },

};