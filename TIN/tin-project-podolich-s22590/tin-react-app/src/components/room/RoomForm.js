import { Link } from "react-router-dom"

function RoomForm() {
    return (
        <main>
            <h2>New room</h2>
            <form className="form">
                <label htmlFor="number">Room number: <span class="symbol-required">*</span></label>
                <input type="text" name="number" id="number" placeholder="1-6 characters" value="" />
                <span id="errorFirstName" className="errors-text"></span>
                <label htmlFor="price">Room pice: <span class="symbol-required">*</span></label>
                <input type="text" name="price" id="price" placeholder="number between 1000 and 100000" value="" />
                <span id="errorLastName" className="errors-text"></span>
                <div className="form-buttons">
                    <p id="errorsSummary" className="errors-text"></p>
                    <input className="form-button-submit" type="submit" value="Add" />
                    <Link to="/rooms" className="form-button-cancel">Cancel</Link>
                </div>
            </form>
        </main >
    )
}

export default RoomForm