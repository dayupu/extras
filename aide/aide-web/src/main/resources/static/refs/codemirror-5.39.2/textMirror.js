define(['CodeMirror', 'css!./lib/codemirror.min.css',
        './mode/javascript/javascript', './mode/xml/xml', './mode/htmlmixed/htmlmixed',
        './mode/sql/sql', './addon/edit/matchbrackets', './addon/edit/closebrackets',
        './addon/edit/matchtags', './addon/edit/closetag',
        './addon/selection/active-line', 'css!./theme/dracula.css'],
    function (CodeMirror) {
        var exports = {};
        exports.fromTextArea = function (target, options) {
            var editor = CodeMirror.fromTextArea(target, options);
            // if (typeof options.height == "number") {
            //     var height = options.height;
            //     if (height < 300) {
            //         height = 300;
            //     }
            //     editor.setSize(null, height);
            // }
            return editor;
        };

        return exports;
    }
);