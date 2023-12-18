function validateForm() {
    const roomInput = document.getElementById('room_id');
    const residentInput = document.getElementById('res_id');
    const startDateInput = document.getElementById('start_date');
    const endDateInput = document.getElementById('end_date');

    const errorRoomInput = document.getElementById('errorRoom');
    const errorResidentInput = document.getElementById('errorResident');
    const errorStartDateInput = document.getElementById('errorStartDate');
    const errorEndDateInput = document.getElementById('errorEndDate');

    const errorsSummary = document.getElementById('errorsSummary');

    //MP3
    const reqMessage = document.getElementById('errorMessage-required').innerText;
    const validDateMessage = document.getElementById('errorMessage-validDate').innerText;
    const pastDateMessage = document.getElementById('errorMessage-pastDate').innerText;
    const formErrorsMessage = document.getElementById('errorMessage-formErrors').innerText;
    const endStartDateErrorMessage = document.getElementById('errorMessage-endStartDateError').innerText;

    resetErrors([roomInput, residentInput, startDateInput, endDateInput], [errorRoomInput, errorResidentInput, errorStartDateInput, errorEndDateInput], errorsSummary);

    let valid = true;

    if (!checkRequired(roomInput.value)) {
        valid = false;
        roomInput.classList.add('error-input');
        errorRoomInput.innerText = reqMessage;
    }

    if (!checkRequired(residentInput.value)) {
        valid = false;
        residentInput.classList.add('error-input');
        errorResidentInput.innerText = reqMessage;
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

    if (!(startDateInput.value)) {
        valid = false;
        startDateInput.classList.add('error-input');
        errorStartDateInput.innerText = reqMessage;
    } else if (!checkDate(startDateInput.value)) {
        valid = false;
        startDateInput.classList.add('error-input');
        errorStartDateInput.innerText = validDateMessage;
    } else if (checkDateIfAfter(startDateInput.value, nowString)) {
        valid = false;
        startDateInput.classList.add('error-input');
        errorStartDateInput.innerText = pastDateMessage;
    } else if (checkRequired(startDateInput.value) && checkDate(startDateInput.value) && !checkDateIfAfter(startDateInput.value, endDateInput.value)) {
        valid = false;
        endDateInput.classList.add('error-input');
        errorEndDateInput.innerText = endStartDateErrorMessage;
    }

    if (!valid) {
        errorsSummary.innerText = formErrorsMessage;
    }

    return valid;
}