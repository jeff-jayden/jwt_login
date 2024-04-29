<template>
  <div class="header">
    <el-header>
      <div>
        jwt_login
      </div>
      <div class="header-right">
        <el-dropdown hide-on-click>
          <span class="el-dropdown-link" style="color: #ffffff;">
            欢迎！ {{user.username}}<el-icon class="el-icon--right"><arrow-down/></el-icon>
          </span>
          <!-- <el-avatar :size="44" src="" /> -->
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>个人中心</el-dropdown-item>
              <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </el-header>
  </div>
</template>

<script lang="ts" setup>
import {ArrowDown} from "@element-plus/icons-vue";
import router from "@/router";

const user = localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : ''

const logout = () => {
  localStorage.removeItem('user');
  router.push('/login')
}

</script>


<style scoped>
.el-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #4a535a;
  color: #ffffff;
  
  .header-right {
    color: #ffffff;
  }
}
</style>