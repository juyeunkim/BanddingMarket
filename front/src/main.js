import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import VueRouter from 'vue-router'
import routes from './routes'
import Vuex from 'vuex'

Vue.config.productionTip = false

Vue.use(Vuex)
Vue.use(VueRouter)

const router = new VueRouter({
    mode: "history",
    routes
});

new Vue({
    vuetify,
    router,
    render: h => h(App)
}).$mount('#app')