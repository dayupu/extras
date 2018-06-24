<template>
  <div class="div-container">
    <el-tabs active-name="first">
      <el-tab-pane label="文档列表" name="first">
        <documentList ref="documentList"></documentList>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import DocumentList from "@/views/docs/doc_list.vue";

  export default {
    name: "doc-main",
    components: {
      documentList: DocumentList
    }
  }
</script>
<style scoped>
  .div-container {
    margin: 0 auto;
    width: 90%;
    padding: 10px;
    background-color: white;
    border: 1px solid #eeeeee;
  }
</style>
