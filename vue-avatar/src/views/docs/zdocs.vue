<template>
  <div>
    <el-menu default-active="1" class="el-menu-demo" mode="horizontal" style="width: 100%">
      <el-menu-item index="0">
        <router-link to="/docs/create">
          <el-button type="primary" size="small" plain>新建文档库</el-button>
        </router-link>
      </el-menu-item>
      <el-menu-item index="1">
        文档库
      </el-menu-item>
    </el-menu>
    <el-table :data="libraryList" style="width: 100%" height="580" @row-dblclick="handleRowClick" stripe>
      <el-table-column type="selection" width="50"></el-table-column>
      <el-table-column prop="name" label="文档库"></el-table-column>
      <el-table-column prop="description" label="描述"></el-table-column>
      <el-table-column prop="updatedOn" label="更新时间" width="200"></el-table-column>
      <el-table-column label="操作" width="180" fixed="right">
        <template slot-scope="scope">
          <el-button size="mini" icon="el-icon-search" @click="handleOpen(scope.$index, scope.row)" plain>浏览</el-button>
        </template>
      </el-table-column>
    </el-table>
    <br/>
    <el-pagination background
                   @size-change="pageSizeChange"
                   @current-change="pageCurrentChange"
                   :current-page="page.pageNum"
                   :page-sizes="[10, 20, 50, 100]"
                   :page-size="page.pageSize"
                   layout="total, sizes, prev, pager, next"
                   :total="page.total">
    </el-pagination>
  </div>
</template>

<script>


  export default {
    name: "zdocs",
    created() {
      this.pageSearch();
    },
    data() {
      return {
        libraryList: [],
        page: {
          pageNum: 1,
          pageSize: 10,
          total: 0
        }
      }
    },
    methods: {
      handleRowClick(row, event) {
        this.browseEvent(row);
      },
      handleOpen(index, row) {
        this.browseEvent(row);
      },
      browseEvent(row) {
        this.$router.push({name: "libraryBrowse", params: {id: row.id}});
      },
      pageSearch() {
        this.$http.post(this.ApiUrls.docs.library.search, {
          pageNum: this.page.pageNum,
          pageSize: this.page.pageSize
        }).then((response) => {
          this.page.total = response.data.data.total;
          this.libraryList = response.data.data.rows;
        })
      },
      pageSizeChange(val) {
        this.page.pageSize = val;
        this.pageSearch();
      },
      pageCurrentChange(val) {
        this.page.pageNum = val;
        this.pageSearch();
      }
    }
  }
</script>
