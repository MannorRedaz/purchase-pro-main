<template>
  <div class="register_container">
    <header class="login-header">
      <div class="container m-top-20">
        <h1 class="logo">
          <RouterLink to="/">实验耗材采购系统</RouterLink>
        </h1>
        <RouterLink class="entry text-decoration" to="/login" style="margin-right: 50px;">
          点击登录
          <i class="iconfont icon-angle-right"></i>
          <i class="iconfont icon-angle-right"></i>
        </RouterLink>
      </div>
    </header>
    <section class="login-section">
      <div class="wrapper">
        <nav>
          <a href="javascript:;" class="text-decoration">供应商账户注册</a>
        </nav>
        <div class="account-box">
          <div class="form">
            <el-form ref="signUpFormRef" :model="signUpForm" label-position="right" label-width="100px" status-icon>
              <!-- 登录选择 -->
              <el-form-item label="账号" prop="name">
                <el-input v-model="signUpForm.name"></el-input>
              </el-form-item>
              <el-form-item label="姓名" style="">
                <el-input v-model="signUpForm.contract_name"></el-input>
              </el-form-item>
              <el-form-item label="电话">
                <el-input v-model="signUpForm.tel"></el-input>
              </el-form-item>
              <el-form-item label="密码">
                <el-input v-model="signUpForm.pwd" type="password"></el-input>
              </el-form-item>
              <el-form-item label="银行卡账号">
                <el-input v-model="signUpForm.account"></el-input>
              </el-form-item>
              <el-form-item label="公司名">
                <el-input v-model="signUpForm.company_name"></el-input>
              </el-form-item>
              <el-form-item label="公司所在地">
                <el-input v-model="signUpForm.company_place"></el-input>
              </el-form-item>
              <el-button size="large" class="subBtn" @click="register">点击注册</el-button>
            </el-form>
          </div>
        </div>
      </div>
    </section>


  </div>
</template>
<script>
export default {
  data() {
    const that = this
    async function supplierCheck(rule, value, callback) {
      const { data: res } = await that.$http.post('supplierCheck', value)
      if (res.success) {
        callback()
      } else {
        return callback(new Error(res.msg))
      }
    }
    return {
      signUpForm: {
        contract_name: '',
        tel: '',
        name: '',
        pwd: '',
        account: '',
        company_name: '',
        company_place: '',
        cid: '1',
        id: '33'
      },
      // 这是表单的验证规则对象
      signUpFormRules: {
        // 验证用户名是否合法
        name: [
          { required: true, message: '请正确输入账号', trigger: 'blur' },
          {
            min: 3,
            max: 10,
            message: '长度应该在3到10个字符之间',
            trigger: 'blur'
          },
          {
            validator: supplierCheck,
            trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    register() {
      this.$refs.signUpFormRef.validate(async valid => {
        if (!valid) {
          return;
        }
        this.signUpForm.id = null;
        if (this.signUpForm.name == null || this.signUpForm.name == "") {
          this.$message.warning("请输入账号");
          return;
        }
        if (this.signUpForm.tel.length != 11) {
          this.$message.warning("电话格式不对");
          return;
        }
        const { data: res } = await this.$http.post('supplierRegister', this.signUpForm)
        if (res.success) {
          this.$message.success(res.msg);
          this.$router.replace('/login');
        }
        else
          this.$message.error(res.msg);
        // window.sessionStorage.setItem('token', res.data.token)
        // 通过编程式导航跳转到后台主页，路由地址是/home
      })
    },
    restSignUpForm() {
      this.$router.replace('/login');
    }
  }
}
</script>
<style lang="less" scoped>
.login-header {
  background: #fff;
  border-bottom: 1px solid #e4e4e4;

  .container {
    display: flex;
    align-items: flex-end;
    justify-content: space-between;
    height: 110px;
  }

  .logo {
    width: 200px;

    a {
      display: block;
      height: 95px;
      width: 100%;
      text-indent: -9999px;
      background: url("../../assets/img/logo.png") no-repeat center 18px / contain;
    }
  }

  .sub {
    flex: 1;
    font-size: 24px;
    font-weight: normal;
    margin-bottom: 38px;
    margin-left: 20px;
    color: #666;
  }

  .entry {
    width: 120px;
    margin-bottom: 38px;
    font-size: 16px;

    i {
      font-size: 14px;
      color: #2e7de6;
      letter-spacing: -5px;
    }
  }
}

.login-section {
  background: url('../../assets/img/login-bg.jpg') no-repeat center / cover;
  height: 584px;
  position: relative;

  .wrapper {
    width: 482px;
    background: #fff;
    position: absolute;
    left: 30%;
    top: 5px;
    transform: translate3d(100px, 0, 0);
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);

    nav {
      font-size: 14px;
      height: 55px;
      margin-bottom: 20px;
      border-bottom: 1px solid #f5f5f5;
      display: flex;
      padding: 0 40px;
      text-align: right;
      align-items: center;

      a {
        flex: 1;
        line-height: 1;
        display: inline-block;
        font-size: 18px;
        position: relative;
        text-align: center;
      }

    }
  }
}

.el-input__inner1 {
  margin-left: 20px;
  width: 90% !important;
}

.el-form-item__label1 {
  line-height: 20px;
}

.login-footer {
  padding: 30px 0 50px;
  background: #fff;

  p {
    text-align: center;
    color: #999;
    padding-top: 20px;

    a {
      line-height: 1;
      padding: 0 10px;
      color: #999;
      display: inline-block;

      ~a {
        border-left: 1px solid #ccc;
      }
    }
  }
}

.account-box {
  .toggle {
    padding: 15px 40px;
    text-align: right;

    a {
      color: #2e7de6;

      i {
        font-size: 14px;
      }
    }
  }

  .form {
    padding: 0 20px 20px 20px;

    &-item {
      margin-bottom: 28px;

      .input {
        position: relative;
        height: 36px;

        >i {
          width: 34px;
          height: 34px;
          background: #cfcdcd;
          color: #fff;
          position: absolute;
          left: 1px;
          top: 1px;
          text-align: center;
          line-height: 34px;
          font-size: 18px;
        }

        input {
          padding-left: 44px;
          border: 1px solid #cfcdcd;
          height: 36px;
          line-height: 36px;
          width: 100%;

          &.error {
            border-color: #cf4444;
          }

          &.active,
          &:focus {
            border-color: #2e7de6;
          }
        }

        .code {
          position: absolute;
          right: 1px;
          top: 1px;
          text-align: center;
          line-height: 34px;
          font-size: 14px;
          background: #f5f5f5;
          color: #666;
          width: 90px;
          height: 34px;
          cursor: pointer;
        }
      }

      >.error {
        position: absolute;
        font-size: 12px;
        line-height: 28px;
        color: #cf4444;

        i {
          font-size: 14px;
          margin-right: 2px;
        }
      }
    }

    .agree {
      a {
        color: #069;
      }
    }

    .btn {
      display: block;
      width: 100%;
      height: 40px;
      color: #fff;
      text-align: center;
      line-height: 40px;
      background: #2e7de6;

      &.disabled {
        background: #cfcdcd;
      }
    }
  }

  .action {
    padding: 20px 40px;
    display: flex;
    justify-content: space-between;
    align-items: center;

    .url {
      a {
        color: #999;
        margin-left: 10px;
      }
    }
  }
}

.subBtn {
  background: #2e7de6;
  width: 100%;
  color: #fff;
}

.text-decoration {
  color: inherit;
  text-decoration: none;
}
</style>
