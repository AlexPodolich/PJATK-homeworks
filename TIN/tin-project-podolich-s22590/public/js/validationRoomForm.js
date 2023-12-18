function validateForm() {
    const roomNumberInput = document.getElementById('number');
    const roomPriceInput = document.getElementById('price');
    //const roomDescriptionInput = document.getElementById('description');

    const errorRoomNum = document.getElementById('errorRoomNum');
    const errorPrice = document.getElementById('errorPrice');
    //const errorDescription = document.getElementById('errorDescription');
    const errorsSummary = document.getElementById('errorsSummary');

    //MP3
    const reqMessage = document.getElementById('errorMessage-required').innerText;
    const char1_6Message = document.getElementById('errorMessage-1-6charError').innerText;
    const numberRequiredMessage = document.getElementById('errorMessage-numberRequired').innerText;
    const validNumberMessage = document.getElementById('errorMessage-validNumber').innerText;
    const formErrorsMessage = document.getElementById('errorMessage-formErrors').innerText;

    //resetErrors([roomNumberInput, roomPriceInput, roomDescriptionInput], [errorRoomNum, errorPrice, errorDescription], errorsSummary);
    resetErrors([roomNumberInput, roomPriceInput], [errorRoomNum, errorPrice], errorsSummary);

    let valid = true;
    if (!checkRequired(roomNumberInput.value)) {
        valid = false;
        roomNumberInput.classList.add('error-input');
        errorRoomNum.innerText = reqMessage;
    } else if (!checkTextLengthRange(roomNumberInput.value, 1, 6)) {
        valid = false;
        roomNumberInput.classList.add('error-input');
        errorRoomNum.innerText = char1_6Message;
    }

    if (!checkRequired(roomPriceInput.value)) {
        valid = false;
        roomPriceInput.classList.add('error-input');
        errorPrice.innerText = reqMessage;
    } else if (!checkNumber(roomPriceInput.value)) {
        valid = false;
        roomPriceInput.classList.add('error-input');
        errorPrice.innerText = numberRequiredMessage;
    } else if (!checkNumberRange(roomPriceInput.value, 1000, 100000)) {
        valid = false;
        roomPriceInput.classList.add('error-input');
        errorPrice.innerText = validNumberMessage;
    }

    // if (!checkRequired(roomDescriptionInput.value)) {
    //     valid = false;
    //     roomDescriptionInput.classList.add('error-input');
    //     errorDescription.innerText = "The field is required.";
    // } else if (!checkTextLengthRange(roomDescriptionInput.value, 1, 60)) {
    //     valid = false;
    //     roomDescriptionInput.classList.add('error-input');
    //     errorDescription.innerText = "The field should contain 1 to 60 characters.";
    // }

    if (!valid) {
        errorsSummary.innerText = formErrorsMessage;
    }

    return valid;
}