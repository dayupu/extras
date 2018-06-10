<template>

  <el-container>
    <el-header style="height: 80px;">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{name:'docs'}"><i class="fa fa-file"></i>&nbsp;&nbsp;文档库</el-breadcrumb-item>
        <el-breadcrumb-item :to="{name:'libraryBrowse',params: {id: library.id}}">{{library.name}}</el-breadcrumb-item>
        <el-breadcrumb-item></el-breadcrumb-item>
      </el-breadcrumb>
      <br/>
      <el-row :gutter="20">
        <el-col :span="20">
          <div class="grid-content bg-purple">
            <el-input placeholder="请输入标题" size="small" style="width: 300px;" v-model="document.title">
              <template slot="prepend">标题</template>
            </el-input>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple">
            <el-button type="primary" size="small" @click="saveDoc">保存</el-button>
            <el-button size="small" @click="handleCancel">取消</el-button>
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <mavon-editor :ishljs="true" style="min-height: 700px;" v-model="document.content" @save="saveMarkdown"></mavon-editor>
    </el-main>
  </el-container>
</template>

<script>
  export default {
    name: "doc_edit",
    data() {
      return {
        libId: this.$route.params.libId,
        paramId: this.$route.params.id,
        library: {},
        document: {}
      }
    },
    created() {
      this.$http.get(this.ApiUrls.docs.library.browse, {params: {id: this.libId}}).then((response) => {
        this.library = response.data.data;
      });
      if (this.paramId != "new") {
        this.$http.get(this.ApiUrls.docs.document.browse, {params: {id: this.paramId}}).then((response) => {
          this.document = response.data.data;
        });
      }


    },
    methods: {
      saveDoc() {
        this.document.libId = this.libId;
        this.$http.post(this.ApiUrls.docs.document.save, this.document).then((response) => {
          this.messageAlert(response);
          if (!this.isSuccess(response.data.code)) {
            return;
          }
          this.document = response.data.data;
          if (this.paramId == "new") {
            this.$router.push({name: "documentEdit", params: {libId: this.libId, id: this.document.id}});
          }
        })
      },
      handleCancel() {
        this.$router.push({name: 'libraryBrowse', params: {id: this.libId}});
      },
      saveMarkdown(){
        this.saveDoc();
      }
    }
  }
</script>

<style scoped>
  .el-main {
    background-color: #eee;
  }
</style>
