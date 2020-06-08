import Vue from "vue";
import Vuex from "vuex";
import actions from "./actions";
import mutations from "./mutations";

Vue.use(Vuex);

const state = {
    test: "test",
    // searchList: false,
    searchList: [],
    board: {
        board_id: 12,
        board_locationX: "0",
        board_locationY: "0",
        category: 0,
        context: "",
        deadlineDate: "2020-06-06T12:59:32.798207",
        deleted: "",
        keyword: "",
        limit_num: 0,
        participants: 0,
        title: "",
        user: {
            age: 0,
            email: "",
            gender: "M",
            name: "",
            nickname: "",
            password: "",
            phone: "",
            reputation: 0,
            role: "",
            user_id: 0,
            user_locationX: 0,
            user_locationY: 0,
        },
        writeDate: "2020-06-06T12:59:32.798207"
    },
    commentList: {},
    drawer: false

    // searchList: ["aaaa", "bbbbb", "CCCCCCCC", "dddd", "ee", "ff", "gg", "h", "i", "jj", "aaaa", "bbbbb", "CCCCCCCC", "dddd", "ee", "ff", "gg", "h", "i", "jj", ],
};

const getters = {
    test: state => {
        return state.test;
    },
    searchList: state => {
        return state.searchList
    },
    board: state => {
        return state.board
    }
};

export default new Vuex.Store({
    state,
    getters,
    mutations,
    actions
});