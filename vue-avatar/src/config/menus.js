// api design menu

const Docs = {
  basePath: "/docs/",
  subMenus: [
    {
      name: "docEdit",
      path: ":docId",
      component: require("@/views/docs/doc_edit.vue").default,
      children: [
        {
          name: "segmentEdit",
          path: ":segId/edit",
          component: require("@/views/docs/segment_edit.vue").default
        },
        {
          name: "segmentBrowse",
          path: ":segId/browse",
          component: require("@/views/docs/segment_browse.vue").default
        }
      ]
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
