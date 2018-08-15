var util = require( './util/minify');
fs = require('fs');
var code = "";
fs.readFile('../src/UniversalValidator.js', 'utf8', function (err,data) {
  if (err) {
    return console.log(err);
  }
  
  
  fs.writeFile('../test/universal.validator.min.js', util.minify(data), function(err) {
    if(err) {
        return console.log(err);
    }

    console.log("complete");
}); 
  
});


