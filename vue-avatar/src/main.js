// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
// easy table for vue

import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import router from './router'
import VueResource from 'vue-resource';
// element ui base on Vue2
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// easy table for Vue
import 'vue-easytable/libs/themes-base/index.css'
import {VTable, VPagination} from 'vue-easytable'
// custom config
import {ApiUrls} from './config/apiUrls';
import MyBreadcrumb from './tags/my-breadcrumb.vue';



// Global application
Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.use(Vuex);
Vue.use(VueResource);
// global parameters
Vue.prototype.ApiUrls = ApiUrls;
// Register to global
Vue.component(VTable.name, VTable);
Vue.component(VPagination.name, VPagination);
Vue.component(MyBreadcrumb.name, MyBreadcrumb);

const store = new Vuex.Store({
  state: {
    count: 0
  },
  mutations: {
    increment(state) {
      state.count++
    }
  }
});


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router, store, VueResource,
  components: {App},
  template: '<App/>',
  render: h => h(App)
});
