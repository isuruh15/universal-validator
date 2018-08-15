import validator from 'validator';


export function required(object){
    if (typeof object ==='undefined'){return false}
    else if (!validator.isBoolean(object)){return false}
    else{return true};
}
export function min(object,ref){
    if (typeof object ==='undefined' && typeof ref === 'undefined'){return true}
    else if (validator.isInt(''+object,{gt:''+(ref-1)})){return true}
    else{return false};
}

export function max(object,ref){
    if (typeof object ==='undefined'){return true}
    else if (validator.isInt(''+object,{lt:''+(ref+1)})){return true}
    else{return false};
}

export function maxlength(object,ref){
    if (typeof object ==='undefined'){return true}
    else if (validator.isInt(''+object,{lt:''+(ref+1)})){return true}
    else{return false};
}

export function minLength(object,ref){
    if (typeof object ==='undefined'){return true}
    else if (validator.isInt(''+object,{gt:''+(ref-1)})){return true}
    else{return false};
}

export function email(object){
    if (object=='undefined'){return true}
    else if (validator.isEmail(object)){return true}
    else{return false};
}

export function url(object){
    if (typeof object ==='undefined'){return true}
    else if (validator.isURL(object)){return true}
    else{return false};
}

export function equalTo(object_a, object_b){
    if (object_a=='undefined' && object_b=='undefined'){return true}
    else if (object_a ===   object_b){return true}
    else{return false};
}

export function oneOf(object, refArr){
    if (typeof object === 'undefined' && typeof refArr ==='undefined'){return true}
    else if (typeof object=== 'number' || typeof object === 'string'){
        var strObject = ''+object;
        var strArr = refArr.map(String);
        if (validator.isIn(strObject,strArr)){return true}    
        
    }
    else{return false};
}


