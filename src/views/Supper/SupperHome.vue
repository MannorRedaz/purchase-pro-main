<template>
  <base-div :menulist="menulist" :background-color="backgroundColor">
    <div slot="header">
      <div class="logo">
        <span>超级管理员</span>
      </div>
     <div class="tools">
        <el-menu class="user"
          background-color="skyblue"
           active-text-color="#ffd04b"
           router >
           <el-submenu index="2">
            <template slot="title">{{userName}}</template>
             <el-menu-item  @click="setting">设置</el-menu-item>
             <el-menu-item index="/">退出</el-menu-item>
           </el-submenu>
        </el-menu>
      </div>
    </div>
  </base-div>
</template>
<script>
import baseDiv from '../../components/Utils/Home'
export default {
  data () {
    return {
      // 左侧菜单数据
      menulist: [
       {
          authName: '账号管理',
          id: 22,
          ico: 'el-icon-document',
          path: 'supper/supperUserMenage',
        },
        {
          authName: '个人信息管理',
          id: 23,
          ico: 'el-icon-document',
          path: 'supper/supperBasicInfo',
        }

      ],
      userName:'未知账号',
      backgroundColor: [
        {
          border: "1px solid pink",
          height: "100%"
        },
        {
          backgroundColor: "skyblue"
        },
        {
          backgroundColor:"#545c64"
        }
      ],
      // 被激活的连接地址
      activePath: '',
      budge:'100',
      count:'10'
    }
  },
  created(){
  
  },
  mounted(){
    const data=JSON.parse(window.sessionStorage.getItem('data'));
    this.userName=data.contract_name;
    
  },
  computed:{
          
  },
  components: {
    baseDiv
  },
  methods: {
    // 保存连接的激活状态
    saveNavState (activePath) {
      window.sessionStorage.setItem('activePath', activePath)
      this.activePath = activePath
    },
  
  }
}
</script>
<style lang="less" scoped>
  .logo {
    line-height: 60px;
    float: left;
  }
  .date {
    width: 500px;
    height: 100px;
    position:absolute;
   left:100px;
   top:20px;
  margin-left: 500px;
  }

  .tools {
    width: 650px;
    float: right;
    padding-right: 50px;
    .money {
      float: left;
      .el-form-item {
        float: left;
        width: 180px;
        margin: 10px 10px 0;
      }
    }
    .user {
      width: 200px;
      float: right;
      text-align: center;
      .el-menu-item {
        z-index: 100;
      }
    }
  }
</style>
