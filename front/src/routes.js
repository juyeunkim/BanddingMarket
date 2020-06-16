import Main from './views/Main.vue'
import Sub from './views/Sub.vue'
import Admin from './views/Admin/Admin.vue'
import UserList from './views/Admin/UserList.vue'
import ReportList from './views/Admin/ReportList.vue'
import TransactionManagement from './views/Admin/TransactionManagement.vue'
import SearchPage from './views/SearchPage'
import MapSearhPage from './views/MapSearchPage'


export default [

    {
        path: '/',
        name: 'Main',
        component: Main
    },
    {
        path: '/sub',
        name: 'Sub',
        component: Sub
    },
    {
        path: '/admin/admin',
        name: 'Admin',
        component: Admin
    },
    {
        path: '/admin/userList',
        name: 'UserList',
        component: UserList
    },
    {
        path: '/admin/reportList',
        name: 'reportList',
        component: ReportList
    },
    {
        path: '/admin/transactionManagement',
        name: 'transactionManagement',
        component: TransactionManagement

    },
    {
        path: '/search',
        name: 'SearchPage',
        component: SearchPage
    },
    {
        path: '/mapsearch',
        name: 'MapSearhPage',
        component: MapSearhPage
    },


]