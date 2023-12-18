const Joi = require('joi');

const errMessages = (errors) => {
    errors.forEach(err => {
        switch (err.code) {
            case "string.empty":
                err.message = "validationMessage.fieldRequired";
                break;
            case "date.min":
                err.message = "validationMessage.pastDate";
                break;
            case "date.greater":
                err.message = "validationMessage.endStartDateError";
                break;
            case "date.format":
                err.message = "validationMessage.validDate";
                break;
            default:
                break;
        }
    });
    return errors;
}

let nowDate = new Date(),
    month = '' + (nowDate.getMonth() + 1),
    day = '' + nowDate.getDate(),
    year = '' + nowDate.getFullYear();

if (month.length < 2)
    month = '0' + month;
if (day.length < 2)
    day = '0' + day;
const nowString = [year, month, day].join('-');

const roomSchema = Joi.object({
    _id: Joi.number()
        .optional()
        .allow("")
        .error(errMessages),
    room_id: Joi.string()
        .required()
        .error(errMessages),
    res_id: Joi.string()
        .required()
        .error(errMessages),
    start_date: Joi.date()
        .iso()
        .required()
        .min(nowString)
        .error(errMessages),
    end_date: Joi.date()
        .iso()
        .required()
        .greater(Joi.ref('start_date'))
        .min(nowString)
        .error(errMessages)
});

module.exports = roomSchema;