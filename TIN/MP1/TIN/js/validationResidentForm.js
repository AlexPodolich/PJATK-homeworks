function validateForm() {
    const residentNameInput = document.getElementById('firstName');
    const residentSurnameInput = document.getElementById('surname');
    const residentPhoneNumberInput = document.getElementById('phoneNum');

    const errorResidentName = document.getElementById('errorFirstName');
    const errorresidentSurname = document.getElementById('errorSurname');
    const errorResidentPhoneNumber = document.getElementById('errorPhoneNum');
    const errorsSummary = document.getElementById('errorsSummary');

    resetErrors([residentNameInput, residentSurnameInput, residentPhoneNumberInput], [errorResidentName, errorresidentSurname, errorResidentPhoneNumber], errorsSummary);

    let valid = true;
    if (!checkRequired(residentNameInput.value)) {
        valid = false;
        residentNameInput.classList.add('error-input');
        errorResidentName.innerText = "The field is required.";
    } else if (!checkTextLengthRange(residentNameInput.value, 2, 15)) {
        valid = false;
        residentNameInput.classList.add('error-input');
        errorResidentName.innerText = "The field should contain 2 to 15 characters.";
    }

    if (!checkRequired(residentSurnameInput.value)) {
        valid = false;
        residentSurnameInput.classList.add('error-input');
        errorresidentSurname.innerText = "The field is required.";
    } else if (!checkTextLengthRange(residentSurnameInput.value, 2, 15)) {
        valid = false;
        residentSurnameInput.classList.add('error-input');
        errorresidentSurname.innerText = "The field should contain 2 to 15 characters.";
    }

    if (!checkRequired(residentPhoneNumberInput.value)) {
        valid = false;
        residentPhoneNumberInput.classList.add('error-input');
        errorResidentPhoneNumber.innerText = "The field is required.";
    } else if (!checkTextLengthRange(residentPhoneNumberInput.value, 9, 12)) {
        valid = false;
        residentPhoneNumberInput.classList.add('error-input');
        errorResidentPhoneNumber.innerText = "The field should contain 9 to 12 characters.";
    } else if (!checkPhoneNumber(residentPhoneNumberInput.value)) {
        valid = false;
        residentPhoneNumberInput.classList.add('error-input');
        errorResidentPhoneNumber.innerText = "The field should contain a valid phone number.";
    }

    if (!valid) {
        errorsSummary.innerText = "Form contans errors.";
    }

    return valid;
}