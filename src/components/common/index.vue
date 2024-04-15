<template>
    <div>
        <header class="login-header">
            <div class="container m-top-20">
                <h1 class="logo">
                    <RouterLink to="/">实验耗材采购系统</RouterLink>
                </h1>
                <RouterLink class="entry text-decoration" to="/register" style="margin-right: 50px;">
                    供应商注册
                    <i class="iconfont icon-angle-right"></i>
                    <i class="iconfont icon-angle-right"></i>
                </RouterLink>
            </div>
        </header>
        <section class="login-section">
            <div class="wrapper">
                <nav>
                    <a href="javascript:;" class="text-decoration">账户登录</a>
                </nav>
                <div class="account-box">
                    <div class="form">
                        <el-form ref="loginForm" :model="loginForm" label-position="right" label-width="60px"
                            status-icon>
                            <!-- 登录选择 -->
                            <el-form-item label-width="10px">
                                <div class="select">
                                    <el-radio v-model="value" label="1">管理员</el-radio>
                                    <el-radio v-model="value" label="0">供应商</el-radio>
                                </div>
                            </el-form-item>
                            <el-form-item prop="name" label="账户" :rules="rules.name">
                                <el-input v-model="loginForm.name" />
                            </el-form-item>
                            <el-form-item prop="pwd" label="密码" :rules="rules.pwd">
                                <el-input v-model="loginForm.pwd" type="password" />
                            </el-form-item>
                            <el-form-item prop="agree" label-width="22px" :rules="rules.agree">
                                <el-checkbox size="large" v-model="loginForm.agree">
                                    我已同意隐私条款和服务条款
                                </el-checkbox>
                            </el-form-item>
                            <el-button size="large" class="subBtn" @click="login('loginForm')">点击登录</el-button>
                        </el-form>
                    </div>
                </div>
            </div>
        </section>
        <!-- 
        <footer class="login-footer">
            <div class="container">
                <p>
                    <a href="javascript:;">关于我们</a>
                    <a href="javascript:;">帮助中心</a>
                    <a href="javascript:;">售后服务</a>
                    <a href="javascript:;">配送与验收</a>
                    <a href="javascript:;">商务合作</a>
                    <a href="javascript:;">搜索推荐</a>
                    <a href="javascript:;">友情链接</a>
                </p>
                <p>CopyRight &copy; 实验耗材采购系统</p>
            </div>
        </footer> -->
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
                agree: true,
            },
            value: '0',
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
            rules: {
                name: [
                    { required: true, message: '请输入账号', trigger: 'blur' }
                ],
                pwd: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 6, max: 16, message: '密码长度为6-16字符', trigger: 'blur' }
                ],
                agree: [
                    {
                        validator(rule, value, callback) {
                            console.log(value);
                            if (value) {
                                callback()
                            } else {
                                callback(new Error('请勾选同意'))
                            }
                        }
                    }
                ]
            }
        };
    },
    methods: {

        // 点击登录按钮，进行登录数据的预验证
        login(form) {
            this.$refs[form].validate((valid) => {
                if (valid) {
                    this.loginAction();
                    // console.log(valid);
                } else {
                    this.$message.error("请完善输入的信息！");
                    // console.log(valid);
                }
            });
        },
        async loginAction() {
            if (this.loginForm.value == 1) {
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
                        this.$router.replace("/purchaser");
                        break;
                    case 3:
                        this.$router.replace("/president");
                        break;
                    case 4:
                        this.$router.replace("/schoolAdmin");
                        break;
                    case 5:
                        this.$router.replace("/supper");
                        break;
                    default:
                        return this.$message.error("登陆失败!");

                }
                this.$message.success("登录成功!");
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
                        this.$router.replace("/supplier");
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
        top: 54px;
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
