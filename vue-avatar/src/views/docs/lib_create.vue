<template>
  <div style="width: 500px;margin: 10px;">
    <h1>新建文档库</h1>
    <el-tag type="info">创作、管理不同类型的项目文档</el-tag>
    <el-form :model="document" label-position="top">
      <el-form-item label="名称">
        <el-input v-model="document.name"></el-input>
      </el-form-item>
      <el-form-item label="描述">
        <el-input type="textarea" v-model="document.description"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" @click="onSubmit">立即创建</el-button>
        <el-button type="success" size="small" @click="onCancel" plain>返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

  export default {
    data() {
      return {
        document: {
          name: '',
          desc: ''
        }
      }
    },
    methods: {
      onSubmit() {
        this.$http.post(this.ApiUrls.docs.library.create, this.document).then((response) => {
          this.messageAlert(response);
          if (this.isSuccess(response.data.code)) {
            this.onCancel();
          }
        });
      },
      onCancel() {
        this.$router.push({name: "docs"});
      }
    }
  }
</script>

<style scoped>

</style>
