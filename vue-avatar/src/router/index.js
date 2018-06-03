import Vue from 'vue'
import Router from 'vue-router'
import {ApiDesignMenu} from "@/config/menus";
import Home from '@/views/home.vue'
import ApiDesignHome from '@/views/apiDesign/zhome.vue'
import ApiDesignDefault from '@/views/apiDesign/zdefault.vue'

Vue.use(Router);
Vue.prototype.mainMenus = {apiDesign: ApiDesignMenu};

export default new Router({
  routes: [
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/apiDesign',
      name: 'api设计',
      component: ApiDesignHome,
      children: [{path: "/", component: ApiDesignDefault}, ...ApiDesignMenu.subMenus]
    },
    {
      path: '*',
      redirect: '/home'
    }
  ]
})
