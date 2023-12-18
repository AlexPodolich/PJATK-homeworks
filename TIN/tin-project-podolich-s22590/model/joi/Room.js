const Joi = require('joi');

const errMessages = (errors) => {
    errors.forEach(err => {
        console.log(err.code);
        switch (err.code) {
            case "string.empty":
                err.message = "validationMessage.fieldRequired";
                break;
            case "string.required":
                err.message = "validationMessage.fieldRequired";
                break;
            case "string.min":
                err.message = "validationMessage.1-6charError";
                break;
            case "string.max":
                err.message = "validationMessage.1-6charError";
                break;
            case "number.min":
                err.message = "validationMessage.validNumber";
                break;
            case "number.max":
                err.message = "validationMessage.validNumber";
                break;
            case "number.base":
                err.message = "validationMessage.fieldRequired";
                break;
            default:
                break;
        }
    });
    return errors;
}

const roomSchema = Joi.object({
    _id: Joi.number()
        .optional()
        .allow("")
        .error(errMessages),
    number: Joi.string()
        .min(1)
        .max(6)
        .required()
        .error(errMessages),
    price: Joi.number()
        .min(1000)
        .max(100000)
        .required()
        .error(errMessages)
});

module.exports = roomSchema;