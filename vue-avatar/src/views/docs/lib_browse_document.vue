<template>
  <div>
    <my-table-search :items="docItems" @onSearch="onSearch" enable-move @onMove="onMove"
                     @onSave="onSave"></my-table-search>
    <el-table :data="documents" style="width: 100%;" height="580"
              @row-dblclick="onRowDoubleClick" @selection-change="onSelectChange" stripe>
      <el-table-column label="移动" width="80" header-align="center" align="center" v-if="showMove">
        <template slot-scope="scope">
          <el-button-group>
            <el-button @click="onMoveColumn(scope.$index,scope.row,true)"
                       size="mini" icon="el-icon-arrow-up" circle></el-button>
            <el-button @click="onMoveColumn(scope.$index,scope.row, false)"
                       size="mini" icon="el-icon-arrow-down" circle></el-button>
          </el-button-group>
        </template>
      </el-table-column>
      <el-table-column prop="title" label="标题"></el-table-column>
      <el-table-column prop="createdOn" label="创建时间" width="200"></el-table-column>
      <el-table-column prop="updatedOn" label="更新时间" width="200"></el-table-column>
      <el-table-column label="操作" width="180" fixed="right">
        <template slot-scope="scope">
          <el-button size="small" type="success" @click="onBrowse(scope.$index, scope.row)"
                     icon="el-icon-search" plain></el-button>
          <el-button size="small" type="warning" @click="onEdit(scope.$index, scope.row)"
                     icon="el-icon-edit" plain></el-button>
        </template>
      </el-table-column>
    </el-table>
    <br/>
    <el-pagination background
                   @size-change="onPageSizeChange"
                   @current-change="onPageNumChange"
                   :current-page="page.num"
                   :page-sizes="[10, 20, 50, 100]"
                   :page-size="page.size"
                   layout="total, sizes, prev, pager, next"
                   :total="page.total">
    </el-pagination>
  </div>
</template>

<script>
  export default {
    name: "doc_browse",
    props: {
      libId: ""
    },
    data() {
      return {
        documents: [],
        docItems: [
          {title: "标题", name: "title", type: "text"},
          {title: "创建时间", name: "createdOn", type: "datetime"},
          {title: "更新时间", name: "updatedOn", type: "datetime"},
        ],
        selectRows: [],
        searchParam: {},
        page: {
          num: 1,
          size: 10,
          total: 0
        },
        showMove: true
      }
    },
    created() {
      this.pageSearch();
    },
    methods: {
      onEdit(index, row) {
        this.$router.push({name: "documentEdit", params: {libId: this.libId, id: row.id}});
        console.log(index, row);
      },
      onBrowse(index, row) {
        this.browseDocument(row);
      },
      onRowDoubleClick(row, event) {
        this.browseDocument(row);
      },
      onSearch(search) {
        this.page.num = 1;
        this.searchParam = search;
        this.pageSearch();
      },
      onSelectChange(rows) {
        this.selectRows = rows;
      },
      onDrop() {
        if (this.selectRows.length == 0) {
          return;
        }
        this.$confirm('此操作将删除选中的文档, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.post(this.ApiUrls.docs.document.drop, this.selectRows).then((response) => {
            if (this.messageAlert(response)) {
              this.pageSearch();
            }
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      onMove(value) {
        if (value) {
          this.showMove = true;
        } else {
          this.showMove = false;
        }
      },
      onSave() {

      },
      onMoveColumn(index, row, isUp) {
        if (index == 0 && isUp) {
          return;
        }
        let curCols = this.documents.splice(index, 1);
        let relateCol = {};
        if (isUp) {
          relateCol = this.documents[index - 1];
          this.documents.splice(index - 1, 0, ...curCols);
        } else {
          relateCol = this.documents[index];
          this.documents.splice(index + 1, 0, ...curCols);
        }

        alert(relateCol.title);
      },
      onPageSizeChange(val) {
        this.page.size = val;
        this.pageSearch();
      },
      onPageNumChange(val) {
        this.page.num = val;
        this.pageSearch();
      },
      pageSearch() {
        this.$http.post(this.ApiUrls.docs.document.search, {
          libId: this.libId,
          page: this.page,
          search: this.searchParam
        }).then((response) => {
          this.page.total = response.data.data.total;
          this.documents = response.data.data.rows;
        })
      },
      browseDocument(row) {
        window.open("/view/browse/doc/" + row.id, '_blank');
      }
    }
  }
</script>

<style scoped>

</style>
