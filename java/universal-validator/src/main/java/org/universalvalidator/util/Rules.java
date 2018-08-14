package org.universalvalidator.util;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rules {
    private static Pattern emailPattern = Pattern.compile("[a-zA-Z0-9[!#$%&'()*+,/\\-_\\.\"]]+@[a-zA-Z0-9[!#$%&'()*+,/\\-_\"]]+\\.[a-zA-Z0-9[!#$%&'()*+,/\\-_\"\\.]]+");
    private static Pattern urlPattern = Pattern.compile("(?i)\\b(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,}))\\.?)(?::\\d{2,5})?(?:[/?#]\\S*)?\\b");



    public boolean required(Object o){
        if(o==null){
            return false;
        }
        else if(o instanceof String && ((String)o).length() == 0){
            return false;
        }
        return true;
    }

    public boolean min(Object o, int val){
        if(o==null){
            return true;
        }
        else if(o instanceof Integer && ((Integer)o)>=val){
            return true;
        }
        return false;
    }

    public boolean max(Object o, int val){
        if(o==null){
            return true;
        }
        else if(o instanceof Integer && ((Integer)o)<=val){
            return true;
        }
        return false;
    }

    public boolean minLength(Object o, int length){
        if(o==null){
            return true;
        }
        else if(o instanceof String && ((String)o).length() >= length){
            return true;
        }
        return false;
    }

    public boolean maxLength(Object o, int length){
        if(o==null){
            return true;
        }
        else if(o instanceof String && ((String)o).length() <= length){
            return true;
        }
        return false;
    }

    public boolean email(Object o){
        if(o==null){
            return true;
        }
        else if(o instanceof String && isValidEmail((String)o)){
            return true;
        }
        return false;
    }

    public boolean url(Object o){
        if(o==null){
            return true;
        }
        else if(o instanceof String && isValidUrl((String)o)){
            return true;
        }
        return false;
    }

    public boolean equalTo(Object o1, Object o2){
        if(o1 == null && o2 == null){
            return true;
        }
        else {
            String objectString1 = new Gson().toJson(o1);
            String objectString2 = new Gson().toJson(o2);

            if(objectString1.equals(objectString2)){
                return true;
            }
            return false;
        }
    }

    public boolean oneOf(Object o, Object arr){
        if(o == null && arr == null){
            return true;
        }
        if(arr instanceof Object[] && Arrays.asList((Object[]) arr).contains(o)){
            return true;
        }
        return false;

    }


    private static boolean isValidEmail(String email)
    {
        Matcher m = emailPattern.matcher(email);
        return !m.matches();
    }

    private static boolean isValidUrl(String url)
    {
        Matcher m = urlPattern.matcher(url);
        return !m.matches();
    }


}
