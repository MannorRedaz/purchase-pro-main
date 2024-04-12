// 导入axios包
import axios from 'axios'
axios.defaults.baseURL = 'http://localhost:8080/ssm_war_exploded/'
// axios请求拦截
axios.interceptors.request.use(config => {
  config.headers.Authorization = window.sessionStorage.getItem('token')
  return config
})
const myAxios = axios
export default myAxios
