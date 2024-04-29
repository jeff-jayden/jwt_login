<template>
  <div>
    <div v-for="item in userList" :key="item.id">{{item.username}}</div>
  </div>
</template>

<script setup lang="ts">

import {computed, onMounted, ref} from "vue";
import request from "@/util/request";


const userList = ref([])
const user = ref('')

onMounted(() => {
  request({
    url: '/user/all',
    method: "get"
  }).then(res => {
    userList.value = res;
  })
})


computed(() => {
  user.value = localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : '无名氏'
})

</script>

<style scoped>

</style>