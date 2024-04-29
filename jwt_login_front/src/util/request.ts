// 引入axios进行网络请求
import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router";
// 创建axios实例，配置默认URL和后台超时时间
const request = axios.create({
    baseURL: 'http://localhost:8080/api',
    timeout: 3000  // 后台接口超时时间设置
})

const user = localStorage.getItem('user')

// request 拦截器
// 可以自请求发送前对请求做一些处理
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    if(user) {
        config.headers['token'] = JSON.parse(user).token
    }
    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        // 当权限验证不通过的时候给出提示
        if (res.code === '401') {
            ElMessage.error(res.msg);
            router.push("/login")
        }
        return res;
    },
    error => {
        console.log('err' + error)
        return Promise.reject(error)
    }
)


export default request