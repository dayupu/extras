define(["jquery", "NProgress", "bootstrap", "serializeJson"], function ($, NProgress) {
    var _appUrl = function (url) {
        return "/app/" + url;
    };
    $.extend({
        xyzUrl: function (url) {
            return _appUrl(url);
        },
        xyzGet: function (url, callback) {
            $.get(_appUrl(url), callback)
        },
        xyzPost: function (url, data, callback) {
            $.ajax({
                url: _appUrl(url),
                type: "POST",
                contentType: "application/json",
                data: data,
                beforeSend: function (xhr) {
                    NProgress.start();
                },
                success: function (data) {
                    callback(data);
                },
                complete: function (xhr) {
                    NProgress.done();
                }
            })
        }
    });

    $.fn.extend({
        xyzLoadPage: function (url, changeUrl) {
            url = _appUrl(url);
            var target = this;
            $.ajax({
                url: url,
                headers: {"ajax-page": "true"},
                beforeSend: function (xhr) {
                    NProgress.start();
                },
                success: function (data) {
                    $(target).html(data);
                    if (typeof changeUrl == "undefined" || changeUrl) {
                        window.history.pushState(null, null, url);
                    }
                },
                complete: function (xhr) {
                    NProgress.done();
                }
            });
        },
        xyzAjaxSubmit: function (url, options, callback) {
            var setting = {
                url: _appUrl(url),
                type: 'POST',
                beforeSend: function (xhr) {
                    NProgress.start();
                },
                success: function (data) {
                    callback(data)
                },
                complete: function (xhr) {
                    NProgress.done();
                }
            };
            if (typeof options == "object") {
                $.extend(setting, options);
            }
            $(this).ajaxSubmit(setting);
        }
    });
    return $;
});