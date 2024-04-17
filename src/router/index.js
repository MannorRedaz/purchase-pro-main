import Vue from 'vue'
import VueRouter from 'vue-router'


import Login from '../views/Login/Login.vue'
import Loginfor from '../components/common/index.vue'
import Register from '../views/Register/Register.vue'

import StaticMain from '../components/common/static/staticMian.vue'

import Application from '../components/Supplier/Application.vue'
import ApplicationResult from '../components/Supplier/ApplicationResults.vue'
import BasicInfo from '../components/Supplier/BasicInfo.vue'
import SWelcome from '../components/Supplier/Home.vue'
import Board from '../components/Supplier/Board.vue'
import SupplierHome from '../views/Supplier/SupplierHome.vue'


//
import PresidentHome from '../views/President/Home.vue'
import PWelcome from '../views/President/Home.vue'
import PBoard from '../components/President/Board.vue'
import PreInfo from '../components/President/BasicInfo.vue'
import PurchaseManage from '../components/President/PurchaseManage.vue'
import ApplyShortListManage from '../components/President/ApplyShortListManage.vue'
import PurchaseList from '../components/President/PurchaseList.vue'
import ResultMessage from '../components/President/ResultMessage.vue'


//supper
import SupperHome from '../views/Supper/SupperHome.vue'
import SupperUserMenage from '../components/Supper/UserMenage.vue'
import SupperBasicInfo from '../components/Supper/BasicInfo.vue'


//schoolAdmin
import SchoolAdminHome from '../views/SchoolAdmin/SchoolAdminHome.vue'
import SchoolAdminNotice from '../components/SchoolAdmin/board/Board.vue'
import SchoolAdminNoticeManage from '../components/SchoolAdmin/board/BoardManage.vue'
import PurchaserUserManage from '../components/SchoolAdmin/userManage/PurchaserUserManage.vue'
import PresidentUserManage from '../components/SchoolAdmin/userManage/PresidentUserManage.vue'
import SchoolAdminPurchaseManage from '../components/SchoolAdmin/applyManage/PurchaseManage.vue'
import SchoolAdminApplyShortListManage from '../components/SchoolAdmin/applyManage/ApplyShortListManage.vue'
import PurchaseListManage from '../components/SchoolAdmin/purchaseDetail/PurchaseListManage.vue'
import SchoolAdminBasicInfo from '../components/SchoolAdmin/BasicInfo.vue'
import applyBoardManage from '../components/SchoolAdmin/board/applyBoardManage.vue'

import PurchaserHome from '../views/Purchaser/AdminHome.vue'


//president

import PresidentNotice from '../components/President/board/Board.vue'
import PresidentNoticeManage from '../components/President/board/BoardManage.vue'
import PriPurchaseManage from '../components/President/purchaseManage/PurchaseManage.vue'
import PripurchaseList from '../components/President/purchaseManage/purchaseListManage.vue'


//common
import ComBoard from '../components/President/board/Board.vue'
import ComBoardManage from '../components/common/board/BoardManage.vue'
//purchaser
import AddPurchase from '../components/Purchaser/AddPurchase.vue'
import PriPurchaseListchaseList from '../components/Purchaser/PurchaseList.vue'
import PurInfo from '../components/Purchaser/BasicInfo.vue'
import PurBoardManage from '../components/Purchaser/BoardManage.vue'


//supplier
import SupBoard from '../components/Supplier/board/Board.vue'
import ApplyManage from '../components/Supplier/applyManage/applyManage1.vue'
import ApplyForList from '../components/Supplier/applyForList/applyForList.vue'
import applyForlistBoard from '../components/Supplier/board/applyForlistBoard.vue'
import Static from '../components/Supplier/staticMian.vue'

Vue.use(VueRouter)

