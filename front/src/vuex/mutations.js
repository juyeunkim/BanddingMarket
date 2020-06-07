import Constant from "./Constant";
import store from "./store";

export default {
    [Constant.SET_TEST]: (state, payload) => {
        store.state.test = payload.test;
    },
    [Constant.SET_SEARCHLIST]: (state, payload) => {
        // store.state.searchList = store.state.searchList.concat(payload.searchList);
        store.state.searchList = payload.searchList
        console.log(store.state.searchList)
    },
    [Constant.SET_BOARD]: (state, payload) => {
        // store.state.searchList = store.state.searchList.concat(payload.searchList);
        store.state.board = payload.board
        console.log(store.state.board)
    },
    [Constant.SET_COMMENTLIST]: (state, payload) => {
        // store.state.searchList = store.state.searchList.concat(payload.searchList);
        store.state.commentList = payload.commentList
        console.log(store.state.commentList)
    },




};