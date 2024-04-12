<template>
  <el-upload
  class="upload-demo"
  action="/native"
           :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
  multiple
  :limit="3"
  :file-list="fileList">
  <el-button size="small" type="primary" :src="imageUrl" >点击上传
</el-button>
  <div slot="tip" class="el-upload__tip">只能上传XXX文件</div>
</el-upload>
</template>

<script>
export default {
  data() {
    return {
      imageUrl: "",
    };
  },
  method: {
    //文件上传
    handleAvatarSuccess(res, file) {
      if (file.response) {
        var self = this;
        self.imageUrl = file.response;
        //   console.log(self.imageUrl);
      }
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === ".jpg/.jpeg";
      const isLt10M = file.size / 1024 / 1024 < 10;
      if (!isJPG) {
        this.$message.error("上传文件只能是 jpg/jpeg格式!");
      }
      if (!isLt10M) {
        this.$message.error("上传文件大小不能超过 10MB!");
      }
      return isLt10M; //isJPG &&
    },
  },
};
</script>

<style>
</style>

