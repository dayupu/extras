<template>
  <div>
    <my-breadcrumb v-model="locations"></my-breadcrumb>
  </div>
</template>

<script>

  let locations = {};
  export default {
    data() {
      return {locations: [{name: "API设计"}, {name: "文档箱"}]}
    }

  }
</script>
