let localhostDev = true;
let _host = localhostDev ? 'http://127.0.0.1/vue' : 'http://xxx.com/vue';
let ApiUrls = {
  docs: {
    library: {
      browse: _host + '/docs/lib/browse',
      detail: _host + '/docs/lib/detail',
      create: _host + '/docs/lib/create',
      list: _host + '/docs/lib/list'
    },
    document:{
      browse: _host + '/docs/doc/browse',
      html: _host + '/docs/doc/html',
      save: _host + '/docs/doc/save',
    }
  }
};
export {ApiUrls}
