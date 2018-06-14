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
          <el-button type="primary" size="small" @click="createDoc">新建文档</el-button>
          <el-button type="primary" size="small">新建数据模型</el-button>
          <el-button type="success" size="small" @click="onCancel" plain>返回</el-button>
        </div>
      </el-col>
    </el-row>
    <el-tabs active-name="first">
      <el-tab-pane label="文档列表" name="first">
        <my-table-search :items="docItems" @onSearch="opSearch" enable-edit @onEdit="opEdit"></my-table-search>
        <el-table :data="documents" style="width: 100%;" height="580" @row-dblclick="handleRowClick" stripe>
          <el-table-column type="selection" width="50"></el-table-column>
          <el-table-column prop="title" label="标题"></el-table-column>
          <el-table-column prop="createdOn" label="创建时间" width="200"></el-table-column>
          <el-table-column prop="updatedOn" label="更新时间" width="200"></el-table-column>
          <el-table-column label="操作" width="180" fixed="right">
            <template slot-scope="scope">
              <el-button-group>
                <el-button size="mini" icon="el-icon-search" @click="handleBrowse(scope.$index, scope.row)">
                </el-button>
                <el-button size="mini" type="warning" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">
                </el-button>
              </el-button-group>
            </template>
          </el-table-column>
        </el-table>
        <br/>
        <el-pagination background
                       @size-change="pageSizeChange"
                       @current-change="pageCurrentChange"
                       :current-page="page.num"
                       :page-sizes="[10, 20, 50, 100]"
                       :page-size="page.size"
                       layout="total, sizes, prev, pager, next"
                       :total="page.total">
        </el-pagination>
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
        id: this.$route.params.id,
        docItems: [
          {title: "标题", name: "title", type: "text"},
          {title: "创建时间", name: "createdOn", type: "datetime"},
          {title: "更新时间", name: "updatedOn", type: "datetime"},
        ],
        searchParam: {},
        page: {
          num: 1,
          size: 10,
          total: 0
        }
      }
    },
    created() {
      this.$http.get(this.ApiUrls.docs.library.browse, {params: {id: this.id}}).then((response) => {
        this.library = response.data.data;
      });
      this.pageSearch();
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
        this.browseEvent(row);
      },
      handleRowClick(row, event) {
        this.browseEvent(row);
      },
      browseEvent(row) {
        this.$router.push({name: "documentBrowse", params: {libId: this.id, id: row.id}});
      },
      onCancel() {
        this.$router.push({name: "docs"});
      },
      pageSearch() {
        this.$http.post(this.ApiUrls.docs.document.search, {
          libId: this.id,
          page: this.page,
          search: this.searchParam
        }).then((response) => {
          this.page.total = response.data.data.total;
          this.documents = response.data.data.rows;
        })
      },
      pageSizeChange(val) {
        this.page.size = val;
        this.pageSearch();
      },
      pageCurrentChange(val) {
        this.page.num = val;
        this.pageSearch();
      },
      opEdit() {

      },
      opSearch(search) {
        this.page.num = 1;
        this.searchParam = search;
        this.pageSearch();
      }
    }
  }
</script>

<style scoped>

  .el-table thead {
    background-color: #eee;
  }

</style>
