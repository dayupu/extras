// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
// easy table for vue

import Vue from 'vue'
import App from './App'
import router from './router'
import resource from './resource'
// import table and pagination comp
import 'vue-easytable/libs/themes-base/index.css'
import {VTable, VPagination} from 'vue-easytable'
// custom tags
import {ApiUrls} from './config/api_url';

// global prototype
Vue.prototype.ApiUrls = ApiUrls
Vue.config.productionTip = false
// Register to global
Vue.component(VTable.name, VTable)
Vue.component(VPagination.name, VPagination)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router, resource,
  components: {App},
  template: '<App/>'
})
