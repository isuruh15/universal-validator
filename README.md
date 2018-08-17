<h1 align="center">
  <br>
  <a href="#"><img src="https://raw.githubusercontent.com/nilankamanoj/universal-validator/master/assets/images/universal_validator.png" alt="UNIVERSAL VALIDATOR" width="200"></a>
  <br>
  UNIVERSAL VALIDATOR
  <br>
  <br>
</h1>

**Centralized validation policy controller over multiple deployments.**

This prevents the cost of :
- Implementing the validation for same set of objects in multiple subsystems.
- Re -coding, re-compilation and re-test on policy change.
- Re-deployment and non-zero downtime of upgrading.

# wrapping libraries :
- jquery : https://jqueryvalidation.org/
- node : https://www.npmjs.com/package/validator

# Supporting validations
|key|jquery|java|nodejs|
|---|---|---|---|
|required    |ok|ok||
|min         |ok|ok||
|max         |ok|ok||
|minLength   |ok|ok||
|maxLength   |ok|ok||
|email       |ok|ok||
|url         |ok|ok||
|equalTo     |ok|ok||
|oneOf       |ok|ok||



# Use Universel-Validator
## 1. Universal setup for validations
- create **validation.json** and declare **forms - fields - field rules** according to the key convention.
- **form-names** should be exactly same to the **form id** s that declared in the html forms.
- **field-names** should be same as the **input id** s and **attribute names** of the java/ javascript classes.

eg:

```json
{
    "formRegister": {
        "name": {
            "required": true,
            "maxLength": 10,
            "minLength": 3
        },
        "age": {
            "required": true,
            "max": 50,
            "min": 18
        },
        "sex": {
            "required": true,
            "oneOf": [
                "male",
                "female"
            ]
        },
        "password" :{
            "required" :true
        },
        "passwordConfirm":{
            "required" : true,
            "equalTo" : "password"
        },
        "email":{
            "email" : true
        },
        "website" :{
            "url" : true
        }
    }
}
```
- So, these rules will be the universal validation rules for the forms, nodejs servers and java backends.
- Host validation.json file in a public server that can be accessible over internet.

## 2. Configure Html forms for live validations.
- Import **jquery**(if not imported) and **jquery validation** in the head of Html.
- Declare **form-name** and the **url for validation.json**. Use **https://cors.io/?** prefix to avoid cross-domain fetching errors.
- Copy paste **jquery/test/universal.validator.min.js** into root folder and import it.

eg:
```html
<html>

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.js"></script>
    <script type="text/javascript">
        var formName = "formRegister";
        var url = 'https://cors.io/?http://nilankamanoj.tk/universal-validator/validation.json'
    </script>
    <script src="./universal.validator.min.js"></script>
</head>

<body>
    <form id="formRegister">
        <label for="name">name</label>
        <input id="name" name="name" />
        <br/>
        <label for="age">age</label>
        <input id="age" name="age" />
        <br/>
        <label for="sex">gender</label>
        <input id="sex" name="sex" />
        <br/>
        <label for="password">password</label>
        <input id="password" name="password" />
        <br/>
        <label for="passwordConfirm">passwordConfirm</label>
        <input id="passwordConfirm" name="passwordConfirm" />
        <br/>
        <label for="email">email</label>
        <input id="email" name="email" />
        <br/>
        <label for="website">website</label>
        <input id="website" name="website" />
        <input type="submit" value="Validate!">
        <br/>

</body>

</html>

```
- So, form submission is prevented until universal rules are satisfied by the user input.

## 3. Configure Java application
- Import universal validator to the project.
eg : 

maven :
```xml
<dependency>
  <groupId>com.github.nilankamanoj</groupId>
  <artifactId>validator</artifactId>
  <version>0.1.1</version>
</dependency>
```
gradle : 
```groovy
compile 'com.github.nilankamanoj:validator:0.1.1'
```
- All dependancy addition instructions for other dependancey managemant systems are available at, https://search.maven.org/artifact/com.github.nilankamanoj/validator/
- Initiate the validator instance along with the **URL of validation.json**.
- Validate **object** along with the **form-name** that you have given in the validation.json.

eg:
```java
import model.User;
import com.github.nilankamanoj.Validator;
import java.util.Map;

public class ValidatorTest {
    public void test() {
        User user = new User(20, "fxxsx", "male", "pass", "passs", "aaaa@aa.com", "https://aaaa.com");
        Validator validator = new Validator(
                "https://cors.io/?http://nilankamanoj.tk/universal-validator/validation.json");
        Map out = validator.validate(user, "formRegister");
        System.out.println(out);
    }

}
```
- **Out** of the validator is a **HashMap** that contains the boolean property of **hasErrr** that indicates the validity. 
- Hashmap contains the **error** along with the **field as the key** if **hasErr is true**.
```java
if(out.get("hasError)){
// handle error
}
else{
//continue process
}
```




