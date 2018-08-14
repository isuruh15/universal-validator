package org.universalvalidator;

import org.universalvalidator.util.RuleLoader;

public class validator {
    private static RuleLoader ruleLoader;

    public static void init(){
        ruleLoader = new RuleLoader();
        ruleLoader.load();
    }

    public static void main(String[] args) {
        init();
    }
}
