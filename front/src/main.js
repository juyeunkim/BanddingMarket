import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import VueRouter from 'vue-router'
import routes from './routes'
import Vuex from 'vuex'
import store from "./vuex/store";
import VueScroller from 'vue-scroller'
import VueCookies from 'vue-cookies'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faPaperclip, faSmile } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

Vue.config.productionTip = false

Vue.use(VueCookies)
Vue.use(Vuex)
Vue.use(VueRouter)
Vue.use(VueScroller)


library.add(faPaperclip)
library.add(faSmile)

Vue.component('font-awesome-icon', FontAwesomeIcon)

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