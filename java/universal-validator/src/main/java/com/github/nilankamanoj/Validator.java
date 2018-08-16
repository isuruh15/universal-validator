package com.github.nilankamanoj;

import com.github.nilankamanoj.util.*;
import org.json.JSONObject;
import com.google.common.collect.Lists;

import java.util.*;

public class Validator {
    private JSONObject rules;
    private Rules ruleTester;

    public Validator(String url) {
        RuleLoader ruleLoader = new RuleLoader(url);
        this.rules = ruleLoader.load();
        this.ruleTester = new Rules();
    }

    public Map validate(Object object, String form) {
        Map out = new HashMap<String, Object>();
        Boolean hasError = false;
        Mapper mapper = new Mapper();
        JSONObject model = mapper.map(object);

        JSONObject relatedRules = (JSONObject) this.rules.get(form);
        System.out.println(relatedRules);
        Iterator<?> keys = relatedRules.keys();

        while (keys.hasNext()) {
            String key = (String) keys.next();
            Boolean fieldError = false;

            if (relatedRules.get(key) instanceof JSONObject) {
                JSONObject rule = (JSONObject) relatedRules.get(key);
                List<String> ruleKeys = Lists.newArrayList(rule.keys());
                if (ruleKeys.contains("required") && rule.get("required") instanceof Boolean
                        && (Boolean) rule.get("required") == true) {
                    if (!ruleTester.required(model.get(key))) {
                        hasError = true;
                        fieldError = true;
                        out.put(key, "cannot be empty");
                    }
                }
                if (ruleKeys.contains("min") && !fieldError && rule.get("min") instanceof Integer) {
                    if (!ruleTester.min(model.get(key), (Integer) rule.get("min"))) {
                        hasError = true;
                        fieldError = true;
                        out.put(key, "cannot be less than " + rule.get("min"));
                    }
                }
                if (ruleKeys.contains("max") && !fieldError && rule.get("max") instanceof Integer) {
                    if (!ruleTester.max(model.get(key), (Integer) rule.get("max"))) {
                        hasError = true;
                        fieldError = true;
                        out.put(key, "cannot be greater than " + rule.get("max"));
                    }
                }
                if (ruleKeys.contains("minLength") && !fieldError && rule.get("minLength") instanceof Integer) {
                    if (!ruleTester.minLength(model.get(key), (Integer) rule.get("minLength"))) {
                        hasError = true;
                        fieldError = true;
                        out.put(key, "cannot be shorter than " + rule.get("minLength"));
                    }
                }
                if (ruleKeys.contains("maxLength") && !fieldError && rule.get("maxLength") instanceof Integer) {
                    if (!ruleTester.maxLength(model.get(key), (Integer) rule.get("maxLength"))) {
                        hasError = true;
                        fieldError = true;
                        out.put(key, "cannot be longer than " + rule.get("maxLength"));
                    }
                }
                if (ruleKeys.contains("email") && !fieldError && rule.get("email") instanceof Boolean) {

                    if (!ruleTester.email(model.get(key))) {
                        hasError = true;
                        fieldError = true;
                        out.put(key, "cannot be non-email format");
                    }
                }
                if (ruleKeys.contains("url") && !fieldError && rule.get("url") instanceof Boolean) {
                    if (!ruleTester.url(model.get(key))) {
                        hasError = true;
                        fieldError = true;
                        out.put(key, "cannot be non-url format");
                    }
                }
                if (ruleKeys.contains("equalTo") && !fieldError && rule.get("equalTo") instanceof String) {

                    if (!ruleTester.equalTo(model.get(key), model.get((String) rule.get("equalTo")))) {
                        hasError = true;
                        fieldError = true;
                        out.put(key, "cannot be different from " + (String) rule.get("equalTo"));
                    }
                }
                if (ruleKeys.contains("oneOf") && !fieldError && rule.get("oneOf") != null) {
                    if (!ruleTester.oneOf(model.get(key), rule.get("oneOf"))) {
                        hasError = true;
                        fieldError = true;
                        out.put(key, "cannot be exclude from " + rule.get("oneOf").toString());
                    }
                }

            }
        }
        if (hasError) {
            out.put("hasError", true);
        } else {
            out.put("hasError", false);
        }
        return out;
    }

}
