/**
 * 使用 axios 封装的 ajax 请求函数 函数返回的是 promise 对象
 * */

import Axios from 'axios';

Axios.defaults.baseURL = "http://localhost:8088/";

export function categoryList(url){
return Axios.get(url).then(res=>res.data)
}
// ' https ://www .luffycity.com/ api/v1/course_list?user=value//请求体{
// key: []
// }
//axiosRe 异步请求函数
export function axiosRe(url,type){
    if(type="post"){
        return Axios.post(url).then(res=>res.data)
    }

    //return Axios.get(url).then(res=>res.data)
    //res=>res.data   function(res){ return res.data}
}
export function courseDetail(courseId){
return Axios.get(`${courseDetailUrl}${courseId}`).then(res=>res.data)
}


// export default function axios(url, data = {}, type) {
//   if (type === 'GET') { //GET请求
//     let paramStr = '';
//     Object.keys(data).forEach(key => {
//       paramStr += `${key}=${data[key]}&`
//     });
//     if (paramStr) {
//       paramStr = paramStr.substring(0, paramStr.length - 1)
//     }
//     let httpUrl = `${url}?${paramStr}`;
//     return Axios.get(httpUrl)
//   } else {
//     // 发送 post 请求
//     return Axios.post(url, data) // data: 包含请求体数据的对象
//   }
// }