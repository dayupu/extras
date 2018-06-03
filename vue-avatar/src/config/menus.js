// api design menu

const ApiDesignMenu =
  {
    basePath: "/apiDesign/",
    subMenus: [
      {
        name: "文档箱",
        path: "box",
        component: require("@/views/apiDesign/box.vue").default
      },
      {
        name: "数据模型",
        path: "model",
        component: require("@/views/apiDesign/model.vue").default
      }
    ]
  };

export {ApiDesignMenu}
