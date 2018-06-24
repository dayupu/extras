<template>
  <div>
    xxxx
    <el-button @click="changeTitle">save</el-button>
  </div>
</template>

<script>
  export default {
    name: "doc_file_model",
    methods: {
      changeTitle() {
        this.$bus.$emit("changeTitle", "test");
      }
    }
  }
</script>

<style scoped>

</style>
