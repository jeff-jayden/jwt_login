<template>
  <div class="container">
    <div class="login">
      <div class="login-content">登录</div>
      <el-form :model="form" label-width="auto" style="max-width: 600px">
        <el-form-item label="用户名">
          <el-input v-model="form.username" placeholder="输入用户名"/>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" show-password v-model="form.password" placeholder="输入密码"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup lang="ts">

import {reactive, ref} from "vue";
import request from "@/util/request";
import router from "@/router";


const form = reactive({
  username: '',
  password: '',
})

const login = () => {
  request({
    url: '/user/login',
    method: "post",
    data: form
  }).then(res => {
    if(res.code == '20041'){
      localStorage.setItem('user',JSON.stringify(res.data));
      router.push('/user')
    }
    console.log(res)
  })
}

</script>

<style scoped>
:deep(.el-button--primary) {
  margin-left: 75%;
}

.container {
  height: 100vh;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.login-content {
  margin-bottom: 10px;
}
</style>