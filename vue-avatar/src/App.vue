<template>
  <div id="app">
    <el-container style="height: 100%" direction="vertical">
      <el-header class="app-header" style="height: 50px; line-height: 50px;background-color: #336699;">
        <div class="app-header-title">
          <span style="color: white;">
            <i class="fa fa-clone"></i>&nbsp;&nbsp;提莫队长
          </span>
          <span class="app-header-title-items">
              <router-link to="/docs">
                <el-button size="small" type="primary" icon="el-icon-document" plain>我的文档</el-button>
              </router-link>
              <router-link to="/tools">
                <el-button size="small" type="primary" icon="el-icon-goods" plain>工具箱</el-button>
              </router-link>
              <router-link to="/share">
                <el-button size="small" type="primary" icon="el-icon-share" plain>分享</el-button>
              </router-link>
          </span>
        </div>
      </el-header>
      <el-main class="app-wrapper" style="overflow-x: hidden;">
        <router-view></router-view>
      </el-main>
      <el-footer class="app-footer" style="height: 10px; background-color: #eee;">
      </el-footer>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>
