package org.universalvalidator;

import org.json.JSONObject;
import org.universalvalidator.util.Mapper;
import org.universalvalidator.util.RuleLoader;
import java.util.HashMap;
import java.util.Map;

public class Validator {
    private RuleLoader ruleLoader;


    public Validator(){
        ruleLoader = new RuleLoader("https://cors.io/?http://nilankamanoj.tk/universal-validator/validation.json");
        JSONObject rules= ruleLoader.load();
    }

    public Map validate(Object object, String form){
        Map out = new HashMap<String,Object>();
        Mapper mapper = new Mapper();
        JSONObject model= mapper.map(object);
        System.out.println(model.toString());
        return out;
    }


}
