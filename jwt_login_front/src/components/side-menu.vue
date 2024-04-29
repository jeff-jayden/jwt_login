<template>
    <el-aside>
        <el-menu default-active="2" class="el-menu-vertical-demo" style="height: 90vh;" router>
            <el-sub-menu index="1">
                <template #title>
                    <el-icon>
                        <User />
                    </el-icon>
                    <span>用户列表</span>
                </template>
                <el-menu-item-group title="Group One">
                    <el-menu-item index="1-1" route="/userList">用户列表</el-menu-item>
                    <el-menu-item index="1-2">item two</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="Group Two">
                    <el-menu-item index="1-3">item three</el-menu-item>
                </el-menu-item-group>
                <el-sub-menu index="1-4">
                    <template #title>item four</template>
                    <el-menu-item index="1-4-1">item one</el-menu-item>
                </el-sub-menu>
            </el-sub-menu>
            <el-menu-item index="2">
                <el-icon><icon-menu /></el-icon>
                <span>Navigator Two</span>
            </el-menu-item>
            <el-menu-item index="3" disabled>
                <el-icon>
                    <document />
                </el-icon>
                <span>Navigator Three</span>
            </el-menu-item>
            <el-menu-item index="4">
                <el-icon>
                    <setting />
                </el-icon>
                <span>Navigator Four</span>
            </el-menu-item>
        </el-menu>
    </el-aside>
</template>

<script lang="ts" setup>
import {Location, User} from "@element-plus/icons-vue";

const handleOpen = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
}
</script>

<style scoped></style>