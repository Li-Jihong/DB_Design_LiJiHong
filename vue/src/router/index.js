import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

// 导入各个页面组件
import NotFound from '../views/404.vue';
import About from '../views/About.vue';
import Activity from '../views/Activity.vue';
import Attendance from '../views/Attendance.vue';
import Building from '../views/Building.vue';
import Classes from '../views/Classes.vue';
import ClassLeader from '../views/Classleader.vue';
import Comments from '../views/Comments.vue';
import Courses from '../views/Courses.vue';
import Dashboard from '../views/Dashbord.vue';
import Donate from '../views/Donate.vue';
import Dormitory from '../views/Dormitory.vue';
import Duty from '../views/Duty.vue';
import Headmaster from '../views/Headmaster.vue';
import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import Manage from '../views/Manage.vue';
import Menu from '../views/Menu.vue';
import Parameter from '../views/Parameter.vue';
import Password from '../views/Password.vue';
import Payment from '../views/Payment.vue';
import Person from '../views/Person.vue';
import Register from '../views/Register.vue';
import RewardPunishment from '../views/Rewardpunishment.vue';
import Role from '../views/Role.vue';
import Students from '../views/Students.vue';
import TalkingRecord from '../views/Talkingrecord.vue';
import Teachers from '../views/Teachers.vue';
import ThemeMeeting from '../views/Thememeeting.vue';
import User from '../views/User.vue';
import WorkPlan from '../views/Workplan.vue';
import WorkSummary from '../views/Worksummary.vue';
import DutyStudent from '../views/DutyStudent.vue';

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/404',
    name: 'NotFound',
    component: NotFound
  },
  {
    path: '/',
    name: 'Manage',
    component: Manage,
    redirect: '/home',
    children: [
      { path: 'home', name: 'Home', component: Home },
      { path: 'about', name: 'About', component: About },
      { path: 'activity', name: 'Activity', component: Activity },
      { path: 'attendance', name: 'Attendance', component: Attendance },
      { path: 'building', name: 'Building', component: Building },
      { path: 'classes', name: 'Classes', component: Classes },
      { path: 'classleader', name: 'ClassLeader', component: ClassLeader },
      { path: 'comments', name: 'Comments', component: Comments },
      { path: 'courses', name: 'Courses', component: Courses },
      { path: 'dashboard', name: 'Dashboard', component: Dashboard },
      { path: 'donate', name: 'Donate', component: Donate },
      { path: 'dormitory', name: 'Dormitory', component: Dormitory },
      { path: 'duty', name: 'Duty', component: Duty },
      { path: 'dutystudent', name: 'DutyStudent', component: DutyStudent },
      { path: 'headmaster', name: 'Headmaster', component: Headmaster },
      { path: 'menu', name: 'Menu', component: Menu },
      { path: 'parameter', name: 'Parameter', component: Parameter },
      { path: 'password', name: 'Password', component: Password },
      { path: 'payment', name: 'Payment', component: Payment },
      { path: 'person', name: 'Person', component: Person },
      { path: 'rewardpunishment', name: 'RewardPunishment', component: RewardPunishment },
      { path: 'role', name: 'Role', component: Role },
      { path: 'students', name: 'Students', component: Students },
      { path: 'talkingrecord', name: 'TalkingRecord', component: TalkingRecord },
      { path: 'teachers', name: 'Teachers', component: Teachers },
      { path: 'thememeeting', name: 'ThemeMeeting', component: ThemeMeeting },
      { path: 'user', name: 'User', component: User },
      { path: 'workplan', name: 'WorkPlan', component: WorkPlan },
      { path: 'worksummary', name: 'WorkSummary', component: WorkSummary },
    ]
  },
  { path: '*', redirect: '/404' } // 未匹配到的路由重定向到404页面
];


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 提供一个重置路由的方法
export const resetRouter = () => {
  router.matcher = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
  }).matcher;
}

// 注意：刷新页面会导致页面路由重置
export const setRoutes = () => {
  const storeMenus = localStorage.getItem("menus");
  if (storeMenus) {
    // 获取当前的路由对象名称数组
    const currentRouteNames = router.getRoutes().map(v => v.name)
    if (!currentRouteNames.includes('Manage')) {
      // 拼装动态路由
      const manageRoute = { path: '/', name: 'Manage', component: () => import('../views/Manage.vue'), redirect: "/home", children: [
          { path: 'person', name: '个人信息', component: () => import('../views/Person.vue')},
          { path: 'password', name: '修改密码', component: () => import('../views/Password.vue')}
        ] }
      const menus = JSON.parse(storeMenus)
      menus.forEach(item => {
        if (item.path) {  // 当且仅当path不为空的时候才去设置路由
          let itemMenu = { path: item.path.replace("/", ""), name: item.name, component: () => import('../views/' + item.pagePath + '.vue')}
          manageRoute.children.push(itemMenu)
        } else if(item.children.length) {
          item.children.forEach(item => {
            if (item.path) {
              let itemMenu = { path: item.path.replace("/", ""), name: item.name, component: () => import('../views/' + item.pagePath + '.vue')}
              manageRoute.children.push(itemMenu)
            }
          })
        }
      })
      // 动态添加到现在的路由对象中去
      router.addRoute(manageRoute)
    }
  }
}

// 重置路由
resetRouter();
// 初始化设置路由
setRoutes();

router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name); // 设置当前的路由名称
  store.commit("setPath");

  // 未找到路由的情况
  if (!to.matched.length) {
    const storeMenus = localStorage.getItem("menus");
    if (storeMenus) {
      next("/404");
    } else {
      // 跳回登录页面
      next("/login");
    }
  }
  // 其他的情况都放行
  next();
});

export default router;
