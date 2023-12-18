import { Link } from "react-router-dom"

function ResidentForm() {
    return (
        <main>
            <h2>New resident</h2>
            <form className="form">
                <label htmlFor="first_name">Name: <span class="symbol-required">*</span></label>
                <input type="text" name="first_name" id="first_name" placeholder="3-20 characters" value="" />
                <span id="errorFirstName" className="errors-text"></span>
                <label htmlFor="last_name">Surname: <span class="symbol-required">*</span></label>
                <input type="text" name="last_name" id="last_name" placeholder="3-20 characters" value="" />
                <span id="errorLastName" className="errors-text"></span>
                <label htmlFor="phone_number">Phone number: <span class="symbol-required">*</span></label>
                <input type="text" name="phone_number" id="phone_number" placeholder="e.g. 0671234567" value="" />
                <span id="errorEmail" className="errors-text"></span>
                <div className="form-buttons">
                    <p id="errorsSummary" className="errors-text"></p>
                    <input className="form-button-submit" type="submit" value="Add" />
                    <Link to="/residents" className="form-button-cancel">Cancel</Link>
                </div>
            </form>
        </main >
    )
}

export default ResidentForm