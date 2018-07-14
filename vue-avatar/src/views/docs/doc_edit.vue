<template>
  <el-container>

    <el-aside width="250px" class="segment-tree">
      <el-tabs active-name="catalog">
        <el-tab-pane label="目录" name="catalog">
          <el-tree
            :data="segmentTree"
            :prop="treeProps"
            ref="segment"
            node-key="id"
            :expand-on-click-node="false"
            @node-expand="treeNodeExpand"
            @node-click="treeNodeClick"
            highlight-current
            default-expand-all
            style="background-color: #eeeeee">
        <span class="custom-tree-node" slot-scope="{ node, data }">
           <span>{{ node.label }}</span>&nbsp;
           <span v-if="node.level <= 2">
              <el-dropdown size="small" placement="bottom-start" @command="treeNodeCommand" trigger="click">
                <span>
                  <i class="el-icon-arrow-down node-arrow"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item :command="{type:'add',node:node,data:data}">添加</el-dropdown-item>
                  <el-dropdown-item :command="{type:'drop',node:node,data:data}">删除</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
           </span>
        </span>
          </el-tree>
        </el-tab-pane>
        <el-tab-pane label="搜索" name="search">搜索</el-tab-pane>
      </el-tabs>

      <el-dialog title="新建文章" :visible.sync="dialog.visible" width="600px">
        <el-form :model="dialog.segment" label-width="80px">
          <el-form-item label="文章名称">
            <el-input v-model="dialog.segment.title" style="width: 350px;"></el-input>
          </el-form-item>
          <el-form-item label="文章类型">
            <el-select v-model="dialog.segment.type" placeholder="请选择文章类型">
              <el-option label="Markdown" value="1"></el-option>
              <el-option label="数据模型" value="2"></el-option>
              <el-option label="HTML" value="3"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialog.visible = false">取 消</el-button>
          <el-button type="primary" @click="submitSegment">确 定</el-button>
        </div>
      </el-dialog>
    </el-aside>
    <el-main class="segment-content">
      <router-view></router-view>
    </el-main>
  </el-container>
</template>

<script>
  export default {
    name: "doc_edit",
    data() {

      return {
        curData: {},
        dialog: {visible: false, segment: {}},
        docId: this.$route.params.docId,
        treeProps: {
          label: 'label',
          children: 'children',
          isLeaf: 'leaf'
        },
        segmentTree: []
      };
    },
    created() {
      this.$http.get(this.ApiUrls.docs.document.segTree, {params: {docId: this.docId}}).then((response) => {
        this.segmentTree = response.data.data;
      });
      var _this = this;
      this.$bus.$on("changeTitle", _this.changeTitle);
    },
    beforeDestroy() {
      var _this = this;
      this.$bus.off('changeTitle', _this.changeTitle);
    },
    methods: {
      changeTitle(title) {
        var node = this.$refs.segment.getNode(1);
        console.log(node.label);
        alert(title);
      },
      treeNodeExpand(data, node, self) {
        // alert(3);
      },
      treeNodeClick(data, node, self) {
        this.$router.push({name: "segmentEdit", params: {segId: node.key, title: node.label}})
      },
      treeNodeCommand(command) {
        if (command.type == "add") {
          this.dialog.visible = true;
          this.curData = command.data;
          return;
        }
      },
      submitSegment() {
        this.dialog.segment.docId = this.docId;
        this.dialog.segment.pid = this.curData.id;
        this.$http.post(this.ApiUrls.docs.segment.save, this.dialog.segment).then((response) => {
          console.log(response);
          if (this.isSuccess(response.data.code)) {
            var segment = response.data.data;
            const newChild = {id: segment.id, label: segment.title, children: []};
            if (!this.curData.children) {
              this.$set(this.curData, 'children', []);
            }
            this.curData.children.push(newChild);
            this.dialog.visible = false;
            this.dialog.segment = {};
          }
        });
      }
    }
  }
</script>

<style scoped>
  .segment-tree {
    min-height: 300px;
  }

  .segment-content {
    background-color: white;
    min-height: 500px;
  }

  .custom-tree-node {
    font-size: 14px;
  }

  .node-arrow {
    font-size: 10px;
    line-height: 30px;
  }
</style>
