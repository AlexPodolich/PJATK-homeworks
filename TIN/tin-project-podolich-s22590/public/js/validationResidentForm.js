function validateForm() {
    const residentNameInput = document.getElementById('first_name');
    const residentSurnameInput = document.getElementById('last_name');
    const residentPhoneNumberInput = document.getElementById('phone_number');
    const residentPasswordInput = document.getElementById('password');

    const errorResidentName = document.getElementById('errorFirstName');
    const errorResidentSurname = document.getElementById('errorSurname');
    const errorResidentPhoneNumber = document.getElementById('errorPhoneNum');
    const errorResidentPassword = document.getElementById('errorPassword');
    const errorsSummary = document.getElementById('errorsSummary');

    //MP3
    const reqMessage = document.getElementById('errorMessage-required').innerText;
    const char3_20Message = document.getElementById('errorMessage-char3-20').innerText;
    const char9_13Message = document.getElementById('errorMessage-char9-13').innerText;
    const validPhoneMessage = document.getElementById('errorMessage-validPhone').innerText;
    const char4_16Message = document.getElementById('errorMessage-char4-16').innerText;
    const validPassMessage = document.getElementById('errorMessage-validPass').innerText;
    const formErrorsMessage = document.getElementById('errorMessage-formErrors').innerText;

    const formType = document.getElementById('formType').innerText;

    resetErrors([residentNameInput, residentSurnameInput, residentPhoneNumberInput, residentPasswordInput], [errorResidentName, errorResidentSurname, errorResidentPhoneNumber, errorResidentPassword], errorsSummary);

    let valid = true;
    if (!checkRequired(residentNameInput.value)) {
        valid = false;
        residentNameInput.classList.add('error-input');
        errorResidentName.innerText = reqMessage;
    } else if (!checkTextLengthRange(residentNameInput.value, 3, 20)) {
        valid = false;
        residentNameInput.classList.add('error-input');
        errorResidentName.innerText = char3_20Message;
    }

    if (!checkRequired(residentSurnameInput.value)) {
        valid = false;
        residentSurnameInput.classList.add('error-input');
        errorResidentSurname.innerText = reqMessage;
    } else if (!checkTextLengthRange(residentSurnameInput.value, 3, 20)) {
        valid = false;
        residentSurnameInput.classList.add('error-input');
        errorResidentSurname.innerText = char3_20Message;
    }

    if (!checkRequired(residentPhoneNumberInput.value)) {
        valid = false;
        residentPhoneNumberInput.classList.add('error-input');
        errorResidentPhoneNumber.innerText = reqMessage;
    } else if (!checkTextLengthRange(residentPhoneNumberInput.value, 9, 13)) {
        valid = false;
        residentPhoneNumberInput.classList.add('error-input');
        errorResidentPhoneNumber.innerText = char9_13Message;
    } else if (!checkPhoneNumber(residentPhoneNumberInput.value)) {
        valid = false;
        residentPhoneNumberInput.classList.add('error-input');
        errorResidentPhoneNumber.innerText = validPhoneMessage;
    }
    
    if(formType == 'createNew' || (formType == 'edit' && residentPasswordInput.value != "")){
        if (!checkRequired(residentPasswordInput.value)) {
            valid = false;
            residentPasswordInput.classList.add('error-input');
            errorResidentPassword.innerText = reqMessage;
        } else if (!checkTextLengthRange(residentPasswordInput.value, 4, 16)) {
            valid = false;
            residentPasswordInput.classList.add('error-input');
            errorResidentPassword.innerText = char4_16Message;
        } else if (!checkPassword(residentPasswordInput.value)) {
            valid = false;
            residentPasswordInput.classList.add('error-input');
            errorResidentPassword.innerText = validPassMessage;
        };
    }


    if (!valid) {
        errorsSummary.innerText = formErrorsMessage;
    }

    return valid;
}