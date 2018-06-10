<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{name:'docs'}"><i class="fa fa-file"></i>&nbsp;&nbsp;文档库</el-breadcrumb-item>
      <el-breadcrumb-item>{{library.name}}</el-breadcrumb-item>
    </el-breadcrumb>
    <br/>
    <el-row :gutter="20">
      <el-col :span="16">
        <div class="grid-content bg-purple">
          <el-tag type="info">{{library.description}}</el-tag>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-button type="primary" size="small" @click="createDoc">新建文档</el-button>
          <el-button size="small">新建数据模型</el-button>
          <el-button size="small">编辑文档库</el-button>
        </div>
      </el-col>
    </el-row>
    <el-tabs active-name="first">
      <el-tab-pane label="文档列表" name="first">
        <el-table :data="documents" style="width: 100%" stripe>
          <el-table-column prop="title" label="标题"></el-table-column>
          <el-table-column prop="createdOn" label="创建时间" width="200"></el-table-column>
          <el-table-column prop="updatedOn" label="更新时间" width="200"></el-table-column>
          <el-table-column label="操作" width="180">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="warning"
                @click="handleEdit(scope.$index, scope.row)">编辑
              </el-button>
              <el-button
                size="mini"
                type="primary"
                @click="handleBrowse(scope.$index, scope.row)">浏览
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="数据模型" name="second">数据模型</el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  export default {
    name: "docs_browse",
    data() {
      return {
        documents: [],
        library: {name: ""},
        id: this.$route.params.id
      }
    },
    created() {
      this.$http.get(this.ApiUrls.docs.library.browse, {params: {id: this.id}}).then((response) => {
        this.library = response.data.data;
      });
      this.$http.get(this.ApiUrls.docs.library.detail, {params: {id: this.id}}).then((response) => {
        this.documents = response.data.data.documents;
      })
    },
    methods: {
      createDoc() {
        this.$router.push({name: "documentEdit", params: {libId: this.id, id: "new"}});
      },
      handleEdit(index, row) {
        this.$router.push({name: "documentEdit", params: {libId: this.id, id: row.id}});
        console.log(index, row);
      },
      handleBrowse(index, row) {
        this.$router.push({name: "documentBrowse", params: {libId: this.id, id: row.id}});
        console.log(index, row);
      }
    }
  }
</script>

<style scoped>

</style>
