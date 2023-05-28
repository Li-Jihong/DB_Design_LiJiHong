import Vue from 'vue'
import Vuex from 'vuex'
import router from "@/router";

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        currentPathName: ''
    },
    mutations: {
        setPath(state) {
            state.currentPathName = localStorage.getItem("currentPathName")
        },
        logout() {
            // 清空缓存
            localStorage.removeItem("user")
            localStorage.removeItem("menus")

            // 重置路由
            router.push("/login")
            resetRouter()
        }
    }
})

// 重置路由的方法
export const resetRouter = () => {
    const newRouter = router.options.routes.filter(route => {
        // 过滤掉非动态添加的路由，只保留基础路由
        return route.path === '/login' || route.path === '/register' || route.path === '/404'
    })
    router.matcher = new VueRouter({
        mode: 'history',
        base: process.env.BASE_URL,
        routes: newRouter
    }).matcher
}

export default store
