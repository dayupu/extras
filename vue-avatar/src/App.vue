<template>
  <div id="app">
    <el-container style="height: 100%" direction="vertical">
      <el-header class="app-header" style="height: 50px; line-height: 50px;">
        <div class="app-header-title">
          <span>
            <i class="fa fa-cube"></i>&nbsp;&nbsp;Avatar
          </span>
          <span class="app-header-title-items">
              <router-link to="/apiDesign">
                <el-button size="small" icon="el-icon-edit" round>API设计</el-button>
              </router-link>
              <router-link to="/share">
                <el-button size="small" icon="el-icon-share" round>分享</el-button>
              </router-link>
          </span>
        </div>
      </el-header>
      <el-main class="app-wrapper" style="overflow-x: hidden;">
        <router-view></router-view>
      </el-main>
      <el-footer class="app-footer" style="height: 40px; line-height: 40px;">
        <span>
           <i class="fa fa-cube"></i>&nbsp;&nbsp;Avatar
        </span>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>
