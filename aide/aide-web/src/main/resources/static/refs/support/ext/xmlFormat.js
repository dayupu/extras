(function ($) {
    function createShiftArr(step) {
        var space = '    ';
        if (isNaN(parseInt(step))) {
            space = step;
        } else {
            space = new Array(step + 1).join(' ');
        }
        var shift = ['\n'];
        for (var ix = 0; ix < 100; ix++) {
            shift.push(shift[ix] + space);
        }
        return shift;
    };

    function isSubquery(str, parenthesisLevel) {
        return parenthesisLevel - (str.replace(/\(/g, '').length - str.replace(/\)/g, '').length);
    };

    function split_sql(str, tab) {
        return str.replace(/\s{1,}/g, " ").replace(/ AND /ig, "~::~" + tab + tab + "AND ").replace(/ BETWEEN /ig, "~::~" + tab + "BETWEEN ").replace(/ CASE /ig, "~::~" + tab + "CASE ").replace(/ ELSE /ig, "~::~" + tab + "ELSE ").replace(/ END /ig, "~::~" + tab + "END ").replace(/ FROM /ig, "~::~FROM ").replace(/ GROUP\s{1,}BY/ig, "~::~GROUP BY ").replace(/ HAVING /ig, "~::~HAVING ").replace(/ IN /ig, " IN ").replace(/ JOIN /ig, "~::~JOIN ").replace(/ CROSS~::~{1,}JOIN /ig, "~::~CROSS JOIN ").replace(/ INNER~::~{1,}JOIN /ig, "~::~INNER JOIN ").replace(/ LEFT~::~{1,}JOIN /ig, "~::~LEFT JOIN ").replace(/ RIGHT~::~{1,}JOIN /ig, "~::~RIGHT JOIN ").replace(/ ON /ig, "~::~" + tab + "ON ").replace(/ OR /ig, "~::~" + tab + tab + "OR ").replace(/ ORDER\s{1,}BY/ig, "~::~ORDER BY ").replace(/ OVER /ig, "~::~" + tab + "OVER ").replace(/\(\s{0,}SELECT /ig, "~::~(SELECT ").replace(/\)\s{0,}SELECT /ig, ")~::~SELECT ").replace(/ THEN /ig, " THEN~::~" + tab + "").replace(/ UNION /ig, "~::~UNION~::~").replace(/ USING /ig, "~::~USING ").replace(/ WHEN /ig, "~::~" + tab + "WHEN ").replace(/ WHERE /ig, "~::~WHERE ").replace(/ WITH /ig, "~::~WITH ").replace(/ ALL /ig, " ALL ").replace(/ AS /ig, " AS ").replace(/ ASC /ig, " ASC ").replace(/ DESC /ig, " DESC ").replace(/ DISTINCT /ig, " DISTINCT ").replace(/ EXISTS /ig, " EXISTS ").replace(/ NOT /ig, " NOT ").replace(/ NULL /ig, " NULL ").replace(/ LIKE /ig, " LIKE ").replace(/\s{0,}SELECT /ig, "SELECT ").replace(/\s{0,}UPDATE /ig, "UPDATE ").replace(/ SET /ig, " SET ").replace(/~::~{1,}/g, "~::~").split('~::~');
    };var Formatter = function (options) {
        this.init(options);
        var methodName = this.options.method;
        if (!$.isFunction(this[methodName])) {
            $.error("'" + methodName + "' is not a Formatter method.");
        }
        ;this.format = function (text) {
            return this[this.options.method].call(this, text);
        };
    };
    Formatter.prototype = {
        options: {}, init: function (options) {
            this.options = $.extend({}, $.fn.format.defaults, options);
            this.step = this.options.step;
            this.preserveComments = this.options.preserveComments;
            this.shift = createShiftArr(this.step);
        }, xml: function (text) {
            var ar = text.replace(/>\s{0,}</g, "><").replace(/</g, "~::~<").replace(/\s*xmlns\:/g, "~::~xmlns:").replace(/\s*xmlns\=/g, "~::~xmlns=").split('~::~'),
                len = ar.length, inComment = false, deep = 0, str = '', ix = 0;
            for (ix = 0; ix < len; ix++) {
                if (ar[ix].search(/<!/) > -1) {
                    str += this.shift[deep] + ar[ix];
                    inComment = true;
                    if (ar[ix].search(/-->/) > -1 || ar[ix].search(/\]>/) > -1 || ar[ix].search(/!DOCTYPE/) > -1) {
                        inComment = false;
                    }
                } else if (ar[ix].search(/-->/) > -1 || ar[ix].search(/\]>/) > -1) {
                    str += ar[ix];
                    inComment = false;
                } else if (/^<\w/.exec(ar[ix - 1]) && /^<\/\w/.exec(ar[ix]) && /^<[\w:\-\.\,]+/.exec(ar[ix - 1]) == /^<\/[\w:\-\.\,]+/.exec(ar[ix])[0].replace('/', '')) {
                    str += ar[ix];
                    if (!inComment) deep--;
                } else if (ar[ix].search(/<\w/) > -1 && ar[ix].search(/<\//) == -1 && ar[ix].search(/\/>/) == -1) {
                    str = !inComment ? str += this.shift[deep++] + ar[ix] : str += ar[ix];
                } else if (ar[ix].search(/<\w/) > -1 && ar[ix].search(/<\//) > -1) {
                    str = !inComment ? str += this.shift[deep] + ar[ix] : str += ar[ix];
                } else if (ar[ix].search(/<\//) > -1) {
                    str = !inComment ? str += this.shift[--deep] + ar[ix] : str += ar[ix];
                } else if (ar[ix].search(/\/>/) > -1) {
                    str = !inComment ? str += this.shift[deep] + ar[ix] : str += ar[ix];
                } else if (ar[ix].search(/<\?/) > -1) {
                    str += this.shift[deep] + ar[ix];
                } else if (ar[ix].search(/xmlns\:/) > -1 || ar[ix].search(/xmlns\=/) > -1) {
                    str += this.shift[deep] + ar[ix];
                }
                else {
                    str += ar[ix];
                }
            }
            return (str[0] == '\n') ? str.slice(1) : str;
        }, sql: function (text) {
            var ar_by_quote = text.replace(/\s{1,}/g, " ").replace(/\'/ig, "~::~\'").split('~::~'),
                len = ar_by_quote.length, ar = [], deep = 0, tab = this.step, parenthesisLevel = 0, str = '', ix = 0;
            for (ix = 0; ix < len; ix++) {
                if (ix % 2) {
                    ar = ar.concat(ar_by_quote[ix]);
                } else {
                    ar = ar.concat(split_sql(ar_by_quote[ix], tab));
                }
            }
            len = ar.length;
            for (ix = 0; ix < len; ix++) {
                parenthesisLevel = isSubquery(ar[ix], parenthesisLevel);
                if (/\s{0,}\s{0,}SELECT\s{0,}/.exec(ar[ix])) {
                    ar[ix] = ar[ix].replace(/\,/g, ",\n" + tab + tab + "");
                }
                if (/\s{0,}\s{0,}SET\s{0,}/.exec(ar[ix])) {
                    ar[ix] = ar[ix].replace(/\,/g, ",\n" + tab + tab + "");
                }
                if (/\s{0,}\(\s{0,}SELECT\s{0,}/.exec(ar[ix])) {
                    deep++;
                    str += this.shift[deep] + ar[ix];
                } else if (/\'/.exec(ar[ix])) {
                    if (parenthesisLevel < 1 && deep) {
                        deep--;
                    }
                    str += ar[ix];
                }
                else {
                    str += this.shift[deep] + ar[ix];
                    if (parenthesisLevel < 1 && deep) {
                        deep--;
                    }
                }
            }
            str = str.replace(/^\n{1,}/, '').replace(/\n{1,}/g, "\n");
            return str;
        },
    };
    $.fn.format = function (options) {
        var fmt = new Formatter(options);
        return this.each(function () {
            var node = $(this);
            var text = node.val();
            text = fmt.format(text);
            node.val(text);
        });
    };
    $.format = function (text, options) {
        var fmt = new Formatter(options);
        return fmt.format(text);
    };
    $.fn.format.defaults = {method: 'xml', step: '    ', preserveComments: false};
})(jQuery);