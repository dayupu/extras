<template>
  <el-container>

    <el-aside width="200px" class="segment-tree">
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
      <el-dialog title="收货地址" :visible.sync="dialogFormVisible">

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
      const data = [{
        id: 1,
        label: '一级 1',
        children: [{
          id: 4,
          label: '二级 1-1',
          children: [{
            id: 9,
            label: '三级 1-1-1'
          }, {
            id: 10,
            label: '三级 1-1-2'
          }]
        }]
      }, {
        id: 2,
        label: '一级 2',
        children: [{
          id: 5,
          label: '二级 2-1'
        }, {
          id: 6,
          label: '二级 2-2'
        }]
      }, {
        id: 3,
        label: '一级 3',
        children: [{
          id: 7,
          label: '二级 3-1'
        }, {
          id: 8,
          label: '二级 3-2'
        }]
      }];
      return {
        dialogFormVisible: false,
        treeProps: {
          label: 'label',
          children: 'children',
          isLeaf: 'leaf'
        },
        segmentTree: JSON.parse(JSON.stringify(data))
      };
    },
    created() {
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
        console.log(data);
        console.log(node);
        this.$router.push({name: "segmentEdit", params: {segId: "xxx"}})
      },
      treeNodeCommand(command) {
        var data = command.data;
        if (command.type == "add") {
          this.dialogFormVisible = true;
          return;
        }
        const newChild = {id: '111', label: 'testtest', children: []};
        if (!data.children) {
          this.$set(data, 'children', []);
        }
        data.children.push(newChild);
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
