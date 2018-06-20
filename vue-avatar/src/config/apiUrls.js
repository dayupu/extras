let localhostDev = true;
let _host = localhostDev ? 'http://192.168.100.107/vue' : 'http://xxx.com/vue';
let ApiUrls = {
  docs: {
    library: {
      browse: _host + '/docs/lib/browse',
      create: _host + '/docs/lib/create',
      search: _host + '/docs/lib/search'
    },
    document:{
      browse: _host + '/docs/doc/browse',
      html: _host + '/docs/doc/html',
      save: _host + '/docs/doc/save',
      search: _host + '/docs/doc/search',
      drop: _host + '/docs/doc/drop',
      move: _host + '/docs/doc/move',
    },
    model: {
      types: _host + '/docs/model/types'
    }
  }
};
export {ApiUrls}
