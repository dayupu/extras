<template>
  <div>
    <div>
      <el-row>
        <el-col :span="12">
          <el-button-group>
            <el-button size="small" type="primary" icon="el-icon-plus" @click="addClazz()"></el-button>
            <el-button size="small" type="danger" icon="el-icon-delete" @click="dropClazz()"></el-button>
            <el-button size="small" icon="el-icon-caret-left" @click="moveClazz(true)"></el-button>
            <el-button size="small" icon="el-icon-caret-right" @click="moveClazz(false)"></el-button>
          </el-button-group>
        </el-col>
        <el-col :span="12" style="float: right;">
          <el-radio-group size="small" v-model="tabPosition" style="margin-bottom: 30px;">
            <el-radio-button label="top">上</el-radio-button>
            <el-radio-button label="bottom">下</el-radio-button>
            <el-radio-button label="left">左</el-radio-button>
            <el-radio-button label="right">右</el-radio-button>
          </el-radio-group>
        </el-col>
      </el-row>
    </div>
    <el-tabs v-model="selectTopic" :tab-position="tabPosition">
      <el-tab-pane v-for="clazz in clazzDatas" :key="clazz.topic" :label="clazz.topic"
                   :name="clazz.topic">
        <el-form :inline="true" class="demo-form-inline">
          <el-form-item label="类名">
            <div class="clazz-props" style="min-width: 140px;">
              <el-input v-if="clazz.editFlag" style="width: 140px;" v-model.trim="clazz.name" size="small"
                        placeholder="类名"></el-input>
              <span v-else>{{clazz.name}}</span>
            </div>
          </el-form-item>
          <el-form-item label="描述">
            <div class="clazz-props" style="min-width: 205px;">
              <el-input v-if="clazz.editFlag" style="width: 205px;" v-model.trim="clazz.description" size="small"
                        placeholder="描述"></el-input>
              <span v-else>{{clazz.description}}</span>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button v-if="!clazz.editFlag" type="primary" size="small" icon="el-icon-edit-outline"
                       @click="onEdit(clazz)"></el-button>
            <el-button v-if="!clazz.editFlag" size="small" icon="el-icon-upload2" @click="onExport(clazz)"></el-button>
            <el-button-group v-if="clazz.editFlag">
              <el-button size="small" type="success" icon="el-icon-check" @click="onSave(clazz)"></el-button>
              <el-button size="small" type="danger" icon="el-icon-close" @click="onCancel(clazz)"></el-button>
              <el-button size="small" type="primary" icon="el-icon-plus" @click="onPlus(clazz, -1)"></el-button>
            </el-button-group>
          </el-form-item>
        </el-form>
        <div style="width:1190px;">
          <el-table :data="clazz.columns" size="small" border stripe>
            <el-table-column label="字段名" width="180" header-align="center">
              <template slot-scope="scope">
               <span v-if="clazz.editFlag">
                 <el-input v-model.trim="scope.row.name" size="mini" placeholder="请输入内容"></el-input>
               </span>
                <span v-else>
                   {{scope.row.name}}
               </span>
              </template>
            </el-table-column>
            <el-table-column label="类型" width="180" header-align="center">
              <template slot-scope="scope">
                <el-cascader size="mini" separator="#" :options="dataTypes" v-model="scope.row.type"
                             :disabled="!clazz.editFlag"></el-cascader>
              </template>
            </el-table-column>
            <el-table-column label="必填" width="80" header-align="center" align="center">
              <template slot-scope="scope">
               <span v-if="clazz.editFlag">
                    <el-select size="mini" v-model="scope.row.required" placeholder="请选择">
                        <el-option v-for="item in requiredOptions" :key="item.value" :label="item.label"
                                   :value="item.value"> </el-option>
                    </el-select>
               </span>
                <span v-else>
                   <span v-if="scope.row.required == '1'">Y</span>
                   <span v-else>N</span>
               </span>
              </template>
            </el-table-column>
            <el-table-column label="默认值" width="180" header-align="center">
              <template slot-scope="scope">
               <span v-if="clazz.editFlag">
                 <el-input v-model.trim="scope.row.default" size="mini" placeholder="请输入内容"></el-input>
               </span>
                <span v-else>
                   {{scope.row.default}}
               </span>
              </template>
            </el-table-column>
            <el-table-column label="描述" width="180" header-align="center">
              <template slot-scope="scope">
               <span v-if="clazz.editFlag">
                 <el-input v-model.trim="scope.row.description" size="mini" placeholder="请输入内容"></el-input>
               </span>
                <span v-else>
                   {{scope.row.description}}
               </span>
              </template>
            </el-table-column>
            <el-table-column label="样例" width="180" header-align="center">
              <template slot-scope="scope">
               <span v-if="clazz.editFlag">
                 <el-input v-model.trim="scope.row.sample" size="mini" placeholder="请输入内容"></el-input>
               </span>
                <span v-else>
                   {{scope.row.sample}}
               </span>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="200">
              <template slot-scope="scope">
                <el-button-group>
                  <el-button :disabled="!clazz.editFlag" size="mini" icon="el-icon-caret-top"
                             @click="onMove(clazz, scope.$index,true)" round></el-button>
                  <el-button :disabled="!clazz.editFlag" size="mini" icon="el-icon-caret-bottom"
                             @click="onMove(clazz, scope.$index,false)" round></el-button>
                  <el-button :disabled="!clazz.editFlag" size="mini" icon="el-icon-minus"
                             @click="onDrop(clazz, scope.$index)"></el-button>
                  <el-button :disabled="!clazz.editFlag" size="mini" icon="el-icon-plus"
                             @click="onPlus(clazz, scope.$index)" round></el-button>
                </el-button-group>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
    </el-tabs>
    <el-dialog title="Markdown" :visible.sync="dialogTableVisible">
      {{content}}
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "model_browse",
    props: {
      libId: ""
    },
    data() {
      return {
        tabPosition: 'top',
        requiredOptions: [
          {
            value: '0',
            label: 'N'
          },
          {
            value: '1',
            label: 'Y',
            disabled: true
          }
        ],
        dataTypes: [],
        editFlag: false,
        clazzDatas: [
          {
            name: 'test',
            topic: 'test',
            description: '',
            editFlag: false,
            columns: [
              {
                name: 'column1',
                type: ['1'],
                sample: 'xxx',
                required: '1',
                description: 'xxxx',
                default: 'xxx'
              },
              {
                name: 'column2',
                type: ['1'],
                sample: 'xxx',
                required: '0',
                description: 'xxxx',
                default: 'xxx'
              }
            ]
          },
          {
            name: 'test2',
            topic: 'test2',
            editFlag: false,
            description: '',
            columns: [
              {
                name: 'column1',
                type: ['1'],
                sample: 'xxx',
                required: '1',
                description: 'xxxx',
                default: 'xxx'
              }
            ]
          }
        ],
        selectTopic: '',
        tabIndex: 1,
        content: '',
        dialogTableVisible: false
      }
    },
    created() {
      this.$http.get(this.ApiUrls.docs.model.types, {params: {libId: this.libId}}).then((response) => {
        // console.log(response);
        this.dataTypes = response.data.data;
      })
    },
    mounted() {
      this.selectTopic = this.clazzDatas[0].topic;
      this.tabIndex = this.clazzDatas.length
    },
    methods: {
      addClazz() {
        let tabTopic = 'Class#' + ++this.tabIndex;
        this.clazzDatas.push({
          topic: tabTopic,
          name: '',
          description: '',
          editFlag: false,
          columns: []
        });
        this.selectTopic = tabTopic
      },
      dropClazz() {
        for (let index in this.clazzDatas) {
          if (this.clazzDatas[index].topic == this.selectTopic) {
            this.clazzDatas.splice(index, 1);
            if (index > 0) {
              this.selectTopic = this.clazzDatas[index - 1].topic
            } else if (this.clazzDatas.length > 0) {
              this.selectTopic = this.clazzDatas[0].topic
            }
            return
          }
        }
      },
      moveClazz(isUp) {
        let index = -1;
        for (let i in this.clazzDatas) {
          if (this.clazzDatas[i].topic == this.selectTopic) {
            index = i;
            break
          }
        }

        if (index != -1) {
          let items = this.avatar.moveItem(this.clazzDatas, index, isUp);
          if (items.length > 0) {
            let topic = items[0].topic;
            items[0].topic = topic + ' ';
            setTimeout(function () {
              items[0].topic = topic
            }, 1)
          }
        }
      },
      onEdit(clazz) {
        clazz.editFlag = true
      },
      onExport(clazz) {
        this.dialogTableVisible = true
      },
      onSave(clazz) {
        if (clazz.name.length == 0) {
          this.$message.error('类名不能为空！');
          return
        }
        let index = 0;
        for (let column of clazz.columns) {
          index++;
          if (column.name.length == 0) {
            this.$message.error('字段名不能为空！' + '（第 ' + index + ' 行）');
            return
          }
        }
        clazz.editFlag = false;
        clazz.topic = clazz.name;
        this.selectTopic = clazz.name
      },
      onCancel(clazz) {
        clazz.editFlag = false
      },
      onDrop(clazz, index) {
        clazz.columns.splice(index, 1)
      },
      onPlus(clazz, index) {
        let newLine = {
          name: '',
          type: ['1'],
          sample: '',
          required: '0',
          description: '',
          default: ''
        };
        if (index == '-1') {
          index = clazz.columns.length
        } else {
          index += 1
        }
        clazz.columns.splice(index, 0, newLine)
      },
      onMove(clazz, index, isUp) {
        this.avatar.moveItem(clazz.columns, index, isUp)
      }
    }
  }
</script>

<style scoped>

</style>
