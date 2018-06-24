// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
// easy table for vue

import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import router from './router'
import VueResource from 'vue-resource';
// element ui base on Vue2
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// mavon editor
import mavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';
import VueMarkdown from 'vue-markdown';
import hljs from 'highlight.js'
import 'highlight.js/styles/github.css'
// content menu
import VueBus from 'vue-bus';
// custom config
import {ApiUrls} from './config/apiUrls';
import Avatar from './config/avatar';
import Base from './config/base';
import MyBreadcrumb from './tags/my-breadcrumb.vue';
import MyTableSearch from './tags/my-table-search.vue';

// Global application
Vue.config.productionTip = false;
Vue.use(Vuex);
Vue.use(ElementUI);
Vue.use(mavonEditor);
Vue.use(VueResource);
Vue.use(Base);
Vue.use(VueBus);

Vue.directive('highlight', (el) => {
  var markdownStyle = function (el) {
    let blocks = el.querySelectorAll('pre code');
    blocks.forEach((block) => {
      hljs.highlightBlock(block)
    });

    let tables = el.querySelectorAll('table');
    tables.forEach((table) => {
      table.style.cssText = "border-collapse:separate; border-spacing:1px 1px;background-color:#E0E0E0";
      let tds = table.getElementsByTagName("td");
      for (var i = 0; i < tds.length; i++) {
        tds[i].style.cssText = "background-color:white;padding:5px;";
      }
      let ths = table.getElementsByTagName("th");
      for (var i = 0; i < ths.length; i++) {
        ths[i].style.cssText = "background-color:#f6f8fa; padding:5px;";
      }
    });
  };
  setTimeout(function () {
    markdownStyle(el)
  }, 300);
});
// global parameters
Vue.prototype.ApiUrls = ApiUrls;
Vue.prototype.avatar = Avatar;
// Register to global
Vue.component(MyBreadcrumb.name, MyBreadcrumb);
Vue.component(MyTableSearch.name, MyTableSearch);
Vue.component("vue-markdown", VueMarkdown);

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
