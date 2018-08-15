import validator from 'validator';


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

fetchJSONFile('https://cors.io/?http://nilankamanoj.tk/universal-validator/validation.json', function (data) {
    // do something with your data
    console.log(data);
    validationProperties = data;
});


export function validate(refField,object) {
    console.log(refField);
    console.log(object);
    console.log("Backend validation starts");

    $(function () {
        var rules = {};
        for (var key in validationProperties[refField]) {
            if (validationProperties[refField].hasOwnProperty(key)) {
                var val = validationProperties[refField][key];
                var rule = {};
                var fieldError = false;

                if (!fieldError && typeof val.required !== 'undefined' && val.required == true && !required(object.refField)) {
                    fieldError = true;
                    rule.required = 'cannot be empty';
                }
                if (!fieldError && typeof val.min !== 'undefined' && !min(object.refField,val.min)) {
                    fieldError = true;
                    rule.min = 'cannot be less than'+ val.min;
                }
                if (!fieldError && typeof val.max !== 'undefined' && !max(object.refField,val.max)) {
                    fieldError = true;
                    rule.max = 'cannot be grater than'+ val.max;
                }
                if (!fieldError && typeof val.minLength !== 'undefined' && !minLength(object.refField,val.minLength)) {
                    fieldError = true;
                    rule.minLength = 'cannot be shorter than'+ val.minLength;
                }
                if (!fieldError && typeof val.maxLength !== 'undefined' && !maxLength(object.refField,val.maxLength)) {
                    fieldError = true;
                    rule.maxLength = 'cannot be longer than'+ val.maxLength;
                }
                if (!fieldError && typeof val.email !== 'undefined' && !email(object.refField)) {
                    fieldError = true;
                    rule.email = 'cannot be non-email format';
                }
                if (!fieldError && typeof val.url !== 'undefined' && !url(object.refField)) {
                    fieldError = true;
                    rule.url = 'cannot be non-url format';
                }
                if (!fieldError && typeof val.equalTo !== 'undefined' && !equalTo(object.refField,val.equalTo)) {
                    fieldError = true;
                    rule.equalTo = 'cannot be different from '+ {val:equalTo}
                
                }
                if (!fieldError && typeof val.oneOf !== 'undefined'&& !oneOf(object.refField,val.oneOf)) {
                    fieldError = true;
                    rule.oneOf = 'cannot be exclude from '+ val.oneOf;

                }

                rules[key] = rule;

            }


        }
        console.log(rules);
        //return rules

    });

      
};

