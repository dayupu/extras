<template>
  <el-container>
    <el-header style="height: 80px;">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{name:'docs'}"><i class="fa fa-file"></i>&nbsp;&nbsp;文档库</el-breadcrumb-item>
        <el-breadcrumb-item :to="{name:'libraryBrowse',params: {id: library.id}}">{{library.name}}</el-breadcrumb-item>
        <el-breadcrumb-item>{{document.title}}</el-breadcrumb-item>
      </el-breadcrumb>
      <br/>
      <el-row :gutter="20">
        <el-col :span="23">
          <div style="font-weight: bold;font-size: 18px;color: #0c7cd5;text-align: center;">{{document.title}}</div>
        </el-col>
        <el-col :span="1">
          <div class="grid-content bg-purple">
            <el-button type="success" size="small" @click="handleCancel" plain>返回</el-button>
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <div class="my-doc-content">
        <vue-markdown :source="document.content" v-highlight></vue-markdown>
      </div>

    </el-main>
  </el-container>
</template>

<script>
  export default {
    name: "doc_edit",
    data() {
      return {
        params: {
          libId: this.$route.params.libId,
          id: this.$route.params.id
        },
        library: {},
        document: {}
      }
    },
    created() {
      this.$http.get(this.ApiUrls.docs.library.browse, {params: {id: this.params.libId}}).then((response) => {
        this.library = response.data.data;
      });
      this.$http.get(this.ApiUrls.docs.document.browse, {params: {id: this.params.id}}).then((response) => {
        this.document = response.data.data;
      });
    },
    methods: {
      handleCancel() {
        this.$router.push({name: 'libraryBrowse', params: {id: this.params.libId}});
      }
    }
  }
</script>

<style scoped>
  .my-doc-content {
    background-color: white;
    margin: 0 auto;
    padding: 10px 20px;
    width: 1000px;
    min-height: 600px;
    border: 1px solid #E3E3E3;
  }
</style>
