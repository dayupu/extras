<template>
  <el-breadcrumb separator-class="el-icon-arrow-right" class="my-breadcrumb">
    <!--<el-breadcrumb-item><i class="el-icon-location"></i></el-breadcrumb-item>-->
    <!--<el-breadcrumb-item v-for="item of items" style="font-size: 13px">-->
      <!--<template v-if="item.path && item.path != null">-->
        <!--<a v-bind:href="'#' + item.path"> {{item.name}}</a>-->
      <!--</template>-->
      <!--<template v-else>-->
        <!--{{item.name}}-->
      <!--</template>-->
    <!--</el-breadcrumb-item>-->
  </el-breadcrumb>
</template>

<script>
  export default {
    name: "my-breadcrumb",
    props: ["value"],
    data: function () {
      return {items: this.value}
    }
  }
</script>

<style scoped>
  .my-breadcrumb {
    margin-bottom: 10px;
    padding-bottom: 8px;
    border-bottom: 1px solid #e6e6e6;
  }

  .el-icon-location {
    color: #0092dd;
  }
</style>
