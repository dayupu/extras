let localhostDev = true;
let _host = localhostDev ? 'http://127.0.0.1/vue' : 'http://xxx.com/vue';
let ApiUrls = {
  apidoc: {
    typeList: _host + '/apidoc/typeList',
    boxList: _host + '/apidoc/boxList'
  }
};
export {ApiUrls}
