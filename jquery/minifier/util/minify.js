var UglifyJS = require("uglify-js");

exports.minify = function (code) {
    var result = UglifyJS.minify(code);
    return result.code;
}