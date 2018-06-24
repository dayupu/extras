<template>
  <div>
    <el-row>
      <el-col :span="24">
        <div style="margin-bottom: 10px;float: right;">
          <el-button @click="dialog.visible = true" size="mini" type="primary">新建文档</el-button>
        </div>
      </el-col>
    </el-row>
    <my-table-search :items="docItems" @onSearch="onSearch" show-button
                     @onSwitch="searchSwitch" @onSubmit="searchSubmit"></my-table-search>
    <el-table :data="documents" style="width: 100%;" height="500" @row-dblclick="onRowDoubleClick" stripe>
      <el-table-column prop="title" label="标题"></el-table-column>
      <el-table-column prop="createdOn" label="创建时间" width="200"></el-table-column>
      <el-table-column prop="updatedOn" label="更新时间" width="200"></el-table-column>
      <el-table-column label="操作" width="180" fixed="right" v-if="showOperation">
        <template slot-scope="scope">
          <el-button-group>
            <el-button size="mini" @click="onEdit(scope.$index, scope.row)"
                       icon="el-icon-edit" round></el-button>
            <el-button size="mini" type="danger" @click="onDrop(scope.$index, scope.row)"
                       icon="el-icon-delete" round></el-button>
          </el-button-group>
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
    <el-dialog :title="dialog.title" :visible.sync="dialog.visible" width="600px">
      <el-form :model="dialog.document" :rules="dialog.rules" ref="dialog.document" label-width="80px"
               label-position="right">
        <el-form-item label="标题" prop="title">
          <el-input size="small" v-model="dialog.document.title" style="width: 200px"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input type="textarea" size="small" v-model="dialog.document.description" :rows="5"
                    style="width: 400px"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialog.visible = false">取 消</el-button>
        <el-button type="primary" @click="submitDocument()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "doc_browse",
    data() {
      return {
        dialog: {
          title: "新建文档",
          visible: false,
          rules: {
            title: [
              {required: true, message: '请输入文档标题', trigger: 'blur'},
              {min: 1, max: 100, message: '长度在 1 到 100 个字符', trigger: 'blur'}
            ],
            description: [
              {required: true, message: '请输入文档描述', trigger: 'blur'},
              {min: 1, max: 255, message: '长度在 1 到 255 个字符', trigger: 'blur'}
            ]
          },
          document: {
            id: "",
            title: "",
            description: ""
          }
        },
        documents: [],
        changeDocs: [],
        docItems:
          [
            {title: "标题", name: "title", type: "text"},
            {title: "创建时间", name: "createdOn", type: "datetime"},
            {title: "更新时间", name: "updatedOn", type: "datetime"},
          ],
        searchParam: {},
        page: {
          num: 1,
          size: 10,
          total: 0
        },
        showOperation: false
      }
    }
    ,
    created() {
      this.pageSearch();
    },
    methods: {
      onRowDoubleClick(row, event) {
        this.$router.push({name: "docEdit", params: {docId: row.id}});
      },
      onSearch(search) {
        this.page.num = 1;
        this.searchParam = search;
        this.pageSearch();
      },
      onDrop(index, row) {
        this.$confirm('此操作将删除选中的文档, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.post(this.ApiUrls.docs.document.drop, row).then((response) => {
            if (this.messageAlert(response)) {
              document = this.emptyDocument;
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
      searchSwitch(on) {
        if (on) {
          this.showOperation = true;
        } else {
          this.showOperation = false;
        }
      },
      searchSubmit() {
        this.$http.post(this.ApiUrls.docs.document.move, this.changeDocs).then((response) => {
          if (this.messageAlert(response)) {
            this.changeDocs = [];
          }
        });
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
          console.log(response.data.data.rows)
        })
      },
      submitDocument() {
        this.$refs["dialog.document"].validate((valid) => {
          if (valid) {
            this.$http.post(this.ApiUrls.docs.document.save, this.dialog.document).then((response) => {
              if (this.messageAlert(response)) {
                this.dialog.document = {};
                this.dialog.visible = false;
                this.pageSearch();
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      }
    }
  }
</script>

<style scoped>

</style>
