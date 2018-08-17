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
fetchJSONFile(url, function (data) {
    validationProperties = data;
    validate(formName);
});
function validate(formName) {
    $(function () {
        var rules = {};
        var messages = {};
        for (var key in validationProperties[formName]) {
            if (validationProperties[formName].hasOwnProperty(key)) {
                var val = validationProperties[formName][key];
                var rule = {};
                var message = {};

                if (typeof val.required !== 'undefined' && val.required == true) {
                    rule.required = true
                    message.required = "cannot be empty"
                }

                if (typeof val.min !== 'undefined' && Number.isInteger(val.min)) {
                    rule.min = val.min
                    message.min = "cannot be less than " + val.min
                }
                if (typeof val.max !== 'undefined' && Number.isInteger(val.max)) {
                    rule.max = val.max
                    message.max = "cannot be greater than " + val.max
                }
                if (typeof val.minLength !== 'undefined' && Number.isInteger(val.minLength)) {
                    rule.minlength = val.minLength
                    message.minlength = "cannot be shoter than " + val.minLength
                }
                if (typeof val.maxLength !== 'undefined' && Number.isInteger(val.maxLength)) {
                    rule.maxlength = val.maxLength
                    message.maxlength = "cannot be longer than " + val.maxLength
                }
                if (typeof val.email !== 'undefined' && val.email == true) {
                    rule.email = true;
                    message.email = "cannot be non-email format"
                }
                if (typeof val.url !== 'undefined' && val.url == true) {
                    rule.url = true;
                    message.url = "cannot be non-url format"
                }
                if (typeof val.equalTo !== 'undefined') {
                    rule.equalTo = '#' + val.equalTo;
                    message.equalTo = "cannot be different from " + val.equalTo
                }
                if (typeof val.oneOf !== 'undefined') {
                    var values = val.oneOf.slice();
                    jQuery.validator.addMethod("oneOf", function (value, element) {

                        if (values.includes(value)) return true
                        else return false

                    });

                    rule.oneOf = true
                    message.oneOf = "cannot be excluded from " + val.oneOf

                }

                rules[key] = rule;
                messages[key] = message;

            }


        }
        $("form[id='" + formName + "']").validate({
            rules: rules,
            messages: messages,
            submitHandler: function (form) {
                form.submit();
            }
        });
    });
}
