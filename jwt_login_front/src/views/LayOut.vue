<template>
    <el-container style="height: 100vh" direction="vertical">
        <top-header></top-header>
        <el-container>
            <side-menu></side-menu>
            <el-main>
                <el-scrollbar>
                    <router-view></router-view>
                </el-scrollbar>
            </el-main>
        </el-container>
    </el-container>
</template>


<script setup lang="ts">
import SideMenu from '@/components/side-menu.vue'
import TopHeader from '@/components/top-header.vue'
</script>

<style scoped></style>