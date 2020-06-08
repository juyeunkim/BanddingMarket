import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import VueRouter from 'vue-router'
import routes from './routes'
import Vuex from 'vuex'
import store from "./vuex/store";
import VueScroller from 'vue-scroller'
import VueCookies from 'vue-cookies'

Vue.config.productionTip = false

Vue.use(VueCookies)
Vue.use(Vuex)
Vue.use(VueRouter)
Vue.use(VueScroller)

const router = new VueRouter({
    mode: "history",
    routes
});

new Vue({
    vuetify,
    router,
    store,
    render: h => h(App)
}).$mount('#app')