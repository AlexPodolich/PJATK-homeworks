const Joi = require('joi');
//const myCustomJoi = Joi.extend(require('joi-phone-number'));

const errMessages = (errors) => {
    errors.forEach(err => {
        switch (err.code) {
            case "string.empty":
                err.message = "validationMessage.fieldRequired";
            case "string.required":
                err.message = "validationMessage.fieldRequired";
                break;
            case "string.min":
                if(err.local.label == "first_name"){
                    err.message = "validationMessage.3-20charError";
                }else if(err.local.label == "last_name"){
                    err.message = "validationMessage.3-20charError";
                }else if(err.local.label == "phone_number"){
                    err.message = "validationMessage.9-13charError";
                }else if(err.local.label == "password"){
                    err.message = "validationMessage.4-16charError";
                }
                //err.message = `This field should contain at least ${err.local.limit} characters`;
                break;
            case "string.max":
                if(err.local.label == "first_name"){
                    err.message = "validationMessage.3-20charError";
                }else if(err.local.label == "last_name"){
                    err.message = "validationMessage.3-20charError";
                }else if(err.local.label == "phone_number"){
                    err.message = "validationMessage.9-13charError";
                }else if(err.local.label == "password"){
                    err.message = "validationMessage.4-16charError";
                }
                //err.message = `This field should contain at most ${err.local.limit} characters`;
                break;
            case "string.pattern.base":
                if(err.local.label == "phone_number"){
                    err.message = "validationMessage.validPhone";
                }else if(err.local.label == "password"){
                    err.message = "validationMessage.validPass";
                }  
                break;
            default:
                break;
        }
    });
    return errors;
}

const residentSchema = Joi.object({
    _id: Joi.number()
        .optional()
        .allow("")
        .error(errMessages),
    first_name: Joi.string()
        .min(3)
        .max(20)
        .required()
        .error(errMessages),
    last_name: Joi.string()
        .min(3)
        .max(20)
        .required()
        .error(errMessages),
    phone_number: Joi.string()
        .min(9)
        .max(13)
        .pattern(/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im)
        .required()
        .error(errMessages),
    password: Joi.string()
        .allow("")
        .min(4)
        .max(16)
        .pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[ -/:-@\[-`{-~]).{4,15}$/)
        .required()
        .error(errMessages)
});

module.exports = residentSchema;