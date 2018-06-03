<template>
  <div>
    <my-breadcrumb v-model="locations"></my-breadcrumb>
    <div class="mine-table-btn-bar">
      <el-button type="primary" icon="el-icon-edit" size="small" plain>主要按钮</el-button>
    </div>
    <v-table
      is-vertical-resize
      :columns="columns"
      :table-data="tableData"
      row-hover-color="#eee"
      row-click-color="#edf7ff"
      :show-vertical-border="true"></v-table>
    <div class="mt20 mb20 bold"></div>
  </div>
</template>

<script>

  var columns = [
    {titleAlign: 'center', width: 60, columnAlign: 'center', type: 'selection'},
    {field: 'name', title: '类型名', isEdit: true, width: 200, titleAlign: 'center', columnAlign: 'center'},
    {field: 'description', title: '描述', isEdit: true, width: 200,  titleAlign: 'center', isResize: true},
    {title: '操作', width: 100,  titleAlign: 'center'}
  ];
  export default {
    data() {
      return {
        tableData: [],
        columns: columns,
        locations:[{name:"API设计"},{name:"数据模型"}]
      }
    },
    methods: {
      getTableData() {
        // this.$http.get({url: "http://www.baidu.com"}).then((response) => {
        //   console.log("success")
        // }), (response) => {
        //   console.log("success")
        // }
        // this.tableConfig.tableData = tableDate.slice((this.pageIndex - 1) * this.pageSize, (this.pageIndex) * this.pageSize)
      }
    },
    created() {
      this.$http.get(this.ApiUrls.apidoc.typeList).then((response) => {
        console.log(response.data)
        this.tableData = response.data
      })
    }
  }
</script>

<style scoped>

</style>
