function validateForm() {
    const roomInput = document.getElementById('room');
    const residentInput = document.getElementById('resident');
    const priceInput = document.getElementById('price');
    const startDateInput = document.getElementById('startDate');
    const endDateInput = document.getElementById('endDate');

    const errorRoomInput = document.getElementById('errorRoom');
    const errorResidentInput = document.getElementById('errorResident');
    const errorPriceInput = document.getElementById('errorPrice');
    const errorStartDateInput = document.getElementById('errorStartDate');
    const errorEndDateInput = document.getElementById('errorEndDate');
    const errorsSummary = document.getElementById('errorsSummary');

    resetErrors([roomInput, residentInput, priceInput, startDateInput, endDateInput], [errorRoomInput, errorResidentInput, errorPriceInput, errorStartDateInput, errorEndDateInput], errorsSummary);

    let valid = true;

    if (!checkRequired(roomInput.value)) {
        valid = false;
        roomInput.classList.add('error-input');
        errorRoomInput.innerText = "The field is required.";
    } else if (!checkTextLengthRange(roomInput.value, 2, 15)) {
        valid = false;
        roomInput.classList.add('error-input');
        errorRoomInput.innerText = "The field should contain 2 to 15 characters.";
    }

    if (!checkRequired(residentInput.value)) {
        valid = false;
        residentInput.classList.add('error-input');
        errorResidentInput.innerText = "The field is required.";
    } else if (!checkTextLengthRange(residentInput.value, 2, 15)) {
        valid = false;
        residentInput.classList.add('error-input');
        errorResidentInput.innerText = "The field should contain 2 to 15 characters.";
    }

    if (!checkRequired(priceInput.value)) {
        valid = false;
        priceInput.classList.add('error-input');
        errorPriceInput.innerText = "The field is required.";
    } else if (!checkNumber(priceInput.value)) {
        valid = false;
        priceInput.classList.add('error-input');
        errorPriceInput.innerText = "The field should be a number.";
    } else if (!checkNumberRange(priceInput.value, 5000, 100000)) {
        valid = false;
        priceInput.classList.add('error-input');
        errorPriceInput.innerText = "The field should be a number from 5000 to 100000.";
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

    if (!checkRequired(startDateInput.value)) {
        valid = false;
        startDateInput.classList.add('error-input');
        errorStartDateInput.innerText = "The field is required.";
    } else if (!checkDate(startDateInput.value)) {
        valid = false;
        startDateInput.classList.add('error-input');
        errorStartDateInput.innerText = "The field shold contain the date in the yyyy-MM-dd format (e.g. 2000-01-01).";
    } else if (checkDateIfAfter(startDateInput.value, nowString)) {
        valid = false;
        startDateInput.classList.add('error-input');
        errorStartDateInput.innerText = "The date cannot be from the past.";
    } else if (checkRequired(startDateInput.value) && checkDate(startDateInput.value) && !checkDateIfAfter(startDateInput.value, endDateInput.value)) {
        valid = false;
        endDateInput.classList.add('error-input');
        errorEndDateInput.innerText = "The \"End date\" should be later than the \"Start date\".";
    }

    if (!valid) {
        errorsSummary.innerText = "Form contans errors.";
    }

    return valid;
}