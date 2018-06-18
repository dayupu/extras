// api design menu

const Docs = {
  basePath: "/docs/",
  subMenus: [
    {
      name: "libraryCreate",
      path: "create",
      component: require("@/views/docs/lib_create.vue").default
    },
    {
      name: "libraryBrowse",
      path: ":id",
      component: require("@/views/docs/lib_browse.vue").default
    },
    // {
    //   name: "documentBrowse",
    //   path: ":libId/:id",
    //   component: require("@/views/docs/doc_browse.vue").default
    // },
    {
      name: "documentEdit",
      path: ":libId/:id/edit",
      component: require("@/views/docs/doc_edit.vue").default
    }
  ]
};

const Tools = {
  basePath: "/tools/",
  subMenus: [
    {
      name: "Json格式化",
      path: "json",
      component: require("@/views/tools/json.vue").default
    }
  ]
};

export {Tools, Docs}
