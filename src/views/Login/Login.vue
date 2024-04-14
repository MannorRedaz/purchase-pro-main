<template>
  <div class="login-container">

    <el-card class="login">
      <div class="logo">
        <img src="../../assets/logo.jpg" />
      </div>
      <!-- 登录表单区域 -->
      <el-form ref="loginForm" :model="loginForm" :rules="loginFormRules" label-width="0px" class="login-form">
        <!-- 登录选择 -->
        <el-form-item label-width="10px">
          <div class="select">
            <el-radio v-model="value" label="1">管理员</el-radio>
            <el-radio v-model="value" label="0">供应商</el-radio>
          </div>
        </el-form-item>
        <!-- 用户名 -->
        <el-form-item prop="name" label-width="10px">
          <div class="user">
            <el-input v-model="loginForm.name" name="username" size="large" class="user"></el-input>
          </div>
        </el-form-item>
        <!-- 密码框 -->
        <el-form-item prop="pwd" label-width="10px">
          <div class="password">
            <el-input v-model="loginForm.pwd" type="password" name="password" show-password="show-password" />
          </div>
        </el-form-item>
        <!--按钮区-->
        <el-form-item class="btns">
          <el-button type="primary" @click="login('loginForm')" class="w-full">登录</el-button>
          <el-button type="info" @click="restLoginForm('loginForm')">重置</el-button>
          <p>没有账号?点击<a href="#" @click="enRegister()">注册</a></p>
        </el-form-item>
      </el-form>
    </el-card>

  </div>
</template>
<script>

export default {
  data() {
    return {
      // 这是数据表单的数据绑定对象
      loginForm: {
        name: "nihao",
        pwd: "123456",
      },
      value: "0",
      options: [
        {
          value: "0",
          label: "供应商",
        },
        {
          value: "1",
          label: "管理员",
        },
      ],
      // 这是登录表单的验证规则对象·
      loginFormRules: {
        // 验证用户名是否合法
        name: [
          { required: true, message: "请输入登录名称", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度应该在3到10个字符之间",
            trigger: "blur",
          },
        ],
        // 验证密码是否合法
        pwd: [
          { required: true, message: "请输入登陆密码", trigger: "blur" },
          {
            min: 6,
            max: 15,
            message: "长度在6到15个字符之间",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    // 点击重置按钮，重置登录表单
    restLoginForm() {
      this.loginForm.name = "";
      this.loginForm.pwd = "";
    },
    // 点击登录按钮，进行登录数据的预验证
    async login(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          this.loginAction();
        } else {
          this.$message.error("输入的格式不对");
        }
      });
    },
    async loginAction() {
      if (this.value == 1) {
        const { data: res } = await this.$http.post(
          "administerLogin",
          this.loginForm
        );
        if (!res.success) {
          return this.$message.error("登陆失败!");
        }
        // 将登录成功之后的token,保存到客户端的sessionStorage中
        const token = res.date[0].token;
        window.sessionStorage.setItem("token", token);
        window.sessionStorage.setItem("data", JSON.stringify(res.date[0]));
        // 通过编程式导航跳转到后台主页，路由地址是/home
        switch (res.p) {
          case 2:
            this.$router.push("/purchaser");
            break;
          case 3:
            this.$router.push("/president");
            break;
          case 4:
            this.$router.push("/schoolAdmin");
            break;
          case 5:
            this.$router.push("/supper");
            break;
          default:
            return this.$message.error("登陆失败!");
        }
        // this.$message.success("登录成功!");
      } else {
        const { data: res1 } = await this.$http.post(
          "supplierLogin",
          this.loginForm
        );
        if (!res1.success) {
          return this.$message.error("登陆失败!");
        }
        // 将登录成功之后的token,保存到客户端的sessionStorage中
        const token = res1.date[0].token;
        window.sessionStorage.setItem("token", token);
        window.sessionStorage.setItem("data", JSON.stringify(res1.date[0]));
        // 通过编程式导航跳转到后台主页，路由地址是/home
        // this.$message.error("来了!");

        switch (res1.p) {
          case 1:
            this.$router.push("/supplier");
            break;
          default:
            return this.$message.error("登陆失败!");
        }
        this.$message.success("登录成功!");
      }
    },
    enRegister() {
      this.$router.push("/register");
    },
  },
};
</script>
<style lang="less" scoped>
.logo {
  margin-bottom: 20px;
  text-align: center;
}

.login-container {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 300px;
}

.btns {
  text-align: center;
}
</style>
