let localhostDev = true;
let _host = localhostDev ? 'http://192.168.100.107/vue' : 'http://xxx.com/vue';
let ApiUrls = {
  docs: {
    document: {
      browse: _host + '/docs/doc/browse',
      save: _host + '/docs/doc/save',
      search: _host + '/docs/doc/search',
      drop: _host + '/docs/doc/drop',
      move: _host + '/docs/doc/move',
      segTree: _host + "/docs/doc/segTree"
    },
    model: {
      browse: _host + '/docs/model/browse',
      save: _host + '/docs/model/save',
      drop: _host + '/docs/model/drop',
      types: _host + '/docs/model/types',
      classes: _host + '/docs/model/classes'
    },
    segment: {
      save: _host + '/docs/segment/save'
    }
  }
};
export {ApiUrls}
