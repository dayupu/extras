define("avatarFmt", ["jquery"], function ($) {
    var exports = {};
    // format json
    exports.formatJson = function (txt, compress) {
        var indentChar = '    ';
        if (/^\s*$/.test(txt)) {
            alert('数据为空,无法格式化! ');
            return;
        }
        try {
            var data = JSON.parse(txt);
        } catch (e) {
            var message = "<span>JSON Error: </span><span>" + e.message + "</span>";
            if (message != null) {
                var regex = /Unexpected (.+) in JSON at position (\d+)/;
                var results = message.match(regex);
                if (results != null) {
                    var index = results[2];
                    var start = index;
                    if (index - 3 >= 0) {
                        start = index - 3;
                    }
                    var line = txt.substr(0, index).split("\n").length;
                    message += "<br/><span>Line: </span>" + line + "";
                    message += "<br/><span>Cause by: </span><span>" + txt.substr(index, 1) + " 在 " + txt.substr(start, 10) + "</span>"
                }
            }
            return message;
        }
        var draw = [], last = false, This = this, line = compress ? '' : '\n', nodeCount = 0, maxDepth = 0;
        var space = compress ? '' : ' ';
        var notify = function (name, value, isLast, indent/*缩进*/, formObj) {
            nodeCount++;
            /*节点计数*/
            for (var i = 0, tab = ''; i < indent; i++) tab += indentChar;
            /* 缩进HTML */
            tab = compress ? '' : tab;
            /*压缩模式忽略缩进*/
            maxDepth = ++indent;
            /*缩进递增并记录*/
            if (value && value.constructor == Array) {/*处理数组*/
                draw.push(tab + (formObj ? ('"' + name + '":') : '') + '[' + line);
                /*缩进'[' 然后换行*/
                for (var i = 0; i < value.length; i++)
                    notify(i, value[i], i == value.length - 1, indent, false);
                draw.push(tab + ']' + (isLast ? line : (',' + line)));
                /*缩进']'换行,若非尾元素则添加逗号*/
            } else if (value && typeof value == 'object') {/*处理对象*/
                draw.push(tab + (formObj ? ('"' + name + '":') : '') + '{' + line);
                /*缩进'{' 然后换行*/
                var len = 0, i = 0;
                for (var key in value) len++;
                for (var key in value) notify(key, value[key], ++i == len, indent, true);
                draw.push(tab + '}' + (isLast ? line : (',' + line)));
                /*缩进'}'换行,若非尾元素则添加逗号*/
            } else {
                if (typeof value == 'string') value = '"' + value + '"';
                draw.push(tab + (formObj ? ('"' + name + '":' + space) : '') + value + (isLast ? '' : ',') + line);
            }
        };
        var isLast = true, indent = 0;
        notify('', data, isLast, indent, false);
        return draw.join('');
    };
    // format xml
    exports.formatXml = function formatXml(text) {
        var getPrefix = function (prefixIndex) {
            var span = '    ';
            var output = [];
            for (var i = 0; i < prefixIndex; ++i) {
                output.push(span);
            }
            return output.join('');
        };
        if (/^\s*$/.test(text)) {
            alert('数据为空,无法格式化! ');
            return;
        }
        //去掉多余的空格
        text = '\n' + text.replace(/(<\w+)(\s.*?>)/g, function ($0, name, props) {
            return name + ' ' + props.replace(/\s+(\w+=)/g, " $1");
        }).replace(/>\s*?</g, ">\n<");
        //把注释编码
        text = text.replace(/\n/g, '\r').replace(/<!--(.+?)-->/g, function ($0, text) {
            var ret = '<!--' + escape(text) + '-->';
            return ret;
        }).replace(/\r/g, '\n');
        //调整格式
        var rgx = /\n(<(([^\?]).+?)(?:\s|\s*?>|\s*?(\/)>)(?:.*?(?:(?:(\/)>)|(?:<(\/)\2>)))?)/mg;
        var nodeStack = [];
        var output = text.replace(rgx, function ($0, all, name, isBegin, isCloseFull1, isCloseFull2, isFull1, isFull2) {
            var isClosed = (isCloseFull1 == '/') || (isCloseFull2 == '/') || (isFull1 == '/') || (isFull2 == '/');
            //alert([all,isClosed].join('='));
            var prefix = '';
            if (isBegin == '!') {
                prefix = getPrefix(nodeStack.length);
            }
            else {
                if (isBegin != '/') {
                    prefix = getPrefix(nodeStack.length);
                    if (!isClosed) {
                        nodeStack.push(name);
                    }
                }
                else {
                    nodeStack.pop();
                    prefix = getPrefix(nodeStack.length);
                }
            }
            var ret = '\n' + prefix + all;
            return ret;
        });
        var prefixSpace = -1;
        var outputText = output.substring(1);
        //把注释还原并解码，调格式
        outputText = outputText.replace(/\n/g, '\r').replace(/(\s*)<!--(.+?)-->/g, function ($0, prefix, text) {
            if (prefix.charAt(0) == '\r')
                prefix = prefix.substring(1);
            text = unescape(text).replace(/\r/g, '\n');
            var ret = '\n' + prefix + '<!--' + text.replace(/^\s*/mg, prefix) + '-->';
            return ret;
        });
        return outputText.replace(/\s+$/g, '').replace(/\r/g, '\r\n');
    };
    return exports;
});