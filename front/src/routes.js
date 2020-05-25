import Main from './views/Main.vue'
import Sub from './views/Sub.vue'
import Admin from './views/Admin/Admin.vue'
import UserList from './views/Admin/UserList.vue'


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
    }
]