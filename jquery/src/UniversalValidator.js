var validationProperties;
function fetchJSONFile(path, callback) {
    var httpRequest = new XMLHttpRequest();
    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState === 4) {
            if (httpRequest.status === 200) {
                var data = JSON.parse(httpRequest.responseText);
                if (callback) callback(data);
            }
        }
    };
    httpRequest.open('GET', path);
    httpRequest.send();
}

// this requests the file and executes a callback with the parsed result once
//   it is available
fetchJSONFile('https://cors.io/?http://nilankamanoj.tk/universal-validator/validation.json', function (data) {
    // do something with your data
    console.log(data);
    validationProperties = data;
});

// jQuery.validator.addMethod("checkDrop", function (value, element) {

//     if (value != "NO" && value != "empty" && value != "0") {
//         return true;
//     }
//     else return false;
// });

function validate(formName) {
    var rules = {};
    for (var key in validationProperties) {
        if (validationProperties.hasOwnProperty(key)) {
            var val = obj[key];
            
        }
    }
    $(function () {

        $("form[id='" + formName + "']").validate({
            rules: {

            },

            messages: {
            },

            submitHandler: function (form) {
                form.submit();
            }
        });
    });
}