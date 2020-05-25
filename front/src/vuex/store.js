import Vue from "vue";
import Vuex from "vuex";
import actions from "./actions";
import mutations from "./mutations";

Vue.use(Vuex);

const state = {
    test: "test",
    // searchList: false,
    searchList: ["aaaa", "bbbbb", "CCCCCCCC"],
};

const getters = {
    test: state => {
        return state.test;
    },
    searchList: state => {
        return state.searchList
    }
};

export default new Vuex.Store({
    state,
    getters,
    mutations,
    actions
});