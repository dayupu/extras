import Vue from 'vue'
import Router from 'vue-router'
import {Tools, Docs} from "@/config/menus";

Vue.use(Router);
Vue.prototype.moduleMenus = {tools: Tools, docs: Docs};
export default new Router({
  routes: [
    {
      path: '/home',
      name: 'home',
      component: require('@/views/home.vue').default,
    },
    {
      path: '/docs',
      name: '文档库',
      component: require('@/views/docs/zhome.vue').default,
      children: [{path: "/", name: "docs", component: require('@/views/docs/zdocs.vue').default}, ...Docs.subMenus]
    },
    {
      path: '/tools',
      name: '工具箱',
      component: require('@/views/tools/zhome.vue').default,
      children: [{path: "/", component: require('@/views/tools/zdefault.vue').default}, ...Tools.subMenus]
    },
    {
      path: '*',
      redirect: '/home'
    }
  ]
})
