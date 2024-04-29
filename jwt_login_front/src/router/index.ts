import {createRouter, createWebHashHistory} from 'vue-router'

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            name: 'home',
            redirect: '/login',
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('@/views/login.vue')
        },
        {
            path: '/user',
            name: 'user',
            component: () => import('@/views/LayOut.vue'),
            children: [
                {
                    path: '/userList',
                    name: 'userList',
                    component: () => import('@/views/user-list.vue')
                }
            ]
        },

    ]
})

//第一处拦截的地方 不足 出现用户信息篡改的时候也能蒙对 鉴权放前端不行，放后端jwt
router.beforeEach((to, from, next) => {
    if (to.path == '/login') {
        next()
    }
    if (!localStorage.getItem('user') && to.path != '/login') {
        next('/login')
    }
    next()
})

export default router
