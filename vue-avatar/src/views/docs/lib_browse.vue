<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{name:'docs'}"><i class="fa fa-file"></i>&nbsp;&nbsp;文档库</el-breadcrumb-item>
      <el-breadcrumb-item>{{library.name}}</el-breadcrumb-item>
    </el-breadcrumb>
    <br/>
    <el-row :gutter="20">
      <el-col :span="14">
        <div class="grid-content bg-purple">
          <el-tag type="info">{{library.description}}</el-tag>
        </div>
      </el-col>
      <el-col :span="10">
        <div class="grid-content bg-purple" style="float: right;">
          <el-button type="primary" size="small" @click="onCreateDoc">新建文档</el-button>
          <el-button type="success" size="small" @click="onBack" plain>返回</el-button>
        </div>
      </el-col>
    </el-row>
    <el-tabs active-name="first">
      <el-tab-pane label="文档列表" name="first">
        <documentList v-bind:lib-id="id" ref="documentList"></documentList>
      </el-tab-pane>
      <el-tab-pane label="数据模型" name="second">
        <modelList v-bind:lib-id="id" ref="modelList"></modelList>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
  import ModelList from '@/views/docs/lib_browse_model.vue';
  import DocumentList from '@/views/docs/lib_browse_document.vue';
  export default {
    name: "docs_browse",
    data() {
      return {
        library: {name: ""},
        id: this.$route.params.id
      }
    },
    created() {
      this.$http.get(this.ApiUrls.docs.library.browse, {params: {id: this.id}}).then((response) => {
        this.library = response.data.data;
      });
    },
    components: {
      modelList: ModelList,
      documentList: DocumentList
    },
    methods: {
      onCreateDoc() {
        this.$router.push({name: "documentEdit", params: {libId: this.id, id: "new"}});
      },
      onBack() {
        this.$router.push({name: "docs"});
      }
    }
  }
</script>
<style scoped>

</style>
