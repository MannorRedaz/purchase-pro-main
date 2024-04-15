// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// 导入axios包
import axios from 'axios'
import './assets/css/global.css';
import './plugins/element.js';

import moment from 'moment'
import echarts from 'echarts'


axios.defaults.baseURL = 'http://localhost:8088/'
Vue.prototype.$echarts = echarts
Vue.prototype.$moment = moment

// axios请求拦截
axios.interceptors.request.use(config => {
  config.headers.Authorization = window.sessionStorage.getItem('token')
  return config
})



Vue.prototype.$http = axios;
Vue.use(ElementUI);
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  render: h => h(App),
})


