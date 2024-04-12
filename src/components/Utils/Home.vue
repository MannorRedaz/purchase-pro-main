<template>
  <el-container :style="containerStyle">
    <el-header :style="headerStyle">
       <slot name="header"></slot>
    </el-header>
      <!-- 内容主体区域 -->
      <el-container>
      <!-- 侧边栏 -->
      <el-aside width="200px" :style="asideStyle">
        <el-menu
          :default-active="activePath"
          class="el-menu-vertical-demo"
          :background-color="asideStyle.backgroundColor"
          text-color="#000000"
          active-text-color="#67C23A"
          router
          >
         <template
            v-for="item in menulist"
          >
              <el-submenu
                v-if="item.children"
                :key="item.id"
                :index="'/'+item.path"
              >
                <template slot="title">
                    <i :class="item.ico"></i>
                    <span><font style="color:white;">{{item.authName}}</font></span>
                </template>
                <el-menu-item
                    :index="'/'+subItem.path"
                    v-for="subItem in item.children"
                    :key="subItem.id"
                    @click="saveNavState('/'+subItem.path)"
                    >
                    <template slot="title">
                        <i :class="subItem.ico"></i>
                        <span><font style="color:white;">{{subItem.authName}}</font></span>
                    </template>
                </el-menu-item>
              </el-submenu>
              <el-menu-item
                v-else
                :index="'/'+item.path"
                :key="item.id"
                @click="saveNavState('/'+item.path)"
              >
                <i :class="item.ico"></i>
                <span slot="title"><font style="color:white;">{{item.authName}}</font></span>
            </el-menu-item>
          </template>
        </el-menu>
      </el-aside>
       <!-- 右侧主体区域 -->
      <el-main>
        <!-- 路由占位符 -->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
export default {
   props: {
    menulist: {
      type: Array,
      default: () => { return [] }
    },
    backgroundColor: {
      type: Array,
      default: ()=>{ return [] }
    }
  },
  data () {
    return {
     
      // 被激活的连接地址
      activePath: ''
    }
  },
  methods: {
    // 保存连接的激活状态
    saveNavState (activePath) {
      window.sessionStorage.setItem('activePath', activePath)
      this.activePath = activePath
    }
  },
  computed: {
    containerStyle: function() {
      return this.backgroundColor[0]
    },
    headerStyle: function() {
      return this.backgroundColor[1]
    },
    asideStyle: function() {
      return this.backgroundColor[2]
    }
  }
}
</script>
<style lang="less" scoped>
</style>
