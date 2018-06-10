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
    <el-table :data="tableData" style="width: 100%" stripe>
      <el-table-column prop="name" label="文档库"></el-table-column>
      <el-table-column prop="description" label="描述"></el-table-column>
      <el-table-column prop="updatedOn" label="更新时间" width="200"></el-table-column>
      <el-table-column label="操作" width="180">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleOpen(scope.$index, scope.row)">打开
          </el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
  export default {
    name: "zdocs",
    created() {
      this.$http.post(this.ApiUrls.docs.library.list).then((response) => {
        this.tableData = response.data.data;
      })
    },
    data() {
      return {
        tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }]
      }
    },
    methods: {
      handleOpen(index, row) {
        this.$router.push({name: "libraryBrowse", params:{id: row.id}});
        console.log(index, row);
      }
    }
  }
</script>