export const routes = [
  { path: '/', redirect: '/login' },
  // { path: '/login', component: Login },
  { path: '/login', component: Loginfor },
  { path: '/register', component: Register },

  {
    path: '/supplier',
    name: 'Supplier',
    component: SupplierHome,
    redirect: '/supplier/board',
    children: [
      { path: '/supplier/basicInfo', component: BasicInfo },
      { path: '/supplier/board', component: SupBoard },
      { path: '/supplier/applyManage', component: ApplyManage },
      { path: '/supplier/applyForlistBoard', component: applyForlistBoard },
      { path: '/supplier/applyForList', component: ApplyForList },
      { path: '/application', component: Application },
      { path: '/applicationResult', component: ApplicationResult },
      { path: '/static', component: Static },
    ]
  },
  {
    path: '/purchaser',
    name: 'purchaser',
    component: PurchaserHome,
    redirect: '/purchaser/board',
    children: [
      { path: '/purchaser/board', component: ComBoard },
      { path: '/purchaser/boardManage', component: PurBoardManage },
      { path: '/purchaser/StaticMain', component: StaticMain },

      { path: '/purchaser/addPurchase', component: AddPurchase },
      { path: '/purchaser/basicInfo', component: PurInfo },
      { path: '/purchaser/applyShortListManage', component: ApplyShortListManage },
      { path: '/purchaser/purchaseManage', component: PurchaseManage },
      { path: '/purchaser/purchaseList', component: PriPurchaseListchaseList },
      { path: '/purchaser/resultMessage', component: ResultMessage },
    ]
  },
  {
    path: '/president',
    name: 'president',
    component: PresidentHome,
    redirect: '/president/Board',
    children: [
      { path: '/president/board', component: PresidentNotice },
      { path: '/president/boardManage', component: PresidentNoticeManage },
      { path: '/president/basicInfo', component: PreInfo },
      { path: '/president/applyShortListManage', component: ApplyShortListManage },
      { path: '/president/purchaseManage', component: PriPurchaseManage },
      { path: '/president/purchaseList', component: PripurchaseList },
      { path: '/president/resultMessage', component: ResultMessage },
      { path: '/president/StaticMain', component: StaticMain },
    ]
  },
  {
    path: '/supper',
    name: 'supper',
    component: SupperHome,
    redirect: '/supper/supperUserMenage',
    children: [
      { path: '/supper/supperUserMenage', component: SupperUserMenage },
      { path: '/supper/supperBasicInfo', component: SupperBasicInfo }
    ]
  },
  {
    path: '/schoolAdmin',
    name: 'schoolAdmin',
    component: SchoolAdminHome,
    redirect: '/schoolAdmin/notice',
    children: [
      { path: '/schoolAdmin/notice', component: SchoolAdminNotice },
      { path: '/schoolAdmin/noticeManage', component: SchoolAdminNoticeManage },
      { path: '/schoolAdmin/presidentUserManage', component: PresidentUserManage },
      { path: '/schoolAdmin/purchaserUserManage', component: PurchaserUserManage },
      { path: '/StaticMain', component: StaticMain },

      { path: '/schoolAdmin/applyShortListManage', component: SchoolAdminApplyShortListManage },
      { path: '/schoolAdmin/purchaseManage', component: SchoolAdminPurchaseManage },
      { path: '/schoolAdmin/purchaseListManage', component: PurchaseListManage },
      { path: '/schoolAdmin/schoolAdminBasicInfo', component: SchoolAdminBasicInfo },
      { path: '/schoolAdmin/applyBoardManage', component: applyBoardManage },
      { path: '/schoolAdmin/StaticMain', component: StaticMain },
    ]
  },
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/register') {
    next();
  } else {
    //let token = localStorage.getItem('Authorization');

    const data1 = JSON.parse(window.sessionStorage.getItem("data")).data[0];
    if (data1 === null || data1 === '') {
      this.$message.error("你还未登录!");
      next('/login');
    } else {
      next();
    }
  }
});



// // 挂载路由导航守卫
// router.beforeEach((to, from, next) => {
//   // to 将要访问的路径
//   // from 代表从哪个路径跳转而来
//   // next 是一个函数，表示放行
//   if (to.path === '/login' || to.path === '/register') {
//     return next()
//   }
//   // 获取token
//   const tokenStr = window.sessionStorage.getItem('token')
//   if (!tokenStr) {
//     return next('/login')
//   }
//   next()
// })

export default router

