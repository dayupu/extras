define("avatar", ["jquery", "amaze"], function ($, amaze) {
    var exports = {};
    /* 项目路径 */
    exports.appPath = "/app/";
    exports.appUrl = function (url) {
        return exports.appPath + url;
    };
    exports.pagePath = function () {
        return window.location.pathname;
    };
    /* Ajax加载页面 */
    exports.loadPage = function (obj, url, title) {
        url = exports.appUrl(url);
        $.ajax({
            url: url,
            headers: {"ajax-page": "true"},
            beforeSend: function (xhr) {
                amaze.progress.start();
            },
            success: function (data) {
                $(obj).html(data);
                if (typeof title == "string") {
                    document.title = title;
                }
                window.history.pushState(null, null, url);
            },
            complete: function (xhr) {
                amaze.progress.done();
            }
        });
    };

    /* JQuery ajax */
    var ajaxRequest = function (url, settings) {
        var options = {
            url: exports.appUrl(url),
            type: "get",
            async: true,
            contentType: "application/json",
            beforeSend: function (xhr) {
                amaze.progress.start();
            },
            complete: function (xhr, ts) {
                amaze.progress.done();
            },
            error: function (xhr, textStatus, thrownError) {
                console.log(thrownError);
            }
        };
        $.extend(options, settings);
        $.ajax(options);
    };
    exports.get = function (url, callback) {
        ajaxRequest(url, {type: "get", success: callback});
    };
    exports.post = function (url, data, callback) {
        ajaxRequest(url, {type: "post", data: JSON.stringify(data), success: callback});
    };
    // load json file
    exports.getJson = function (url, callback) {
        $.getJSON(exports.appUrl(url), callback);
    };
    return exports;
});