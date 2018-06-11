<template>
  <div>
    <el-container>
      <el-aside style="max-width: 200px;">
        <el-menu :default-active="menuSelected" :default-openeds="['top']" class="el-menu-vertical-demo" unique-opened>
          <el-submenu index="top">
            <template slot="title">
              <i class="el-icon-menu" style="color: #0092dd"></i>
              <span slot="title">工具箱</span>
            </template>
            <router-link v-for="item of items" :to="basePath + item.path" style="text-decoration: none;">
              <el-menu-item v-bind:index="basePath + item.path" style="height: 40px;line-height: 40px;">{{item.name}}
              </el-menu-item>
            </router-link>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>

  </div>
</template>

<script>

  export default {
    created() {
      this.defaultSelected();
    },
    data() {
      return {
        menuSelected: "1",
        basePath: this.moduleMenus.tools.basePath,
        items: this.moduleMenus.tools.subMenus
      };
    },
    methods: {
      defaultSelected() {
        let path = window.location.href.split("#")[1];
        this.menuSelected = path;
      }
    },
    watch: {
      '$store.state.navMenuIndex': 'getNavType'
    }
  }
</script>
