<template>
  <div id="app" class="wrapper sidebar-mini fixed  pace-done">
    <!-- Navbar-->
    <header class="main-header hidden-print"><a class="logo" href="#">Avatar</a>
      <nav class="navbar navbar-static-top">
        <a class="sidebar-toggle" href="#" data-toggle="offcanvas"></a>
        <!-- Navbar Right Menu-->
        <div class="navbar-custom-menu">
          <ul class="top-nav">

          </ul>
        </div>
      </nav>
    </header>
    <aside class="main-sidebar hidden-print">
      <div class="slimScrollDiv" style="position: relative; overflow: visible; width: auto; height: 940px;">
        <section class="sidebar" style="overflow: visible; width: auto; height: 940px;">
          <div class="top-panel">
            <h3 style="color: white">welcome</h3>
          </div>
          <!-- Sidebar Menu-->
          <ul class="sidebar-menu">
            <li class="treeview"><a href="#">
              <i class="fa fa-laptop"></i><span>API 设计师</span><i class="fa fa-angle-right"></i></a>
              <ul class="treeview-menu" style="display: none;">
                <li><a href="#/apidoc/typeList"><i class="fa fa-tag"></i>基础类型</a></li>
                <li><a href="#/apidoc/boxList"><i class="fa fa-tag"></i>API文档库</a></li>
              </ul>
            </li>
          </ul>
        </section>
      </div>
    </aside>
    <div class="content-wrapper">
      <div class="row">
        <div class="col-md-12">
          <div class="card">
            <div class="bs-element-section">
              <router-view></router-view>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>
