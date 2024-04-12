<template>
  <div class="register_container">
    <el-header>这是首部</el-header>
    <el-card class="register_box">
      <el-row>
        <el-col :span="12" class="form">
          <el-form ref="signUpFormRef" :model="signUpForm" label-width="100px" :rules="signUpFormRules">
            <el-form-item label="姓名">
              <el-input v-model="signUpForm.contract_name"></el-input>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="signUpForm.tel"></el-input>
            </el-form-item>
            <el-form-item label="账号" prop="name">
              <el-input v-model="signUpForm.name"></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="signUpForm.pwd"></el-input>
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
            <el-form-item>
              <el-button type="primary" @click="register">立即注册</el-button>
              <el-button @click="restSignUpForm()">取消</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </el-card>
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
        pwd: '123456',
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
          { required: true, message: '请输入用户名', trigger: 'blur' },
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
        // console.log("valid" + valid);
        this.signUpForm.id = null;
        if (this.signUpForm.name == null || this.signUpForm.name == "") {
          this.$message.warning("请输入用户名");
          return;
        }
        if (this.signUpForm.tel.length != 11) {
          this.$message.warning("电话格式不对");
          return;
        }

        const { data: res } = await this.$http.post('supplierRegister', this.signUpForm)

        if (res.success)
          this.$message.success(res.msg);
        else
          this.$message.error(res.msg);
        window.sessionStorage.setItem('token', res.data.token)
        // 通过编程式导航跳转到后台主页，路由地址是/home
        this.$router.push('/login')
      })
    },
    restSignUpForm() {
      // console.log(this);
      // console.log(this.$refs.signUpFormRef)
      // this.$refs.signUpFormRef.resetFields()
      //   this.$router.push("/Login");
      this.$router.push('/login');
    }
  }
}
</script>
<style lang="less" scoped>
.register_container {
  height: 100%;
  background-color: aqua;

  .register_box {
    padding-top: 20px;
    width: 1180px;
    margin: 20px auto 0;
  }

  .form {
    position: relative;
    left: 50%;
    transform: translateX(-50%);
  }
}
</style>
