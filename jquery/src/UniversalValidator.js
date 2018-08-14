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
//
// this requests the file and executes a callback with the parsed result once
//   it is available
fetchJSONFile('https://cors.io/?http://nilankamanoj.tk/universal-validator/validation.json', function (data) {
    // do something with your data
    console.log(data);
    validationProperties = data;
    validate( "formRegister");
});

// jQuery.validator.addMethod("checkDrop", function (value, element) {

//     if (value != "NO" && value != "empty" && value != "0") {
//         return true;
//     }
//     else return false;
// });

function validate(formName) {
    $(function () {
        var rules = {};
        for (var key in validationProperties[formName]) {
            if (validationProperties[formName].hasOwnProperty(key)) {
                var val = validationProperties[formName][key];
                var rule = {}

                if (typeof val.required !== 'undefined' && val.required == true) {
                    rule.required = true
                }

                if (typeof val.min !== 'undefined' && Number.isInteger(val.min)) {
                    rule.min = val.min
                }
                if (typeof val.max !== 'undefined' && Number.isInteger(val.max)) {
                    rule.max = val.max
                }
                if (typeof val.minLength !== 'undefined' && Number.isInteger(val.minLength)) {
                    rule.minlength = val.minLength
                }
                if (typeof val.maxLength !== 'undefined' && Number.isInteger(val.maxLength)) {
                    rule.maxlength = val.maxLength
                }
                if (typeof val.email !== 'undefined' && val.email == true) {
                    rule.email = true;
                }
                if (typeof val.url !== 'undefined' && val.url == true) {
                    rule.url = true;
                }
                if (typeof val.eualTo !== 'undefined') {
                    rule.eualTo = '#' + val.eualTo;
                }
                if (typeof val.oneOf !== 'undefined') {

                    jQuery.validator.addMethod("oneOf", function (value, element) {
                        if (val.oneOf.includes(value)) return true
                        else return false

                    });

                    rule.oneOf = true

                }

                rules[key] = rule;

            }


        }
        console.log(rules);

        $("form[id='" + formName + "']").validate({
            rules: rules,
            submitHandler: function (form) {
                form.submit();
            }
        });
    });
}
