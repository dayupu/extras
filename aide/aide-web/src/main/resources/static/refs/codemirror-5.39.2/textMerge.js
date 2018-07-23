define(['CodeMirror', './addon/merge/merge', 'css!./lib/codemirror.min.css',
        'css!./addon/merge/merge.min.css', './mode/javascript/javascript', './mode/xml/xml',
        './mode/htmlmixed/htmlmixed', './mode/sql/sql','css!./theme/idea.css'],
    function (CodeMirror) {
        var exports = {};
        exports.mergeView = function (target, options) {
            var editor = CodeMirror.MergeView(target, options);
            if (typeof options.height == "number") {
                var height = options.height;
                if (height < 300) {
                    height = 300;
                }
                if (editor.leftOriginal())
                    editor.leftOriginal().setSize(null, height);
                if (editor.rightOriginal())
                    editor.rightOriginal().setSize(null, height);
                editor.editor().setSize(null, height);
                editor.wrap.style.height = (height + 2) + "px";
            }
            return editor;
        };

        exports.value = function (editor) {
            var diff = editor.right.diff;
            var leftArr = [], rightArr = [];
            var len = diff.length;
            var line, type, content;
            for (var i = 0; i < len; i++) {
                line = diff[i];
                type = line[0];
                content = line[1];
                if (type == 0) {//EQUAL
                    leftArr.push(content);
                    rightArr.push(content);
                } else if (type == -1) {//DELETE
                    rightArr.push(content);
                } else if (type == 1) {//INSERT
                    leftArr.push(content);
                }
            }
            return {left: leftArr.join(''), right: rightArr.join('')}
        };
        return exports;
    }
);