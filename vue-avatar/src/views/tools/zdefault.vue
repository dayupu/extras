<template>
  <div>
    <my-breadcrumb v-model="locations"></my-breadcrumb>
    Hello world
  </div>
</template>

<script>
  export default {
    name: "default",
    data() {
      return {locations: [{name: "API设计"}]}
    }
  }
</script>

<style scoped>

</style>
