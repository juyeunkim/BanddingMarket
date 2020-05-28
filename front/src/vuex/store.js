import Vue from "vue";
import Vuex from "vuex";
import actions from "./actions";
import mutations from "./mutations";

Vue.use(Vuex);

const state = {
    test: "test",
};

const getters = {
    test: state => {
        return state.test;
    },
};

export default new Vuex.Store({
    state,
    getters,
    mutations,
    actions
});