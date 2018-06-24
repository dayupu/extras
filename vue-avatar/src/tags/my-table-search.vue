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
        <div style="float: right;margin-right: 10px;">
          <template v-if="showButton">
            <el-switch v-model="switchOn" @change="onSwitch"></el-switch>
            <el-button size="small" type="success" :disabled="!switchOn" icon="el-icon-check" v-on:click="onSubmit"
                       round></el-button>
          </template>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "my-table-search",
    props: {
      items: {type: Array, default: () => ([])},
      showButton: {type: Boolean, default: false}
    },
    data() {
      return {
        allItems: [{title: "全部", name: "", type: null}, ...this.items],
        selectItem: "",
        inputText: null,
        inputDate: null,
        switchOn: false,
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
      onSwitch: function () {
        this.$emit('onSwitch', this.switchOn);
      },
      onSubmit: function () {
        this.$emit('onSubmit');
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
