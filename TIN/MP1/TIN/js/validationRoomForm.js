function validateForm() {
    const roomNumberInput = document.getElementById('roomNum');
    const roomPriceInput = document.getElementById('price');
    const roomDescriptionInput = document.getElementById('description');

    const errorRoomNum = document.getElementById('errorRoomNum');
    const errorPrice = document.getElementById('errorPrice');
    const errorDescription = document.getElementById('errorDescription');
    const errorsSummary = document.getElementById('errorsSummary');

    resetErrors([roomNumberInput, roomPriceInput, roomDescriptionInput], [errorRoomNum, errorPrice, errorDescription], errorsSummary);

    let valid = true;
    if (!checkRequired(roomNumberInput.value)) {
        valid = false;
        roomNumberInput.classList.add('error-input');
        errorRoomNum.innerText = "The field is required.";
    } else if (!checkTextLengthRange(roomNumberInput.value, 1, 3)) {
        valid = false;
        roomNumberInput.classList.add('error-input');
        errorRoomNum.innerText = "The field should contain 1 to 3 characters.";
    }

    if (!checkRequired(roomPriceInput.value)) {
        valid = false;
        roomPriceInput.classList.add('error-input');
        errorPrice.innerText = "The field is required.";
    } else if (!checkTextLengthRange(roomPriceInput.value, 1, 10)) {
        valid = false;
        roomPriceInput.classList.add('error-input');
        errorPrice.innerText = "The field should contain 1 to 10 characters.";
    }

    if (!checkRequired(roomDescriptionInput.value)) {
        valid = false;
        roomDescriptionInput.classList.add('error-input');
        errorDescription.innerText = "The field is required.";
    } else if (!checkTextLengthRange(roomDescriptionInput.value, 1, 60)) {
        valid = false;
        roomDescriptionInput.classList.add('error-input');
        errorDescription.innerText = "The field should contain 1 to 60 characters.";
    }

    if (!valid) {
        errorsSummary.innerText = "Form contans errors.";
    }

    return valid;
}