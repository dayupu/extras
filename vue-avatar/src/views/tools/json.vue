<template>
  <div>
    <my-breadcrumb v-model="locations"></my-breadcrumb>
  </div>
</template>

<script>
export default {
  data() {
    return { locations: [{ name: "工具箱" }, { name: "Json格式化" }] };
  }
};
</script>
