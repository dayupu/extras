<template>
  <div class="table-search-content">
    <el-row :gutter="20">
      <el-col :span="19">
        <el-select v-model="selectItem" @change="itemChange" size="small" style="width: 130px;" filterable
                   placeholder="请选择">
          <el-option
            v-for="item in allItems"
            :key="item.name"
            :label="item.title"
            :value="item.name">
          </el-option>
        </el-select>
        <template v-if="targetItem.type == 'text'">
          <el-input v-model="inputText" size="small" style="width: 200px;"
                    placeholder="请输入内容"></el-input>
        </template>
        <template v-if="targetItem.type == 'date'">
          <el-date-picker
            size="small"
            v-model="inputDate"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
        </template>
        <template v-if="targetItem.type == 'datetime'">
          <el-date-picker
            v-model="inputDate" size="small" type="datetimerange" range-separator="至" start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
        </template>
        <el-button size="small" type="primary" icon="el-icon-search" v-on:click="onSearch"></el-button>
      </el-col>
      <el-col :span="5">
        <el-button-group style="float: right;">
          <template v-if="this.enableEdit">
            <el-button size="small" type="warning" icon="el-icon-edit" v-on:click="onEdit"></el-button>
          </template>
          <template v-if="this.enableDrop">
            <el-button size="small" type="danger" icon="el-icon-delete" v-on:click="onDrop"></el-button>
          </template>
          <template v-if="this.enableMove">
            <el-button size="small" type="success" icon="el-icon-caret-top" v-on:click="onMove('up')"></el-button>
            <el-button size="small" type="success" icon="el-icon-caret-bottom" v-on:click="onMove('down')"></el-button>
          </template>
        </el-button-group>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "my-table-search",
    props: {
      items: {type: Array, default: () => ([])},
      enableEdit: {type: Boolean, default: false},
      enableDrop: {type: Boolean, default: false},
      enableMove: {type: Boolean, default: false}
    },
    data() {
      return {
        allItems: [{title: "全部", name: "", type: null}, ...this.items],
        selectItem: "",
        inputText: null,
        inputDate: null,
        targetItem: {}
      }
    },
    methods: {
      itemChange: function (name) {
        for (let index in this.allItems) {
          if (this.allItems[index].name == name) {
            this.targetItem = this.allItems[index];
          }
        }
      },
      onSearch: function () {
        let value = null;
        if (this.targetItem.type == "text") {
          value = this.inputText;
        } else if (this.targetItem.type == "date" || this.targetItem.type == "datetime") {
          value = this.inputDate;
        }
        this.$emit('onSearch', {name: this.targetItem.name, type: this.targetItem.type, value: value});
      },
      onEdit: function () {
        this.$emit('onEdit');
      }
      ,
      onDrop: function () {
        this.$emit('onDrop');
      }
      ,
      onMove: function (val) {
        this.$emit('onMove', val);
      }
    }

  }
</script>

<style scoped>
  .table-search-content {
    padding: 5px;
    background-color: #eee;
  }
</style>
