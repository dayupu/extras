<template>
  <div class="my-menu">
    <div class="title">
      <span class="glyphicon glyphicon-th-large logo"></span>{{title}}
    </div>
    <ul>
      <li v-for="item in menuItems">
        {{ item.name }}
      </li>
    </ul>
  </div>
</template>

<script>
  export default {
    props: {menus: Array, title: String},
    name: "myMenu",
    data: function () {
      return {menuItems: this.menus}
    }
  }
</script>

<style scoped>
  .my-menu > .title {
    padding-top: 15px;
    height: 50px;
    background-color: #354457;
    font-size: 15px;
    color: #fff;
    vertical-align: bottom;
  }

  .my-menu > .title > .logo {
    margin: 0 10px 0 20px;
  }
</style>
