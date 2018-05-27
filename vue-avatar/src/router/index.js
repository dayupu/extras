import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/home'
import Apidoc_typeList from '@/components/apidoc/typeList'
import Apidoc_boxList from '@/components/apidoc/boxList'

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/apidoc/typeList',
      name: 'typeList',
      component: Apidoc_typeList
    },
    {
      path: '/apidoc/boxList',
      name: 'typeList',
      component: Apidoc_boxList
    },
    {
      path: '*',
      redirect: '/home'
    }
  ]
})
